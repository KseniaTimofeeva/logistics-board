package com.tsystems.app.logisticsboard.mbeans.rest;

import com.tsystems.app.logisticsboard.mbeans.data.DriverInfoData;
import com.tsystems.app.logisticscommon.DriverInfoBoardDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ksenia on 15.11.2017.
 */
@ManagedBean(name = "driverInfoMBean")
@ViewScoped
public class DriverInfoMBean implements Serializable {
    private static final Logger LOG = LogManager.getLogger(DriverInfoMBean.class);

    @Inject
    private DriverInfoData driverInfoData;

    public List<DriverInfoBoardDto> getAllDriversInfo() {
        return driverInfoData.getAllDriversInfo();
    }

}
