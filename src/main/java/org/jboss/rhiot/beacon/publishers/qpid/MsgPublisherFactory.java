package org.jboss.rhiot.beacon.publishers.qpid;

import org.jboss.rhiot.beacon.common.MsgPublisher;
import org.jboss.rhiot.beacon.common.MsgPublisherType;

/**
 * TODO
 */
public class MsgPublisherFactory implements org.jboss.rhiot.beacon.common.MsgPublisherFactory {
    public MsgPublisher create(MsgPublisherType type, String brokerUrl, String userName, String password, String clientID) {
        MsgPublisher publisher = null;
        switch (type) {
            case AMQP_QPID:
                publisher = new QpidPublisher();
                break;
        }
        return publisher;
    }
}
