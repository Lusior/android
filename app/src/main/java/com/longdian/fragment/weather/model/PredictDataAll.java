package com.longdian.fragment.weather.model;

import java.util.List;

public class PredictDataAll {

    private PredictData baseData;
    private PredictHistoryData targetData;
    private String yesQ3;
    private List<PredictStation> predictStationList;
    private List<PredictStation> predictStationListLast;

    public PredictData getBaseData() {
        return baseData;
    }

    public void setBaseData(PredictData baseData) {
        this.baseData = baseData;
    }

    public PredictHistoryData getTargetData() {
        return targetData;
    }

    public void setTargetData(PredictHistoryData targetData) {
        this.targetData = targetData;
    }

    public String getYesQ3() {
        return yesQ3;
    }

    public void setYesQ3(String yesQ3) {
        this.yesQ3 = yesQ3;
    }

    public List<PredictStation> getPredictStationList() {
        return predictStationList;
    }

    public void setPredictStationList(List<PredictStation> predictStationList) {
        this.predictStationList = predictStationList;
    }

    public List<PredictStation> getPredictStationListLast() {
        return predictStationListLast;
    }

    public void setPredictStationListLast(List<PredictStation> predictStationListLast) {
        this.predictStationListLast = predictStationListLast;
    }
}
