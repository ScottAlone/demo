package com.xay.MySQL.DO;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/16.
 */
public class GuideDO{
    private String username;
    private float stars;

    public GuideDO(String username, float stars){
        this.username = username;
        this.stars = stars;
    }

    public GuideDO(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
