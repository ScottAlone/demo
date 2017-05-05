package com.xay.Controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/4.
 */
public class WebAccount implements Serializable {
    private String name;
    private String username;
    private String password;
    private String newPassword;
    private Integer type;

    public WebAccount(String name, String username, String password, String newPassword, Integer type) throws NoSuchAlgorithmException{
        setType(type);
        setName(name);
        setUsername(username);
        setPassword(password);
        setNewPassword(newPassword);
    }

    public WebAccount(String name, String username, String password, Integer type) throws NoSuchAlgorithmException{
        setType(type);
        setName(name);
        setUsername(username);
        setPassword(password);
    }
    public WebAccount(String username, String password, Integer type) throws NoSuchAlgorithmException{
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public void setPassword(String password) throws NoSuchAlgorithmException{
        this.password = password;
    }

    public void setNewPassword(String newPassword) throws NoSuchAlgorithmException {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(newPassword);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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