package org.jboss.rhiot.beacon.bluez;

import org.jboss.rhiot.beacon.common.Beacon;

/**
 * Higher level callback that unwraps the raw native beacon event into a Beacon object
 */
@FunctionalInterface
public interface IEventCallback {
    public boolean beaconEvent(Beacon beacon);
}
