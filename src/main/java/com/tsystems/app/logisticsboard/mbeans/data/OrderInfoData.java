package com.tsystems.app.logisticsboard.mbeans.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.app.logisticscommon.MessageDto;
import com.tsystems.app.logisticscommon.OrderInfoBoardDto;
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
@Singleton(name = "orderInfoData")
public class OrderInfoData {
    private static final Logger LOG = LogManager.getLogger(DriverInfoData.class);

    private List<OrderInfoBoardDto> ordersInfo;

    public List<OrderInfoBoardDto> getOrdersInfo() {
        return ordersInfo;
    }

    @PostConstruct
    public void init() {
        ordersInfo = setData();
    }

    private List<OrderInfoBoardDto> setData() {
        List<OrderInfoBoardDto> dtoList = null;
        try {
            dtoList = new ObjectMapper()
                    .readValue(new URL("http://localhost:8080/logistics/rest/orders-info"), new TypeReference<List<OrderInfoBoardDto>>() {
                    });
        } catch (IOException e) {
            LOG.error("Getting data from server error");
        }
        return dtoList;
    }

    public void changeData(MessageDto messageDto) {
        switch (messageDto.getMessageType()) {
            case ADD_ORDER:
                add((OrderInfoBoardDto) messageDto.getChange());
                break;
            case UPDATE_ORDER:
                update((OrderInfoBoardDto) messageDto.getChange());
                break;
            case DELETE_ORDER:
                remove((Long) messageDto.getChange());
                break;
        }
    }

    private void add(OrderInfoBoardDto dto) {
        ordersInfo.add(dto);
    }

    private void update(OrderInfoBoardDto dto) {
        ListIterator<OrderInfoBoardDto> iter = ordersInfo.listIterator();
        while (iter.hasNext()) {
            OrderInfoBoardDto next = iter.next();
            if (next.getId().equals(dto.getId())) {
                iter.set(dto);
            }
        }
    }

    private void remove(Long id) {
        ordersInfo.removeIf(dto -> dto.getId().equals(id));
    }

}
