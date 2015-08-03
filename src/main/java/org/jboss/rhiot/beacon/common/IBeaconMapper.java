package org.jboss.rhiot.beacon.common;

/**
 * Interface for mapping from the beacon minor id to a registered user.
 */
@FunctionalInterface
public interface IBeaconMapper {
    public String lookupUser(int minorID);
}
