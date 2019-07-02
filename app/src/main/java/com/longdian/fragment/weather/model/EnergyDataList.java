package com.longdian.fragment.weather.model;

public class EnergyDataList {
    private static final long serialVersionUID = 1L;

    private String stationId;
    private String stationName;
    private String standId;
    private String ft3q;
    private String jqi;
    private String qqi;
    private String money;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }

    public String getFt3q() {
        return ft3q;
    }

    public void setFt3q(String ft3q) {
        this.ft3q = ft3q;
    }

    public String getJqi() {
        return jqi;
    }

    public void setJqi(String jqi) {
        this.jqi = jqi;
    }

    public String getQqi() {
        return qqi;
    }

    public void setQqi(String qqi) {
        this.qqi = qqi;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "EnergyDataList{" +
                "stationId='" + stationId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", standId='" + standId + '\'' +
                ", ft3q='" + ft3q + '\'' +
                ", jqi='" + jqi + '\'' +
                ", qqi='" + qqi + '\'' +
                '}';
    }
}
