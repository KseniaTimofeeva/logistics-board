package com.tsystems.app.logisticsboard.mbeans.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.app.logisticscommon.MessageDto;
import com.tsystems.app.logisticscommon.TruckFullDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by ksenia on 19.11.2017.
 */
@Singleton(name = "truckInfoData")
public class TruckInfoData {
    private static final Logger LOG = LogManager.getLogger(TruckInfoData.class);

    private List<TruckFullDto> allTrucksInfo;

    @PostConstruct
    public void init() {
        allTrucksInfo = setData();
    }

    public List<TruckFullDto> getAllTrucksInfo() {
        return allTrucksInfo;
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

}
