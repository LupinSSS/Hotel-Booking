package com.example.hotel_booking.common.domain;

import java.util.Date;

public class Orders extends OrdersKey {
    private Date checkind;

    private Date checkoutd;

    private Integer number;

    private Integer price;

    private Integer payed;

    private Date ordertime;

    private String remark;

    private Integer finished;

    public Date getCheckind() {
        return checkind;
    }

    public void setCheckind(Date checkind) {
        this.checkind = checkind;
    }

    public Date getCheckoutd() {
        return checkoutd;
    }

    public void setCheckoutd(Date checkoutd) {
        this.checkoutd = checkoutd;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPayed() {
        return payed;
    }

    public void setPayed(Integer payed) {
        this.payed = payed;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }
}