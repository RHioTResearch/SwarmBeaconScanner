package org.jboss.rhiot.beacon.publishers.qpid;

import org.jboss.rhiot.beacon.bluez.BeaconInfo;
import org.jboss.rhiot.beacon.common.Beacon;
import org.jboss.rhiot.beacon.common.MsgPublisher;

import java.util.Properties;

/**
 * Created by starksm on 7/12/15.
 */
public class QpidPublisher implements MsgPublisher {
    @Override
    public void setDestinationName(String name) {

    }

    @Override
    public String getDestinationName() {
        return null;
    }

    @Override
    public int getReconnectInterval() {
        return 0;
    }

    @Override
    public void setReconnectInterval(int reconnectInterval) {

    }

    @Override
    public boolean isReconnectOnFailure() {
        return false;
    }

    @Override
    public void setReconnectOnFailure(boolean reconnectOnFailure) {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void setConnected(boolean connected) {

    }

    @Override
    public void setUseTopics(boolean flag) {

    }

    @Override
    public boolean isUseTopics() {
        return false;
    }

    @Override
    public boolean isUseTransactions() {
        return false;
    }

    @Override
    public void setUseTransactions(boolean useTransactions) {

    }

    @Override
    public void start(boolean asyncMode) throws Exception {

    }

    @Override
    public void stop() throws Exception {

    }

    @Override
    public void publish(String destinationName, BeaconInfo beaconInfo) {

    }

    @Override
    public void publish(String destinationName, Beacon beacon) {

    }

    @Override
    public void publishStatus(BeaconInfo beaconInfo) {

    }

    @Override
    public void publishStatus(Beacon beacon) {

    }

    @Override
    public void publishProperties(String destinationName, Properties properties) {

    }
}
