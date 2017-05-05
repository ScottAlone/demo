package com.xay.MySQL.DO;

import com.xay.Domain.WebAccount;

import java.sql.Timestamp;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public class AccountDO extends BaseDO{
    private String name;
    private String username;
    private String password;

    public AccountDO(WebAccount webAccount) {
        setName(webAccount.getName());
        setUsername(webAccount.getUsername());
        setPassword(webAccount.getPassword());
    }

    public AccountDO() {

    }

    public AccountDO(Integer id, Timestamp create_time, Timestamp update_time, String name, String username, String password) {
        super(id, create_time, update_time);
        this.name = name;
        this.username = username;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountDO{" +
                "name='" + getName() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
