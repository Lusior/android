package com.longdian.fragment.weather.model;

import com.longdian.bean.BaseEntity;

public class WeatherData extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String day;
    private Float teL;
    private Float teH;
    private Float teA;
    private String teWd1;
    private String teWd2;
    private String teWe1;
    private String teWe2;
    private String teC1;
    private String teC2;
    private String teI1;
    private String teI2;
    private String hour3Data;
    private String cityNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Float getTeL() {
        return teL;
    }

    public void setTeL(Float teL) {
        this.teL = teL;
    }

    public Float getTeH() {
        return teH;
    }

    public void setTeH(Float teH) {
        this.teH = teH;
    }

    public String getTeWd1() {
        return teWd1;
    }

    public void setTeWd1(String teWd1) {
        this.teWd1 = teWd1;
    }

    public String getTeWd2() {
        return teWd2;
    }

    public void setTeWd2(String teWd2) {
        this.teWd2 = teWd2;
    }

    public String getTeWe1() {
        return teWe1;
    }

    public void setTeWe1(String teWe1) {
        this.teWe1 = teWe1;
    }

    public String getTeWe2() {
        return teWe2;
    }

    public void setTeWe2(String teWe2) {
        this.teWe2 = teWe2;
    }

    public String getTeC1() {
        return teC1;
    }

    public void setTeC1(String teC1) {
        this.teC1 = teC1;
    }

    public String getTeC2() {
        return teC2;
    }

    public void setTeC2(String teC2) {
        this.teC2 = teC2;
    }

    public String getTeI1() {
        return teI1;
    }

    public void setTeI1(String teI1) {
        this.teI1 = teI1;
    }

    public String getTeI2() {
        return teI2;
    }

    public void setTeI2(String teI2) {
        this.teI2 = teI2;
    }

    public String getHour3Data() {
        return hour3Data;
    }

    public void setHour3Data(String hour3Data) {
        this.hour3Data = hour3Data;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public Float getTeA() {
        return teA;
    }

    public void setTeA(Float teA) {
        this.teA = teA;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WeatherData [id=");
        builder.append(id);
        builder.append(", day=");
        builder.append(day);
        builder.append(", rtmTe=");
        builder.append(", teL=");
        builder.append(teL);
        builder.append(", teH=");
        builder.append(teH);
        builder.append(", teWd1=");
        builder.append(teWd1);
        builder.append(", teWd2=");
        builder.append(teWd2);
        builder.append(", teWe1=");
        builder.append(teWe1);
        builder.append(", teWe2=");
        builder.append(teWe2);
        builder.append(", teC1=");
        builder.append(teC1);
        builder.append(", teC2=");
        builder.append(teC2);
        builder.append(", teI1=");
        builder.append(teI1);
        builder.append(", teI2=");
        builder.append(teI2);
        builder.append(", hour3Data=");
        builder.append(hour3Data);
        builder.append(", cityNo=");
        builder.append(cityNo);
        builder.append("]");
        return builder.toString();
    }
}
