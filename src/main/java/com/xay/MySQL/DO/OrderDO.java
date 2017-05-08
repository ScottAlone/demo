package com.xay.MySQL.DO;

import com.xay.Domain.OrderDomain;

import java.sql.Date;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public class OrderDO {

    private Integer order_id;
    private Integer customer_id;
    private Integer guide_id;
    private Integer price;
    private String status;
    private Date create_time;
    private Date pay_time;
    private Date deliver_time;
    private Date deal_time;

    public OrderDO(OrderDomain orderDomain){
        this.create_time = orderDomain.getCreateTime();
        this.customer_id = orderDomain.getCustomerId();
        this.deal_time = orderDomain.getDealTime();
        this.deliver_time = orderDomain.getDeliverTime();
        this.guide_id = orderDomain.getGuideId();
        this.order_id = orderDomain.getOrderId();
        this.pay_time = orderDomain.getPayTime();
        this.price = orderDomain.getPrice();
        this.status = orderDomain.getStatus();
    }

    public OrderDO(Integer order_id, Integer customer_id, Integer guide_id, Integer price, String status, Date create_time, Date pay_time, Date deliver_time, Date deal_time) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.guide_id = guide_id;
        this.price = price;
        this.status = status;
        this.create_time = create_time;
        this.pay_time = pay_time;
        this.deliver_time = deliver_time;
        this.deal_time = deal_time;
    }

    public OrderDO(Integer customer_id, String status) {
        this.customer_id = customer_id;
        this.status = status;
    }

    public OrderDO(){

    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getGuide_id() {
        return guide_id;
    }

    public void setGuide_id(Integer guide_id) {
        this.guide_id = guide_id;
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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Date getDeliver_time() {
        return deliver_time;
    }

    public void setDeliver_time(Date deliver_time) {
        this.deliver_time = deliver_time;
    }

    public Date getDeal_time() {
        return deal_time;
    }

    public void setDeal_time(Date deal_time) {
        this.deal_time = deal_time;
    }
}
