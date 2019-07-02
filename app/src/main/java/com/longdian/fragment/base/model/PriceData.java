package com.longdian.fragment.base.model;

import com.longdian.bean.BaseEntity;

public class PriceData extends BaseEntity {
    /**
     * 价格
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Float ft3qPrice;//水耗单价
    private Float jqiPrice;//电耗单价
    private Float qqiPrice;//热耗单价

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PriceData [id=");
        builder.append(id);
        builder.append(", ft3qPrice=");
        builder.append(ft3qPrice);
        builder.append(", jqiPrice=");
        builder.append(jqiPrice);
        builder.append(", qqiPrice=");
        builder.append(qqiPrice);
        builder.append("]");
        return builder.toString();
    }
}
