package com.xay.Domain;

import com.xay.MySQL.DO.OrderDO;

import java.sql.Timestamp;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public class OrderDomain {
    private Integer orderId;
    private String cUsername;
    private String gUsername;
    private Integer price;
    private String status;
    private Timestamp createTime;
    private Timestamp payTime;
    private Timestamp deliverTime;
    private Timestamp dealTime;

    public OrderDomain(String cUsername, String gUsername, Integer price, String status, Timestamp createTime, Timestamp payTime, Timestamp deliverTime, Timestamp dealTime) {
        this.cUsername = cUsername;
        this.gUsername = gUsername;
        this.price = price;
        this.status = status;
    }

    public OrderDomain(OrderDO orderDO){
        this.orderId = orderDO.getOrder_id();
        this.cUsername = orderDO.getC_username();
        this.gUsername = orderDO.getG_username();
        this.price = orderDO.getPrice();
        this.status = orderDO.getStatus();
        this.createTime = orderDO.getCreate_time();
        this.payTime = orderDO.getPay_time();
        this.deliverTime = orderDO.getDeliver_time();
        this.dealTime = orderDO.getDeal_time();
    }

    public OrderDomain(Integer orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public OrderDomain(){

    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getcUsername() {
        return cUsername;
    }

    public void setcUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getgUsername() {
        return gUsername;
    }

    public void setgUsername(String gUsername) {
        this.gUsername = gUsername;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public Timestamp getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Timestamp deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Timestamp getDealTime() {
        return dealTime;
    }

    public void setDealTime(Timestamp dealTime) {
        this.dealTime = dealTime;
    }
}
