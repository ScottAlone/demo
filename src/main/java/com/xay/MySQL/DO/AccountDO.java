package com.xay.MySQL.DO;

import com.xay.Domain.AccountDomain;

import java.sql.Timestamp;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public class AccountDO extends BaseDO{
    private String name;
    private String username;
    private String password;
    private byte[] file;

    public AccountDO(AccountDomain accountDomain) {
        this.name = accountDomain.getName();
        this.username = accountDomain.getUsername();
        this.password = accountDomain.getPassword();
        this.file = accountDomain.getFile();
    }

    public AccountDO() {

    }

    public AccountDO(Integer id, Timestamp create_time, Timestamp update_time, String name, String username, String password, byte[] file) {
        super(id, create_time, update_time);
        this.name = name;
        this.username = username;
        this.password = password;
        this.file = file;
    }

    public AccountDO(String username, byte[] file) {
        this.username = username;
        this.file = file;
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
