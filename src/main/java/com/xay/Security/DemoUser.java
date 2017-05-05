package com.xay.Security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public class DemoUser implements UserDetails{
    private final Integer type;
    private final Integer id;
    private final String name;
    private final String userName;
    private final String password;
    private final List<GrantedAuthority> authorities;
    private final boolean enabled;

    public DemoUser(Integer type, Integer id, String name, String userName, String password, List<GrantedAuthority> authorities, boolean enabled, Date lastPasswordResetDate){
        this.type = type;
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    public Integer getType() {
        return type;
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
