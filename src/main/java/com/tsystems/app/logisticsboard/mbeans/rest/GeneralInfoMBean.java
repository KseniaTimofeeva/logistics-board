package com.tsystems.app.logisticsboard.mbeans.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.app.logisticscommon.GeneralInfoDto;
import com.tsystems.app.logisticscommon.MessageDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.push.annotation.Singleton;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ksenia on 11.11.2017.
 */
@ManagedBean(name = "generalInfoMBean")
@SessionScoped
@Singleton
public class GeneralInfoMBean implements Serializable {
    private static final Logger LOG = LogManager.getLogger(GeneralInfoMBean.class);

    private GeneralInfoDto generalInfo;

    @PostConstruct
    public void init() {
        generalInfo = setData();
    }

    public List<GeneralInfoDto> getGeneralInfo() {
        return Collections.singletonList(generalInfo);
    }

    public void changeData(MessageDto messageDto) {
        GeneralInfoDto change = (GeneralInfoDto) (messageDto.getChange());
        if (change.getDriverQty() != null) {
            generalInfo.setDriverQty(change.getDriverQty());
        }
        if (change.getVacantDriverQty() != null) {
            generalInfo.setVacantDriverQty(change.getVacantDriverQty());
        }
        if (change.getNotAvailableDriverQty() != null) {
            generalInfo.setNotAvailableDriverQty(change.getNotAvailableDriverQty());
        }
        if (change.getTruckQty() != null) {
            generalInfo.setTruckQty(change.getTruckQty());
        }
        if (change.getVacantTruckQty() != null) {
            generalInfo.setVacantTruckQty(change.getVacantTruckQty());
        }
        if (change.getOnOrderTruckQty() != null) {
            generalInfo.setOnOrderTruckQty(change.getOnOrderTruckQty());
        }
        if (change.getNotWorkingTruckQty() != null) {
            generalInfo.setNotWorkingTruckQty(change.getNotWorkingTruckQty());
        }
    }

    private GeneralInfoDto setData() {
        GeneralInfoDto generalInfoDto = null;
        try {
            generalInfoDto = new ObjectMapper()
                    .readValue(new URL("http://localhost:8080/logistics/rest/general-info"), GeneralInfoDto.class);
        } catch (IOException e) {
            LOG.error("Getting data from server error");
        }
        return generalInfoDto;
    }


}
