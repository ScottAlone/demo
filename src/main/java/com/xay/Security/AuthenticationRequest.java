package com.xay.Security;

import java.io.Serializable;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public class AuthenticationRequest implements Serializable {
    private static final long serialVersionUID = -8445943548965154778L;

    private String username;
    private String password;

    public AuthenticationRequest() {
        super();
    }

    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
