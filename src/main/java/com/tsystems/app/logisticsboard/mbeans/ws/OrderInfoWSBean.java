package com.tsystems.app.logisticsboard.mbeans.ws;

import com.tsystems.app.logisticsboard.enums.ChannelWS;
import com.tsystems.app.logisticsboard.mbeans.data.OrderInfoData;
import com.tsystems.app.logisticscommon.MessageDto;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by ksenia on 17.11.2017.
 */
@ManagedBean(name = "orderInfoWSBean")
@RequestScoped
public class OrderInfoWSBean implements Serializable{

    private static final String CHANNEL = ChannelWS.ORDER_INFO.getName();
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
    @Inject
    private OrderInfoData orderInfoData;

    public void send(MessageDto message) {
        orderInfoData.changeData(message);
        eventBus.publish(CHANNEL, message);
    }



}
