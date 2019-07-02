package com.longdian.fragment.base.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoenix on 2017/6/10.
 */

public class StationList {

    private List<StationData> stationDataList;
    private List<StandBasicData> standBasicDataList;
    private PriceData priceData;

    public List<StandBasicData> getById(String stationId) {
        List<StandBasicData> result = new ArrayList<>();
        for (StandBasicData data : standBasicDataList) {
            if (data.getStationId().equals(stationId)) {
                result.add(data);
            }
        }
        return result;
    }

    public List<StationData> getStationDataList() {
        return stationDataList;
    }

    public void setStationDataList(List<StationData> stationDataList) {
        this.stationDataList = stationDataList;
    }

    public List<StandBasicData> getStandBasicDataList() {
        return standBasicDataList;
    }

    public void setStandBasicDataList(List<StandBasicData> standBasicDataList) {
        this.standBasicDataList = standBasicDataList;
    }

    public PriceData getPriceData() {
        return priceData;
    }

    public void setPriceData(PriceData priceData) {
        this.priceData = priceData;
    }

    @Override
    public String toString() {
        return "StationList{" +
                "stationDataList=" + stationDataList +
                ", standBasicDataList=" + standBasicDataList +
                ", priceData=" + priceData +
                '}';
    }
}
