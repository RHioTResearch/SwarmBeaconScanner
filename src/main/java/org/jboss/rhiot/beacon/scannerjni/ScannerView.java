package org.jboss.rhiot.beacon.scannerjni;

import org.jboss.rhiot.beacon.common.StatusInformation;

/**
 * Created by starksm on 7/11/15.
 */
public interface ScannerView {
    void displayStatus(StatusInformation statusInformation);

    boolean isDisplayBeaconsMode();
}
