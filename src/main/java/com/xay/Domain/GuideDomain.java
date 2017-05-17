package com.xay.Domain;

import com.xay.MySQL.DO.GuideDO;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/16.
 */
public class GuideDomain {
    private byte[] file;
    private String username;
    private String phoneNum;
    private String cityName;
    private float balance;
    private float stars;

    public GuideDomain(GuideDO guideDO){
        this.file = guideDO.getFile();
        this.username = guideDO.getUsername();
        this.phoneNum = guideDO.getPhone_num();
        this.balance = guideDO.getBalance();
        this.stars = guideDO.getStars();
        this.cityName = guideDO.getCity_name();
    }

    public GuideDomain(String username, float stars, float balance){
        this.username = username;
        this.stars = stars;
        this.balance = balance;
    }

    public GuideDomain(String username, String phoneNum, String cityName){
        this.username = username;
        this.phoneNum = phoneNum;
        this.cityName = cityName;
    }

    public GuideDomain(){

    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
