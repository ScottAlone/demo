package com.xay.Security;

import com.xay.MySQL.DO.AccountDO;
import com.xay.MySQL.Mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Service
public class UserDetailImpl implements UserDetailsService{
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDO guideAccount = accountMapper.getGuideByUsername(username);
        AccountDO customerAccount = accountMapper.getCustomerByUsername(username);

        if (guideAccount == null && customerAccount == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else if (guideAccount == null){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("Customer"));
            return new DemoUser(1, customerAccount.getId(),customerAccount.getName(),customerAccount.getUsername(),customerAccount.getPassword(),authorities,true,new Date());
        }else {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("Guide"));
            return new DemoUser(0, guideAccount.getId(),guideAccount.getName(),guideAccount.getUsername(),guideAccount.getPassword(),authorities,true,new Date());
        }
    }
}
