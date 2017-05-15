package com.xay.MySQL.DO;

import com.xay.Domain.JourneyDomain;

import java.sql.Timestamp;


/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public class JourneyDO {
    private Integer journey_id;
    private String journey_name;
    private Timestamp create_time;
    private Timestamp update_time;
    private String phone_num;
    private String city_name;
    private String members;
    private Integer tour_type;
    private Integer low_price;
    private Integer high_price;
    private String start_time;
    private String end_time;
    private String tags;
    private String others;
    private String c_username;
    private String due_date;
    private Integer price;
    private Integer paid;

    public JourneyDO(JourneyDomain journeyDomain){
        this.journey_id = journeyDomain.getJourneyId();
        this.journey_name = journeyDomain.getJourneyName();
        this.phone_num = journeyDomain.getPhoneNum();
        this.city_name = journeyDomain.getCityName();
        this.members = journeyDomain.getMembers();
        this.tour_type = journeyDomain.getTourType();
        this.low_price = journeyDomain.getLowPrice();
        this.high_price = journeyDomain.getHighPrice();
        this.start_time = journeyDomain.getStartTime();
        this.end_time = journeyDomain.getEndTime();
        this.tags = journeyDomain.getTags();
        this.others = journeyDomain.getOthers();
        this.c_username = journeyDomain.getcUsername();
        this.due_date = journeyDomain.getDueDate();
        this.price = journeyDomain.getPrice();
        this.paid = journeyDomain.getPaid();
    }

    public JourneyDO(Integer journey_id, String journey_name, Timestamp create_time, Timestamp update_time, String phone_num, String city_name, String members, Integer tour_type, Integer low_price, Integer high_price, String start_time, String end_time, String tags, String others, String c_username, Integer paid, Integer price, String due_date) {
        this.journey_id = journey_id;
        this.journey_name = journey_name;
        this.create_time = create_time;
        this.update_time = update_time;
        this.phone_num = phone_num;
        this.city_name = city_name;
        this.members = members;
        this.tour_type = tour_type;
        this.low_price = low_price;
        this.high_price = high_price;
        this.start_time = start_time;
        this.end_time = end_time;
        this.due_date = due_date;
        this.tags = tags;
        this.others = others;
        this.c_username = c_username;
        this.price = price;
        this.paid = paid;
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

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
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

    public String getC_username() {
        return c_username;
    }

    public void setC_username(String c_username) {
        this.c_username = c_username;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }
}
