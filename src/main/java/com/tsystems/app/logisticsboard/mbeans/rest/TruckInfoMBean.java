package com.tsystems.app.logisticsboard.mbeans.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.app.logisticsboard.mbeans.data.TruckInfoData;
import com.tsystems.app.logisticscommon.MessageDto;
import com.tsystems.app.logisticscommon.TruckFullDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by ksenia on 15.11.2017.
 */
@ManagedBean(name = "truckInfoMBean")
@ViewScoped
public class TruckInfoMBean implements Serializable {
    private static final Logger LOG = LogManager.getLogger(TruckInfoMBean.class);

    @Inject
    private TruckInfoData truckInfoData;

    public List<TruckFullDto> getAllTrucksInfo() {
        return truckInfoData.getAllTrucksInfo();
    }
}
