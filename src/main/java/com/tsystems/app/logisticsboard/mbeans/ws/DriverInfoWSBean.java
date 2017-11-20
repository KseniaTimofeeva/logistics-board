package com.tsystems.app.logisticsboard.mbeans.ws;

import com.tsystems.app.logisticsboard.enums.ChannelWS;
import com.tsystems.app.logisticsboard.mbeans.data.DriverInfoData;
import com.tsystems.app.logisticscommon.MessageDto;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by ksenia on 17.11.2017.
 */
@ManagedBean(name = "driverInfoWSBean")
@RequestScoped
public class DriverInfoWSBean implements Serializable {

    private static final String CHANNEL = ChannelWS.DRIVER_INFO.getName();
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
    @Inject
    private DriverInfoData driverInfoData;

    public void send(MessageDto message) {
        driverInfoData.changeData(message);
        eventBus.publish(CHANNEL, message);
    }



}
