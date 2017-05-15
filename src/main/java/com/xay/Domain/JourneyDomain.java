package com.xay.Domain;

import com.xay.MySQL.DO.JourneyDO;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public class JourneyDomain implements Serializable {
    private Integer journeyId;
    private String journeyName;
    private String createTime;
    private String updateTime;
    private String phoneNum;
    private String cityName;
    private String members;
    private Integer tourType;
    private Integer lowPrice;
    private Integer highPrice;
    private String startTime;
    private String endTime;
    private String tags;
    private String others;
    private String cUsername;
    private String dueDate;
    private Integer price;
    private Integer paid;


    public JourneyDomain(JourneyDO journeyDO, String createTime, String updateTime){
        this.journeyId = journeyDO.getJourney_id();
        this.journeyName = journeyDO.getJourney_name();
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.phoneNum = journeyDO.getPhone_num();
        this.cityName = journeyDO.getCity_name();
        this.members = journeyDO.getMembers();
        this.tourType = journeyDO.getTour_type();
        this.lowPrice = journeyDO.getLow_price();
        this.highPrice = journeyDO.getHigh_price();
        this.startTime = journeyDO.getStart_time();
        this.endTime = journeyDO.getEnd_time();
        this.paid = journeyDO.getPaid();
        this.tags = journeyDO.getTags();
        this.others = journeyDO.getOthers();
        this.cUsername = journeyDO.getC_username();
        this.dueDate = journeyDO.getDue_date();
        this.price = journeyDO.getPrice();
    }

    public JourneyDomain(String journeyName, String phoneNum, String cityName, String members, Integer tourType, Integer lowPrice, Integer highPrice, String startTime, String endTime, String tags, String others, String cUsername, String dueDate, Integer price) {
        this.journeyName = journeyName;
        this.phoneNum = phoneNum;
        this.cityName = cityName;
        this.members = members;
        this.tourType = tourType;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = tags;
        this.others = others;
        this.cUsername = cUsername;
        this.dueDate = dueDate;
        this.price = price;
    }

    public JourneyDomain(){

    }

    public Integer getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Integer journeyId) {
        this.journeyId = journeyId;
    }

    public String getJourneyName() {
        return journeyName;
    }

    public void setJourneyName(String journeyName) {
        this.journeyName = journeyName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public Integer getTourType() {
        return tourType;
    }

    public void setTourType(Integer tourType) {
        this.tourType = tourType;
    }

    public Integer getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Integer highPrice) {
        this.highPrice = highPrice;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getcUsername() {
        return cUsername;
    }

    public void setcUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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
