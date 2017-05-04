package com.xay.Controller;

import sun.misc.BASE64Encoder;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/4.
 */
public class WebAccount implements Serializable {
    private String name;
    private String username;
    private String password;
    private int type;
    MessageDigest md;
    BASE64Encoder base;

    public WebAccount(String name, String username, String password, int type) throws NoSuchAlgorithmException{
        setType(type);
        setName(name);
        setUsername(username);
        setPassword(password);
    }
    public WebAccount(String username, String password, int type) throws NoSuchAlgorithmException{
        setType(type);
        setUsername(username);
        setPassword(password);
    }

    public WebAccount(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException{
        md = MessageDigest.getInstance("MD5");
        base = new BASE64Encoder();
        this.password = base.encode(md.digest(password.getBytes()));
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + getName() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", type='" + getType() + '\'' +
                "}";
    }
}