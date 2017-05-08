package com.xay.Domain;

import com.xay.MySQL.DO.JourneyDO;

import java.io.Serializable;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public class JourneyDomain implements Serializable {
    private Integer journeyId;
    private String journeyName;
    private String phoneNum;
    private Integer cityId;
    private String members;
    private Integer tourType;
    private Integer lowPrice;
    private Integer highPrice;
    private String startTime;
    private String endTime;
    private String tags;
    private String others;
    private Integer customerId;

    public JourneyDomain(JourneyDO journeyDO){
        this.journeyId = journeyDO.getJourney_id();
        this.journeyName = journeyDO.getJourney_name();
        this.phoneNum = journeyDO.getPhone_num();
        this.cityId = journeyDO.getCity_id();
        this.members = journeyDO.getMembers();
        this.tourType = journeyDO.getTour_type();
        this.lowPrice = journeyDO.getLow_price();
        this.highPrice = journeyDO.getHigh_price();
        this.startTime = journeyDO.getStart_time();
        this.endTime = journeyDO.getEnd_time();
        this.tags = journeyDO.getTags();
        this.others = journeyDO.getOthers();
        this.customerId = journeyDO.getCustomer_id();
    }

    public JourneyDomain(String journeyName, String phoneNum, Integer cityId, String members, Integer tourType, Integer lowPrice, Integer highPrice, String startTime, String endTime, String tags, String others, Integer customerId) {
        this.journeyName = journeyName;
        this.phoneNum = phoneNum;
        this.cityId = cityId;
        this.members = members;
        this.tourType = tourType;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = tags;
        this.others = others;
        this.customerId = customerId;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
