package org.jboss.rhiot.beacon.swarm;

import org.jboss.logging.Logger;
import org.jboss.msc.service.ServiceActivator;
import org.jboss.msc.service.ServiceActivatorContext;
import org.jboss.msc.service.ServiceController;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceRegistryException;
import org.jboss.msc.service.ServiceTarget;
import org.jboss.msc.service.ValueService;

import java.util.Arrays;

/**
 * Created by starksm on 8/2/15.
 */
public class ScannerServiceActivator implements ServiceActivator {
    private static Logger log = Logger.getLogger(ScannerServiceActivator.class);

    public void activate(ServiceActivatorContext serviceActivatorContext) throws ServiceRegistryException {

        ServiceTarget target = serviceActivatorContext.getServiceTarget();
        ServiceController argsController = serviceActivatorContext.getServiceRegistry().getService(ServiceName.of("wildfly", "swarm", "main-args"));
        ValueService<String[]> argsService = (ValueService<String[]>) argsController.getService();
        String[] args = argsService.getValue();
        log.infof("Args available to services: %s\n", Arrays.asList(args));

        ScannerService service = new ScannerService(args);
        ServiceName serviceName = ServiceName.parse("org.jboss.rhiot.beacon.swarm.ScannerService");
        target.addService(serviceName, service)
            .install();
    }
}
