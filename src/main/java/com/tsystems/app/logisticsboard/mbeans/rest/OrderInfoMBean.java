package com.tsystems.app.logisticsboard.mbeans.rest;

import com.tsystems.app.logisticsboard.mbeans.data.OrderInfoData;
import com.tsystems.app.logisticscommon.OrderInfoBoardDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ksenia on 15.11.2017.
 */
@ManagedBean(name = "orderInfoMBean")
@ViewScoped
public class OrderInfoMBean implements Serializable {

    private static final Logger LOG = LogManager.getLogger(DriverInfoMBean.class);

    @Inject
    private OrderInfoData orderInfoData;

    public List<OrderInfoBoardDto> getOrdersInfo() {
        return orderInfoData.getOrdersInfo();
    }

}
