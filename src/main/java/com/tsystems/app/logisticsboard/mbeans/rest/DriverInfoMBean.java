package com.tsystems.app.logisticsboard.mbeans.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.app.logisticscommon.DriverInfoBoardDto;
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
import java.util.List;
import java.util.ListIterator;

/**
 * Created by ksenia on 15.11.2017.
 */
@ManagedBean(name = "driverInfoMBean")
@SessionScoped
@Singleton
public class DriverInfoMBean implements Serializable {
    private static final Logger LOG = LogManager.getLogger(GeneralInfoMBean.class);

    private List<DriverInfoBoardDto> allDriversInfo;

    @PostConstruct
    public void init() {
        allDriversInfo = setData();
    }

    public List<DriverInfoBoardDto> getAllDriversInfo() {
        return allDriversInfo;
    }


    public void changeData(MessageDto messageDto) {
        switch (messageDto.getMessageType()) {
            case ADD_DRIVER:
                add((DriverInfoBoardDto) messageDto.getChange());
                break;
            case UPDATE_DRIVER:
                update((DriverInfoBoardDto) messageDto.getChange());
                break;
            case DELETE_DRIVER:
                remove((Long) messageDto.getChange());
                break;
        }
    }

    private List<DriverInfoBoardDto> setData() {
        List<DriverInfoBoardDto> driverInfoBoardDtoList = null;
        try {
            driverInfoBoardDtoList = new ObjectMapper()
                    .readValue(new URL("http://localhost:8080/logistics/rest/drivers-info"), new TypeReference<List<DriverInfoBoardDto>>() {
                    });
        } catch (IOException e) {
            LOG.error("Getting data from server error");
        }
        return driverInfoBoardDtoList;
    }

    private void add(DriverInfoBoardDto dto) {
        allDriversInfo.add(dto);
    }

    private void update(DriverInfoBoardDto dto) {
        ListIterator<DriverInfoBoardDto> iter = allDriversInfo.listIterator();
        while (iter.hasNext()) {
            DriverInfoBoardDto next = iter.next();
            if (next.getId().equals(dto.getId())) {
                iter.set(dto);
            }
        }
    }

    private void remove(Long id) {
        allDriversInfo.removeIf(dto -> dto.getId().equals(id));
    }
}
