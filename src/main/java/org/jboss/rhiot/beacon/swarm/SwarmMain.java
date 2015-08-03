package org.jboss.rhiot.beacon.swarm;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.msc.ServiceActivatorArchive;

import java.net.URL;

/**
 * Must set -Djava.library.path=/usr/local/lib on vm args
 */
public class SwarmMain {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
        Container container = new Container(false);
        container.setArgs(args);
        container.start();

        JavaArchive deployment = ShrinkWrap.create(JavaArchive.class);


        // Add all of the scanner packages
        String[] packages = {
            "org.jboss.rhiot.beacon.bluez",
            "org.jboss.rhiot.beacon.common",
            "org.jboss.rhiot.beacon.publishers",
            "org.jboss.rhiot.beacon.scannerjni",
            "com.beust.jcommander"
        };
        deployment.addPackages(true, packages);
        deployment.addClass(ScannerService.class);
        URL loggingPropsURL = SwarmMain.class.getResource("/logging.properties");
        deployment.addAsResource(loggingPropsURL, "/logging.properties");
        deployment.as(ServiceActivatorArchive.class).addServiceActivator(ScannerServiceActivator.class);

        container.deploy(deployment);
    }

}
