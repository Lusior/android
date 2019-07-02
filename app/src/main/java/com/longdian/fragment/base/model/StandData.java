package com.longdian.fragment.base.model;

import com.longdian.bean.BaseEntity;

public class StandData extends BaseEntity {
    /**
     * 机组表
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String standId;//机组ID
    private String standName;//机组名称
    private String stationId;//换热站ID
    private Integer designArea;//设计面积
    private Integer realArea;//实际面积

    private String heatTransfer;//换热负荷；
    private String heatTransferArea;//换热面积；
    private String boardsOfNumber;//板换数量；
    private String circulatoryPumpLift;//循环泵扬程；
    private String circulatoryPumpPower;//循环泵功率；
    private String circulatoryPumpNumber;//循环泵数量；
    private String fillingPumpFlow;//补水泵流量；
    private String pumpPower;//补水泵功率；
    private String quantityOfPump;//补水泵数量；

    public String getHeatTransfer() {
        return heatTransfer;
    }

    public void setHeatTransfer(String heatTransfer) {
        this.heatTransfer = heatTransfer;
    }

    public String getHeatTransferArea() {
        return heatTransferArea;
    }

    public void setHeatTransferArea(String heatTransferArea) {
        this.heatTransferArea = heatTransferArea;
    }

    public String getBoardsOfNumber() {
        return boardsOfNumber;
    }

    public void setBoardsOfNumber(String boardsOfNumber) {
        this.boardsOfNumber = boardsOfNumber;
    }

    public String getCirculatoryPumpLift() {
        return circulatoryPumpLift;
    }

    public void setCirculatoryPumpLift(String circulatoryPumpLift) {
        this.circulatoryPumpLift = circulatoryPumpLift;
    }

    public String getCirculatoryPumpPower() {
        return circulatoryPumpPower;
    }

    public void setCirculatoryPumpPower(String circulatoryPumpPower) {
        this.circulatoryPumpPower = circulatoryPumpPower;
    }

    public String getCirculatoryPumpNumber() {
        return circulatoryPumpNumber;
    }

    public void setCirculatoryPumpNumber(String circulatoryPumpNumber) {
        this.circulatoryPumpNumber = circulatoryPumpNumber;
    }

    public String getFillingPumpFlow() {
        return fillingPumpFlow;
    }

    public void setFillingPumpFlow(String fillingPumpFlow) {
        this.fillingPumpFlow = fillingPumpFlow;
    }

    public String getPumpPower() {
        return pumpPower;
    }

    public void setPumpPower(String pumpPower) {
        this.pumpPower = pumpPower;
    }

    public String getQuantityOfPump() {
        return quantityOfPump;
    }

    public void setQuantityOfPump(String quantityOfPump) {
        this.quantityOfPump = quantityOfPump;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }

    public String getStandName() {
        return standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
    }


    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Integer getDesignArea() {
        return designArea;
    }

    public void setDesignArea(Integer designArea) {
        this.designArea = designArea;
    }

    public Integer getRealArea() {
        return realArea;
    }

    public void setRealArea(Integer realArea) {
        this.realArea = realArea;
    }


    public void setDataFromExtra(StandDataExtra extra) {
        if (extra != null) {
            this.heatTransfer = extra.getHeatTransfer();
            this.heatTransferArea = extra.getHeatTransferArea();
            this.boardsOfNumber = extra.getBoardsOfNumber();
            this.circulatoryPumpLift = extra.getCirculatoryPumpLift();
            this.circulatoryPumpNumber = extra.getCirculatoryPumpNumber();
            this.circulatoryPumpPower = extra.getCirculatoryPumpPower();
            this.fillingPumpFlow = extra.getFillingPumpFlow();
            this.pumpPower = extra.getPumpPower();
            this.quantityOfPump = extra.getQuantityOfPump();
        }
    }

    @Override
    public String toString() {
        return "StandData{" +
                "id=" + id +
                ", standId='" + standId + '\'' +
                ", standName='" + standName + '\'' +
                ", stationId='" + stationId + '\'' +
                ", designArea=" + designArea +
                ", realArea=" + realArea +
                ", heatTransfer='" + heatTransfer + '\'' +
                ", heatTransferArea='" + heatTransferArea + '\'' +
                ", boardsOfNumber='" + boardsOfNumber + '\'' +
                ", circulatoryPumpLift='" + circulatoryPumpLift + '\'' +
                ", circulatoryPumpPower='" + circulatoryPumpPower + '\'' +
                ", circulatoryPumpNumber='" + circulatoryPumpNumber + '\'' +
                ", fillingPumpFlow='" + fillingPumpFlow + '\'' +
                ", pumpPower='" + pumpPower + '\'' +
                ", quantityOfPump='" + quantityOfPump + '\'' +
                '}';
    }

    //    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("StandData [id=");
//        builder.append(id);
//        builder.append(", standId=");
//        builder.append(standId);
//        builder.append(", standName=");
//        builder.append(standName);
//        builder.append(", StationId=");
//        builder.append(stationId);
//        builder.append(", designArea=");
//        builder.append(designArea);
//        builder.append(", realArea=");
//        builder.append(realArea);
//        builder.append("]");
//        return builder.toString();
//    }
}
