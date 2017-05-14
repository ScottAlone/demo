package com.xay.Domain;

import com.xay.MySQL.DO.OrderDO;

import java.text.SimpleDateFormat;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public class OrderDomain {
    private Integer orderId;
    private Integer journeyId;
    private String cUsername;
    private String gUsername;
    private Integer price;
    private Integer status;
    private String createTime;
    private String payTime;
    private String deliverTime;
    private String dealTime;

    public OrderDomain(Integer journeyId, String cUsername, String gUsername, Integer price) {
        this.journeyId = journeyId;
        this.cUsername = cUsername;
        this.gUsername = gUsername;
        this.price = price;
    }

    public OrderDomain(OrderDO orderDO, String createTime, String payTime, String deliverTime, String dealTime){
        this.orderId = orderDO.getOrder_id();
        this.journeyId = orderDO.getJourney_id();
        this.cUsername = orderDO.getC_username();
        this.gUsername = orderDO.getG_username();
        this.price = orderDO.getPrice();
        this.status = orderDO.getStatus();
        this.createTime = createTime;
        this.payTime = payTime;
        this.deliverTime = deliverTime;
        this.dealTime = dealTime;
    }

    public OrderDomain(Integer orderId, Integer status) {
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

    public Integer getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Integer journeyId) {
        this.journeyId = journeyId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }
}
