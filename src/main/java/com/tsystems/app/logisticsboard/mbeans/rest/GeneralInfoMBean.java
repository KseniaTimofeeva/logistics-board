package com.tsystems.app.logisticsboard.mbeans.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.app.logisticsboard.mbeans.data.GeneralInfoData;
import com.tsystems.app.logisticscommon.GeneralInfoDto;
import com.tsystems.app.logisticscommon.MessageDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Created by ksenia on 11.11.2017.
 */
@ManagedBean(name = "generalInfoMBean")
@ViewScoped
public class GeneralInfoMBean implements Serializable {
    private static final Logger LOG = LogManager.getLogger(GeneralInfoMBean.class);

    @Inject
    private GeneralInfoData generalInfoData;

    public List<GeneralInfoDto> getGeneralInfo() {
        return Collections.singletonList(generalInfoData.getGeneralInfo());
    }
}
