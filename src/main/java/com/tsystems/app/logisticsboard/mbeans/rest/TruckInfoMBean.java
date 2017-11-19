package com.tsystems.app.logisticsboard.mbeans.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.app.logisticscommon.MessageDto;
import com.tsystems.app.logisticscommon.TruckFullDto;
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
@ManagedBean(name = "truckInfoMBean")
@SessionScoped
@Singleton
public class TruckInfoMBean implements Serializable {
    private static final Logger LOG = LogManager.getLogger(GeneralInfoMBean.class);

    private List<TruckFullDto> allTrucksInfo;

    @PostConstruct
    public void init() {
        allTrucksInfo = setData();
    }

    public List<TruckFullDto> getAllTrucksInfo() {
        return allTrucksInfo;
    }

    public void changeData(MessageDto messageDto) {
        switch (messageDto.getMessageType()) {
            case ADD_TRUCK:
                add((TruckFullDto) (messageDto.getChange()));
                break;
            case UPDATE_TRUCK:
                update((TruckFullDto) (messageDto.getChange()));
                break;
            case DELETE_TRUCK:
                remove((Long) (messageDto.getChange()));
                break;
        }
    }

    private void add(TruckFullDto dto) {
        allTrucksInfo.add(dto);
    }

    private void update(TruckFullDto dto) {
        ListIterator<TruckFullDto> iter = allTrucksInfo.listIterator();
        while (iter.hasNext()) {
            TruckFullDto next = iter.next();
            if (next.getId().equals(dto.getId())) {
                iter.set(dto);
            }
        }
    }

    private void remove(Long id) {
        allTrucksInfo.removeIf(dto -> dto.getId().equals(id));
    }

    private List<TruckFullDto> setData() {
        List<TruckFullDto> truckInfoDtoList = null;
        try {
            truckInfoDtoList = new ObjectMapper()
                    .readValue(new URL("http://localhost:8080/logistics/rest/trucks-info"), new TypeReference<List<TruckFullDto>>() {
                    });
        } catch (IOException e) {
            LOG.error("Getting data from server error");
        }
        return truckInfoDtoList;
    }
}
