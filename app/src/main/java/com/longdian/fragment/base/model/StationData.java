package com.longdian.fragment.base.model;


import com.longdian.bean.BaseEntity;

public class StationData extends BaseEntity {
    /**
     * 换热站
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String stationId;//换热站ID
    private String stationName;//换热站名称
    private String groupId;//分所ID
    private String personnel;//负责人
    private String telephone;//电话

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StationData [id=");
        builder.append(id);
        builder.append(", stationId=");
        builder.append(stationId);
        builder.append(", stationName=");
        builder.append(stationName);
        builder.append(", groupId=");
        builder.append(groupId);
        builder.append(", personnel=");
        builder.append(personnel);
        builder.append(", telephone=");
        builder.append(telephone);
        builder.append("]");
        return builder.toString();
    }
}
