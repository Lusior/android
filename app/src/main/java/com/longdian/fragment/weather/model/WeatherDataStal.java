package com.longdian.fragment.weather.model;

import com.longdian.bean.BaseEntity;

import java.math.BigDecimal;

public class WeatherDataStal extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String staId;
    private String day;
    private String hour;
    private BigDecimal prs;
    private BigDecimal prsSea;
    private BigDecimal prsMax;
    private BigDecimal prsMin;
    private BigDecimal tem;
    private BigDecimal temMax;
    private BigDecimal temMin;
    private BigDecimal rhu;
    private BigDecimal rhuMin;
    private BigDecimal vap;
    private BigDecimal pre1h;
    private BigDecimal winDInstMax;
    private BigDecimal winSMax;
    private BigDecimal winDSMax;
    private BigDecimal winSAvg10Mi;
    private BigDecimal winDAvg10mi;
    private BigDecimal winSInstMax;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaId() {
        return staId;
    }

    public void setStaId(String staId) {
        this.staId = staId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public BigDecimal getPrs() {
        return prs;
    }

    public void setPrs(BigDecimal prs) {
        this.prs = prs;
    }

    public BigDecimal getPrsSea() {
        return prsSea;
    }

    public void setPrsSea(BigDecimal prsSea) {
        this.prsSea = prsSea;
    }

    public BigDecimal getPrsMax() {
        return prsMax;
    }

    public void setPrsMax(BigDecimal prsMax) {
        this.prsMax = prsMax;
    }

    public BigDecimal getPrsMin() {
        return prsMin;
    }

    public void setPrsMin(BigDecimal prsMin) {
        this.prsMin = prsMin;
    }

    public BigDecimal getTem() {
        return tem;
    }

    public void setTem(BigDecimal tem) {
        this.tem = tem;
    }

    public BigDecimal getTemMax() {
        return temMax;
    }

    public void setTemMax(BigDecimal temMax) {
        this.temMax = temMax;
    }

    public BigDecimal getTemMin() {
        return temMin;
    }

    public void setTemMin(BigDecimal temMin) {
        this.temMin = temMin;
    }

    public BigDecimal getRhu() {
        return rhu;
    }

    public void setRhu(BigDecimal rhu) {
        this.rhu = rhu;
    }

    public BigDecimal getRhuMin() {
        return rhuMin;
    }

    public void setRhuMin(BigDecimal rhuMin) {
        this.rhuMin = rhuMin;
    }

    public BigDecimal getVap() {
        return vap;
    }

    public void setVap(BigDecimal vap) {
        this.vap = vap;
    }

    public BigDecimal getPre1h() {
        return pre1h;
    }

    public void setPre1h(BigDecimal pre1h) {
        this.pre1h = pre1h;
    }

    public BigDecimal getWinDInstMax() {
        return winDInstMax;
    }

    public void setWinDInstMax(BigDecimal winDInstMax) {
        this.winDInstMax = winDInstMax;
    }

    public BigDecimal getWinSMax() {
        return winSMax;
    }

    public void setWinSMax(BigDecimal winSMax) {
        this.winSMax = winSMax;
    }

    public BigDecimal getWinDSMax() {
        return winDSMax;
    }

    public void setWinDSMax(BigDecimal winDSMax) {
        this.winDSMax = winDSMax;
    }

    public BigDecimal getWinSAvg10Mi() {
        return winSAvg10Mi;
    }

    public void setWinSAvg10Mi(BigDecimal winSAvg10Mi) {
        this.winSAvg10Mi = winSAvg10Mi;
    }

    public BigDecimal getWinDAvg10mi() {
        return winDAvg10mi;
    }

    public void setWinDAvg10mi(BigDecimal winDAvg10mi) {
        this.winDAvg10mi = winDAvg10mi;
    }

    public BigDecimal getWinSInstMax() {
        return winSInstMax;
    }

    public void setWinSInstMax(BigDecimal winSInstMax) {
        this.winSInstMax = winSInstMax;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WeatherDataStal [id=");
        builder.append(id);
        builder.append(", StaId=");
        builder.append(staId);
        builder.append(", day=");
        builder.append(day);
        builder.append(", hour=");
        builder.append(hour);
        builder.append(", prs=");
        builder.append(prs);
        builder.append(", prsSea=");
        builder.append(prsSea);
        builder.append(", prsMax=");
        builder.append(prsMax);
        builder.append(", prsMin=");
        builder.append(prsMin);
        builder.append(", tem=");
        builder.append(tem);
        builder.append(", temMax=");
        builder.append(temMax);
        builder.append(", temMin=");
        builder.append(temMin);
        builder.append(", rhu=");
        builder.append(rhu);
        builder.append(", rhuMin=");
        builder.append(rhuMin);
        builder.append(", vap=");
        builder.append(vap);
        builder.append(", pre1h=");
        builder.append(pre1h);
        builder.append(", winDInstMax=");
        builder.append(winDInstMax);
        builder.append(", winSMax=");
        builder.append(winSMax);
        builder.append(", winDSMax=");
        builder.append(winDSMax);
        builder.append(", winSAvg10Mi=");
        builder.append(winSAvg10Mi);
        builder.append(", winDAvg10mi=");
        builder.append(winDAvg10mi);
        builder.append(", winSInstMax=");
        builder.append(winSInstMax);
        builder.append("]");
        return builder.toString();
    }
}
