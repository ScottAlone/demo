package com.xay.Service.impl;

import com.xay.Domain.BaseResult;
import com.xay.Domain.WebAccount;
import com.xay.MySQL.DO.AccountDO;
import com.xay.MySQL.Mapper.AccountMapper;
import com.xay.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountMapper accountMapper;

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
    public BaseResult<Object> update(WebAccount account) throws NoSuchAlgorithmException{
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (account.getType() == 0){
            AccountDO accountDO = accountMapper.getGuideByUsername(account.getUsername());
            if (accountDO == null){
                return new BaseResult<>(500, "用户不存在");
            }
            if (passwordEncoder.matches(account.getRawPassword(), accountDO.getPassword())){
                account.setPassword(account.getRawNewPassword());
                accountMapper.updateGuide(new AccountDO(account));
            }else return new BaseResult<>(500, "密码错误");
        }else if (account.getType() == 1){
            AccountDO accountDO = accountMapper.getCustomerByUsername(account.getUsername());
            if (accountMapper.getCustomerByUsername(account.getUsername()) == null){
                return new BaseResult<>(500, "用户不存在");
            }
            if (passwordEncoder.matches(account.getRawPassword(), accountDO.getPassword())){
                account.setPassword(account.getRawNewPassword());
                accountMapper.updateCustomer(new AccountDO(account));
            }else return new BaseResult<>(500, "密码错误");
        }
        return new BaseResult<>();
    }
}
