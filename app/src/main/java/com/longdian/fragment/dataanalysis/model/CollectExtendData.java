package com.longdian.fragment.dataanalysis.model;

public class CollectExtendData extends CollectData {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String standName;

    private String stationName;

    private Float pt3_pt4;

    private String opTimeStr;//格式化后的日期

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStandName() {
        return standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
    }

    public Float getPt3_pt4() {
        return pt3_pt4;
    }

    public void setPt3_pt4(Float pt3_pt4) {
        this.pt3_pt4 = pt3_pt4;
    }

    public String getOpTimeStr() {
        return opTimeStr;
    }

    public void setOpTimeStr(String opTimeStr) {
        this.opTimeStr = opTimeStr;
    }

}
