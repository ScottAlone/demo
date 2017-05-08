package com.xay.Domain;

import com.xay.MySQL.DO.OrderDO;

import java.sql.Date;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public class OrderDomain {
    private Integer orderId;
    private Integer customerId;
    private Integer guideId;
    private Integer price;
    private String status;
    private Date createTime;
    private Date payTime;
    private Date deliverTime;
    private Date dealTime;

    public OrderDomain(Integer customerId, Integer guideId, Integer price, String status, Date createTime, Date payTime, Date deliverTime, Date dealTime) {
        this.customerId = customerId;
        this.guideId = guideId;
        this.price = price;
        this.status = status;
    }

    public OrderDomain(OrderDO orderDO){
        this.orderId = orderDO.getOrder_id();
        this.customerId = orderDO.getCustomer_id();
        this.guideId = orderDO.getGuide_id();
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getGuideId() {
        return guideId;
    }

    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }
}
