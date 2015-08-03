package org.jboss.rhiot.beacon.swarm;

import com.beust.jcommander.JCommander;
import org.jboss.logging.Logger;
import org.jboss.msc.service.Service;
import org.jboss.msc.service.ServiceContainer;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;
import org.jboss.rhiot.beacon.bluez.HCIDump;
import org.jboss.rhiot.beacon.common.ParseCommand;
import org.jboss.rhiot.beacon.scannerjni.HCIDumpParser;
import org.jboss.rhiot.beacon.scannerjni.HealthStatus;

import java.net.SocketException;

public class ScannerService implements Service<Void> {
    private static Logger log = Logger.getLogger(ScannerService.class);
    private ParseCommand cmdArgs;
    private HCIDumpParser parser;

    public ScannerService(String[] args) {
        System.loadLibrary("scannerJni");
        log.info("Loaded native scannerJni library");

        cmdArgs = new ParseCommand();
        JCommander cmdArgParser = new JCommander(cmdArgs);
        cmdArgParser.parse(args);

        // If scannerID is the string {IP}, replace it with the host IP address
        String scannerID = cmdArgs.scannerID;
        if(scannerID.compareTo("{IP}") == 0) {
            char hostIPAddress[] = new char[128];
            char macaddr[] = new char[32];
            try {
                HealthStatus.getHostInfo(hostIPAddress, macaddr);
                cmdArgs.scannerID = new String(hostIPAddress);
            } catch (SocketException e) {
                log.warn("Failed to read host address info", e);
            }
        }
    }

    @Override
    public void start(StartContext context) throws StartException {
        parser = new HCIDumpParser(cmdArgs);
        final ServiceContainer serviceContainer = context.getController().getServiceContainer();

        try {
            log.infof("Begin scanning");
            // Start the scanner parser handler threads other than the native stack handler
            parser.start();
            // Setup the native bluetooth stack integration, callbacks, and stack thread
            HCIDump.setRawEventCallback(parser::beaconEvent);
            HCIDump.initScanner(cmdArgs.hciDev);
            // Schedule a thread to wait for a shutdown marker
            Thread shutdownMonitor = new Thread(() -> awaitShutdown(serviceContainer), "ShutdownMonitor");
            shutdownMonitor.setDaemon(true);
            shutdownMonitor.start();
        } catch (Exception e) {
            log.error("Scanner exiting on exception", e);
            context.failed(new StartException(e));
        }
    }

    @Override
    public void stop(StopContext context) {
        // Shutdown the parser
        try {
            parser.stop();
        } catch (Exception e) {
            log.error("Failed to stop parser", e);
        }
        log.infof("End scanning");
    }

    @Override
    public Void getValue() throws IllegalStateException, IllegalArgumentException {
            return null;
    }

    private void awaitShutdown(final ServiceContainer serviceContainer) {
        parser.waitForStop();
        log.info("Shutdown marker seen, stopping container");
        serviceContainer.shutdown();
    }

}
