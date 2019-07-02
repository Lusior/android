package com.longdian.fragment.dataanalysis.model;

import com.longdian.bean.BaseEntity;

import java.util.Date;

public class CollectData extends BaseEntity {
    /**
     * 基础数据表
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String standId;//分所ID
    private String stationId;//换热站ID
    private String groupId;//分所ID
    private Date opTime;//操作时间
    private Float pt1;//一网供压
    private Float pt2;//一网回压
    private Float te1;//一网供温
    private Float te2;//一网回温
    private Float cvi1;//调节阀开度
    private Float qi;//一网瞬时热量
    private Float ft1;//一网瞬时流量
    private Float ft1q;//调一网累积流量
    private Float pt3;//二网供压
    private Float pt4;//二网回压
    private Float te3;//二网回温
    private Float te4;//二网供水
    private Float ft2;//二供流量
    private Float ft3;//补水流量
    private Float fc1v1;//循环泵频率
    private Float lt1;//水箱液位
    private Float qqi;//累积热量
    private Float jqi;//累积电量
    private Float ft3q;//累积补水量
    private Float ft2q;//二网供水累积流量
    private Float ft3qPrice;//水耗单价
    private Float jqiPrice;//电耗单价
    private Float qqiPrice;//热耗单价
    private Integer batchNumber;//批次号，每次插入相同，累计


    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }


    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public Float getPt1() {
        return pt1;
    }

    public void setPt1(Float pt1) {
        this.pt1 = pt1;
    }

    public Float getPt2() {
        return pt2;
    }

    public void setPt2(Float pt2) {
        this.pt2 = pt2;
    }

    public Float getTe1() {
        return te1;
    }

    public void setTe1(Float te1) {
        this.te1 = te1;
    }

    public Float getTe2() {
        return te2;
    }

    public void setTe2(Float te2) {
        this.te2 = te2;
    }

    public Float getCvi1() {
        return cvi1;
    }

    public void setCvi1(Float cvi1) {
        this.cvi1 = cvi1;
    }

    public Float getQi() {
        return qi;
    }

    public void setQi(Float qi) {
        this.qi = qi;
    }

    public Float getFt1() {
        return ft1;
    }

    public void setFt1(Float ft1) {
        this.ft1 = ft1;
    }

    public Float getFt1q() {
        return ft1q;
    }

    public void setFt1q(Float ft1q) {
        this.ft1q = ft1q;
    }

    public Float getPt3() {
        return pt3;
    }

    public void setPt3(Float pt3) {
        this.pt3 = pt3;
    }

    public Float getPt4() {
        return pt4;
    }

    public void setPt4(Float pt4) {
        this.pt4 = pt4;
    }

    public Float getTe3() {
        return te3;
    }

    public void setTe3(Float te3) {
        this.te3 = te3;
    }

    public Float getTe4() {
        return te4;
    }

    public void setTe4(Float te4) {
        this.te4 = te4;
    }

    public Float getFt2() {
        return ft2;
    }

    public void setFt2(Float ft2) {
        this.ft2 = ft2;
    }

    public Float getFt3() {
        return ft3;
    }

    public void setFt3(Float ft3) {
        this.ft3 = ft3;
    }

    public Float getFc1v1() {
        return fc1v1;
    }

    public void setFc1v1(Float fc1v1) {
        this.fc1v1 = fc1v1;
    }

    public Float getLt1() {
        return lt1;
    }

    public void setLt1(Float lt1) {
        this.lt1 = lt1;
    }

    public Float getQqi() {
        return qqi;
    }

    public void setQqi(Float qqi) {
        this.qqi = qqi;
    }

    public Float getJqi() {
        return jqi;
    }

    public void setJqi(Float jqi) {
        this.jqi = jqi;
    }

    public Float getFt3q() {
        return ft3q;
    }

    public void setFt3q(Float ft3q) {
        this.ft3q = ft3q;
    }

    public Float getFt2q() {
        return ft2q;
    }

    public void setFt2q(Float ft2q) {
        this.ft2q = ft2q;
    }

    public Float getFt3qPrice() {
        return ft3qPrice;
    }

    public void setFt3qPrice(Float ft3qPrice) {
        this.ft3qPrice = ft3qPrice;
    }

    public Float getJqiPrice() {
        return jqiPrice;
    }

    public void setJqiPrice(Float jqiPrice) {
        this.jqiPrice = jqiPrice;
    }

    public Float getQqiPrice() {
        return qqiPrice;
    }

    public void setQqiPrice(Float qqiPrice) {
        this.qqiPrice = qqiPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(Integer batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CollectData [id=");
        builder.append(id);
        builder.append(", standId=");
        builder.append(standId);
        builder.append(", stationId=");
        builder.append(stationId);
        builder.append(", groupId=");
        builder.append(groupId);
        builder.append(", opTime=");
        builder.append(opTime);
        builder.append(", pt1=");
        builder.append(pt1);
        builder.append(", pt2=");
        builder.append(pt2);
        builder.append(", te1=");
        builder.append(te1);
        builder.append(", te2=");
        builder.append(te2);
        builder.append(", cvi1=");
        builder.append(cvi1);
        builder.append(", qi=");
        builder.append(qi);
        builder.append(", ft1=");
        builder.append(ft1);
        builder.append(", ft1q=");
        builder.append(ft1q);
        builder.append(", groupId=");
        builder.append(groupId);
        builder.append(", pt3=");
        builder.append(pt3);
        builder.append(", pt4=");
        builder.append(pt4);
        builder.append(", te3=");
        builder.append(te3);
        builder.append(", te4=");
        builder.append(te4);
        builder.append(", ft2=");
        builder.append(ft2);
        builder.append(", ft3=");
        builder.append(ft3);
        builder.append(", fc1v1=");
        builder.append(fc1v1);
        builder.append(", lt1=");
        builder.append(lt1);
        builder.append(", qqi=");
        builder.append(qqi);
        builder.append(", jqi=");
        builder.append(jqi);
        builder.append(", ft3q=");
        builder.append(ft3q);
        builder.append(", ft2q=");
        builder.append(ft2q);
        builder.append(", ft3qPrice=");
        builder.append(ft3qPrice);
        builder.append(", jqiPrice=");
        builder.append(jqiPrice);
        builder.append(", qqiPrice=");
        builder.append(qqiPrice);
        builder.append(", batchNumber=");
        builder.append(batchNumber);
        builder.append("]");
        return builder.toString();
    }
}
