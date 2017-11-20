package com.tsystems.app.logisticsboard.mbeans.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.app.logisticsboard.mbeans.rest.GeneralInfoMBean;
import com.tsystems.app.logisticscommon.GeneralInfoDto;
import com.tsystems.app.logisticscommon.MessageDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.io.IOException;
import java.net.URL;

/**
 * Created by ksenia on 19.11.2017.
 */
@Singleton(name = "generalInfoData")
public class GeneralInfoData {
    private static final Logger LOG = LogManager.getLogger(GeneralInfoData.class);

    private GeneralInfoDto generalInfo;

    @PostConstruct
    public void init() {
        generalInfo = setData();
    }

    public GeneralInfoDto getGeneralInfo() {
        return generalInfo;
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
}
