package com.longdian.fragment.base.model;

/**
 * Created by fanwei on 2019/2/28.
 */

public class StandDataExtra {
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

    public StandDataExtra setHeatTransfer(String heatTransfer) {
        this.heatTransfer = heatTransfer;
        return this;
    }

    public String getHeatTransferArea() {
        return heatTransferArea;
    }

    public StandDataExtra setHeatTransferArea(String heatTransferArea) {
        this.heatTransferArea = heatTransferArea;
        return this;
    }

    public String getBoardsOfNumber() {
        return boardsOfNumber;
    }

    public StandDataExtra setBoardsOfNumber(String boardsOfNumber) {
        this.boardsOfNumber = boardsOfNumber;
        return this;
    }

    public String getCirculatoryPumpLift() {
        return circulatoryPumpLift;
    }

    public StandDataExtra setCirculatoryPumpLift(String circulatoryPumpLift) {
        this.circulatoryPumpLift = circulatoryPumpLift;
        return this;
    }

    public String getCirculatoryPumpPower() {
        return circulatoryPumpPower;
    }

    public StandDataExtra setCirculatoryPumpPower(String circulatoryPumpPower) {
        this.circulatoryPumpPower = circulatoryPumpPower;
        return this;
    }

    public String getCirculatoryPumpNumber() {
        return circulatoryPumpNumber;
    }

    public StandDataExtra setCirculatoryPumpNumber(String circulatoryPumpNumber) {
        this.circulatoryPumpNumber = circulatoryPumpNumber;
        return this;
    }

    public String getFillingPumpFlow() {
        return fillingPumpFlow;
    }

    public StandDataExtra setFillingPumpFlow(String fillingPumpFlow) {
        this.fillingPumpFlow = fillingPumpFlow;
        return this;
    }

    public String getPumpPower() {
        return pumpPower;
    }

    public StandDataExtra setPumpPower(String pumpPower) {
        this.pumpPower = pumpPower;
        return this;
    }

    public String getQuantityOfPump() {
        return quantityOfPump;
    }

    public StandDataExtra setQuantityOfPump(String quantityOfPump) {
        this.quantityOfPump = quantityOfPump;
        return this;
    }
}
