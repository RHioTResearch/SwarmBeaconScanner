package org.jboss.rhiot.beacon.common;


/**
 * @author Scott Stark (sstark@redhat.com) (C) 2014 Red Hat Inc.
 */
public enum StatusProperties {
    ScannerID,          // the name of the scanner passed in via the --scannerID argument
    HostIPAddress,      // the ip address of the scanner host
    MACAddress,         // the MAC address of the scanner host
    SystemType,         // Type of the system; PiB, PiB+, P2B, IntelNUC, BeagleBoneBlack, ...
    SystemOS,           // Type of the operating system; Pidora, Raspbian, ...
    SystemTime,         // strftime(timestr, 128, "%F %T", tm) = YYYY-MM-DD HH:MM:SS
    SystemTimeMS,       // system time in milliseconds since epoch
    Uptime,             // uptime of scanner process in seconds as string formatted as "uptime: %ld, days:%d, hrs: %d, min: %d"
    SystemUptime,       // uptime of system in seconds as string formatted as "uptime: %ld, days:%d, hrs: %d, min: %d"
    Procs,              // number of procs active on the scanner
    LoadAverage,        // load averages for the past 1, 5, and 15 minutes "load average: 0.00, 0.01, 0.05"
    RawEventCount,      // Raw number of BLE iBeacon type of events from the bluetooth stack
    PublishEventCount,  // The number of time windowed events pushed to the message broker
    HeartbeatCount,     // The number of events from the scanner's associated --heartbeatUUID beacon
    HeartbeatRSSI,      // The average RSSI for the scanner's associated --heartbeatUUID beacon
    EventsWindow,       // The counts of beacon events as a sequence of +{minorID}: {count}; values
    ActiveBeacons,      // The count of beacons active in the last events window
    MemTotal,           // Total memory on scanner in MB
    MemFree,            // Free memory on scanner in MB
    MemActive,          // Total - Free memory on scanner in MB
    SwapTotal,          // Total swap memory on scanner in MB
    SwapFree,           // Free swap memory on scanner in MB
}
