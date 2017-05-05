package com.xay.Service.impl;

import com.xay.Domain.BaseResult;
import com.xay.Controller.WebAccount;
import com.xay.MySQL.DO.AccountDO;
import com.xay.MySQL.Mapper.AccountMapper;
import com.xay.Security.UserDetailImpl;
import com.xay.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserDetailImpl userDetail;

    @Override
    public BaseResult<Object> register(WebAccount account) {
        if (account.getType() == 0){
            if (accountMapper.getGuideByUsername(account.getUsername()) != null
                    || accountMapper.getCustomerByUsername(account.getUsername()) != null){
                return new BaseResult<>(500, "用户已存在");
            }
            accountMapper.insertGuide(new AccountDO(account));
        }else if (account.getType() == 1){
            if (accountMapper.getCustomerByUsername(account.getUsername()) != null
                    || accountMapper.getGuideByUsername(account.getUsername()) != null){
                return new BaseResult<>(500, "用户已存在");
            }
            int a = accountMapper.insertCustomer(new AccountDO(account));
            System.out.println(a);
        }
        return new BaseResult<>();
    }

    @Override
    public BaseResult<Object> login(WebAccount account) {
        return null;
    }

    @Override
    public BaseResult<Object> update(WebAccount account) {
        if (account.getType() == 0){
            AccountDO accountDO = accountMapper.getGuideByUsername(account.getUsername());
            if (accountDO == null){
                return new BaseResult<>(500, "用户不存在");
            }
            UserDetails user = userDetail.loadUserByUsername(account.getUsername());
            if (accountDO.getPassword().equals(account.getPassword())){
                accountMapper.updateGuide(new AccountDO(account));
            }else return new BaseResult<>(500, "密码错误");
        }else if (account.getType() == 1){
            AccountDO accountDO = accountMapper.getCustomerByUsername(account.getUsername());
            if (accountMapper.getCustomerByUsername(account.getUsername()) == null){
                return new BaseResult<>(500, "用户不存在");
            }
            if (accountDO.getPassword().equals(account.getPassword())){
                accountMapper.updateCustomer(new AccountDO(account));
            }else return new BaseResult<>(500, "密码错误");
        }
        return new BaseResult<>();
    }
}
