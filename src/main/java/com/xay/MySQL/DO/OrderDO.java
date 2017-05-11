package com.xay.MySQL.DO;

import com.xay.Domain.OrderDomain;

import java.sql.Timestamp;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public class OrderDO {
    private Integer order_id;
    private Integer journey_id;
    private String c_username;
    private String g_username;
    private Integer price;
    private Integer status;
    private Timestamp create_time;
    private Timestamp pay_time;
    private Timestamp deliver_time;
    private Timestamp deal_time;

    public OrderDO(OrderDomain orderDomain){
        this.journey_id = orderDomain.getJourneyId();
        this.c_username = orderDomain.getcUsername();
        this.g_username = orderDomain.getgUsername();
        this.price = orderDomain.getPrice();
    }

    public OrderDO(Integer order_id, Integer journey_id, String c_username, String g_username, Integer price, Integer status, Timestamp create_time, Timestamp pay_time, Timestamp deliver_time, Timestamp deal_time) {
        this.order_id = order_id;
        this.journey_id = journey_id;
        this.c_username = c_username;
        this.g_username = g_username;
        this.price = price;
        this.status = status;
        this.create_time = create_time;
        this.pay_time = pay_time;
        this.deliver_time = deliver_time;
        this.deal_time = deal_time;
    }

    public OrderDO(String c_username, Integer status) {
        this.c_username = c_username;
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

    public Integer getJourney_id() {
        return journey_id;
    }

    public void setJourney_id(Integer journey_id) {
        this.journey_id = journey_id;
    }

    public String getC_username() {
        return c_username;
    }

    public void setC_username(String c_username) {
        this.c_username = c_username;
    }

    public String getG_username() {
        return g_username;
    }

    public void setG_username(String g_username) {
        this.g_username = g_username;
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

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getPay_time() {
        return pay_time;
    }

    public void setPay_time(Timestamp pay_time) {
        this.pay_time = pay_time;
    }

    public Timestamp getDeliver_time() {
        return deliver_time;
    }

    public void setDeliver_time(Timestamp deliver_time) {
        this.deliver_time = deliver_time;
    }

    public Timestamp getDeal_time() {
        return deal_time;
    }

    public void setDeal_time(Timestamp deal_time) {
        this.deal_time = deal_time;
    }
}
