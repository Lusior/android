package com.longdian.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name="供热信息")
public class HotInfo {

    public HotInfo(String value, String price) {
        this.value = value;
        this.price = price;
    }

    @SmartColumn(id =1,name = "数值")
    private String value;
    @SmartColumn(id =1,name = "单价")
    private String price;
}
