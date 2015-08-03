package org.jboss.rhiot.beacon.common;


/**
 * An enum encapsulating the JMSType string and "messageType" int property used for the
 * javax.jms.Message(s) sent to broker
 *
 * @author Scott Stark (sstark@redhat.com) (C) 2014 Red Hat Inc.
 */
public enum MsgType {
    /** A beacon event read by a scanner */
    SCANNER_READ,
    /** A status heartbeat from the scanner */
    SCANNER_HEARTBEAT,
    /** A scanner status message */
    SCANNER_STATUS

}
