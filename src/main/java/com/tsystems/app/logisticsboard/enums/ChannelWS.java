package com.tsystems.app.logisticsboard.enums;

/**
 * Created by ksenia on 17.11.2017.
 */
public enum  ChannelWS {
    GENERAL_INFO("/generalInfo"),
    DRIVER_INFO("/driverInfo"),
    TRUCK_INFO("/truckInfo"),
    ORDER_INFO("/orderInfo");

    private final String name;

    ChannelWS(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
