package com.scut.mall.entity;

/**
 * @Author: cookie
 * @Description:
 */
public class ReturnOrder {
    private String months;

    private int ordernum;

    private double total;

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
