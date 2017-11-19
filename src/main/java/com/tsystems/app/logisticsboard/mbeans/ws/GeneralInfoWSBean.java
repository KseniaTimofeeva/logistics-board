package com.tsystems.app.logisticsboard.mbeans.ws;

import com.tsystems.app.logisticsboard.enums.ChannelWS;
import com.tsystems.app.logisticsboard.mbeans.rest.GeneralInfoMBean;
import com.tsystems.app.logisticscommon.MessageDto;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.primefaces.push.impl.JSONEncoder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by ksenia on 16.11.2017.
 */
@ManagedBean(name = "generalInfoWSBean")
@RequestScoped
public class GeneralInfoWSBean implements Serializable{

    private static final String CHANNEL = ChannelWS.GENERAL_INFO.getName();
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
    @Inject
    private GeneralInfoMBean generalInfoMBean;

    public void send(MessageDto message) {
        generalInfoMBean.changeData(message);
        eventBus.publish(CHANNEL, message);
    }



}
