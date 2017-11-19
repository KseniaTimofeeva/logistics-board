package com.tsystems.app.logisticsboard.service;

import com.tsystems.app.logisticsboard.enums.ChannelWS;
import com.tsystems.app.logisticsboard.mbeans.ws.DriverInfoWSBean;
import com.tsystems.app.logisticsboard.mbeans.ws.GeneralInfoWSBean;
import com.tsystems.app.logisticsboard.mbeans.ws.OrderInfoWSBean;
import com.tsystems.app.logisticsboard.mbeans.ws.TruckInfoWSBean;
import com.tsystems.app.logisticscommon.MessageDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by ksenia on 01.11.2017.
 */

@MessageDriven(name = "MessageReceiverService", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jms/queue/logisticsQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class MessageReceiverService implements MessageListener {
    private static final Logger LOG = LogManager.getLogger(MessageReceiverService.class);

    @Inject
    private DriverInfoWSBean driverInfoWSBean;
    @Inject
    private GeneralInfoWSBean generalInfoWSBean;
    @Inject
    private OrderInfoWSBean orderInfoWSBean;
    @Inject
    private TruckInfoWSBean truckInfoWSBean;

    @Override
    public void onMessage(Message rcvMessage) {

        ObjectMessage msg = null;
        Object object = null;
        MessageDto messageDto = null;
        try {
            if (rcvMessage instanceof ObjectMessage) {

                msg = (ObjectMessage) rcvMessage;
                object = msg.getObject();
                if (object instanceof MessageDto) {
                    messageDto = (MessageDto) object;

                    switch (messageDto.getMessageType()) {
                        case GENERAL:
                            generalInfoWSBean.send(messageDto);
                            break;
                        case ADD_DRIVER:
                        case UPDATE_DRIVER:
                        case DELETE_DRIVER:
                            driverInfoWSBean.send(messageDto);
                            break;
                        case ADD_TRUCK:
                        case UPDATE_TRUCK:
                        case DELETE_TRUCK:
                            truckInfoWSBean.send(messageDto);
                            break;
                        case ADD_ORDER:
                        case UPDATE_ORDER:
                        case DELETE_ORDER:
                            orderInfoWSBean.send(messageDto);
                            break;
                    }
                }
            }
        } catch (JMSException ex) {
            LOG.error("Exception", ex);
            LOG.error("Message of wrong type {}", rcvMessage.getClass().getName());
        }
    }
}
