package com.xay.MySQL.DO;

import com.xay.Domain.GuideDomain;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/16.
 */
public class GuideDO{
    private byte[] file;
    private String username;
    private float stars;
    private float balance;
    private String phone_num;
    private String city_name;
    private Integer served;

    public GuideDO(byte[] file, String username, float stars, String phone_num){
        this.file = file;
        this.username = username;
        this.stars = stars;
        this.phone_num = phone_num;
    }

    public GuideDO(String username, float stars, float balance, Integer served) {
        this.username = username;
        this.stars = stars;
        this.balance = balance;
        this.served = served;
    }

    public GuideDO(String username, float stars, float balance, String phone_num, String city_name, Integer served) {
        this.username = username;
        this.stars = stars;
        this.balance = balance;
        this.phone_num = phone_num;
        this.city_name = city_name;
        this.served = served;
    }

    public GuideDO(GuideDomain guideDomain){
        this.username = guideDomain.getUsername();
        this.phone_num = guideDomain.getPhoneNum();
        this.city_name = guideDomain.getCityName();
    }

    public GuideDO(){

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

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public Integer getServed() {
        return served;
    }

    public void setServed(Integer served) {
        this.served = served;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
