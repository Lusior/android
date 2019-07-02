package com.longdian.service;

/**
 * Created by Administrator on 2017/3/27.
 */

public class HoolaiResponse<T> {

    private int code;
    private String desc;
    private String group;
    private T value;

    public boolean isSuccess() {
        return code == 0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
