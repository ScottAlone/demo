package com.xay.MySQL.DO;

import com.xay.Domain.JourneyDomain;

import java.sql.Date;


/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public class JourneyDO {
    private Integer journey_id;
    private String journey_name;
    private Date create_time;
    private Date update_time;
    private String phone_num;
    private Integer city_id;
    private String members;
    private Integer tour_type;
    private Integer low_price;
    private Integer high_price;
    private String start_time;
    private String end_time;
    private String tags;
    private String others;
    private Integer customer_id;
    private String due_date;
    private Integer price;

    public JourneyDO(JourneyDomain journeyDomain){
        this.journey_id = journeyDomain.getJourneyId();
        this.journey_name = journeyDomain.getJourneyName();
        this.phone_num = journeyDomain.getPhoneNum();
        this.city_id = journeyDomain.getCityId();
        this.members = journeyDomain.getMembers();
        this.tour_type = journeyDomain.getTourType();
        this.low_price = journeyDomain.getLowPrice();
        this.high_price = journeyDomain.getHighPrice();
        this.start_time = journeyDomain.getStartTime();
        this.end_time = journeyDomain.getEndTime();
        this.tags = journeyDomain.getTags();
        this.others = journeyDomain.getOthers();
        this.customer_id = journeyDomain.getCustomerId();
        this.due_date = journeyDomain.getDueDate();
        this.price = journeyDomain.getPrice();
    }

    public JourneyDO(Integer journey_id, String journey_name, Date create_time, Date update_time, String phone_num, Integer city_id, String members, Integer tour_type, Integer low_price, Integer high_price, String start_time, String end_time, String tags, String others, Integer customer_id) {
        this.journey_id = journey_id;
        this.journey_name = journey_name;
        this.create_time = create_time;
        this.update_time = update_time;
        this.phone_num = phone_num;
        this.city_id = city_id;
        this.members = members;
        this.tour_type = tour_type;
        this.low_price = low_price;
        this.high_price = high_price;
        this.start_time = start_time;
        this.end_time = end_time;
        this.tags = tags;
        this.others = others;
        this.customer_id = customer_id;
    }

    public JourneyDO(Integer journey_id){
        this.journey_id = journey_id;
    }

    public JourneyDO(){

    }

    public Integer getJourney_id() {
        return journey_id;
    }

    public void setJourney_id(Integer journey_id) {
        this.journey_id = journey_id;
    }

    public String getJourney_name() {
        return journey_name;
    }

    public void setJourney_name(String journey_name) {
        this.journey_name = journey_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public Integer getTour_type() {
        return tour_type;
    }

    public void setTour_type(Integer tour_type) {
        this.tour_type = tour_type;
    }

    public Integer getLow_price() {
        return low_price;
    }

    public void setLow_price(Integer low_price) {
        this.low_price = low_price;
    }

    public Integer getHigh_price() {
        return high_price;
    }

    public void setHigh_price(Integer high_price) {
        this.high_price = high_price;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
