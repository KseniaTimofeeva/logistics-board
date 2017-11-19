package com.tsystems.app.logisticsboard.mbeans.ws;

import com.tsystems.app.logisticscommon.MessageDto;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.primefaces.push.impl.JSONEncoder;

import java.io.Serializable;

/**
 * Created by ksenia on 19.11.2017.
 */
@PushEndpoint("/generalInfo")
@Singleton
public class GeneralInfoEndpoint  {

    @OnMessage(encoders = {JSONEncoder.class})
    public MessageDto onMessage(MessageDto message) {
        return message;
    }
}
