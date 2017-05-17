package com.xay.Service.impl;

import com.xay.Domain.BaseResult;
import com.xay.Domain.AccountDomain;
import com.xay.Domain.GuideDomain;
import com.xay.MySQL.DO.AccountDO;
import com.xay.MySQL.DO.GuideDO;
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
    public BaseResult<Object> register(AccountDomain account) {
        if (account.getType() == 0){
            if (accountMapper.getGuideByUsername(account.getUsername()) != null){
                return new BaseResult<>(500, "User already exists");
            }
            accountMapper.insertGuide(new AccountDO(account));
        }else if (account.getType() == 1){
            if (accountMapper.getCustomerByUsername(account.getUsername()) != null){
                return new BaseResult<>(500, "User already exists");
            }
            accountMapper.insertCustomer(new AccountDO(account));
        }
        return new BaseResult<>();
    }

    @Override
    public BaseResult<Object> update(AccountDomain account) throws NoSuchAlgorithmException{
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (account.getType() == 0){
            AccountDO accountDO = accountMapper.getGuideByUsername(account.getUsername());
            if (accountDO == null){
                return new BaseResult<>(500, "No user found");
            }
            if (passwordEncoder.matches(account.getRawPassword(), accountDO.getPassword())){
                account.setPassword(account.getRawNewPassword());
                accountMapper.updateGuide(new AccountDO(account));
            }else return new BaseResult<>(500, "Bad password");
        }else if (account.getType() == 1){
            AccountDO accountDO = accountMapper.getCustomerByUsername(account.getUsername());
            if (accountMapper.getCustomerByUsername(account.getUsername()) == null){
                return new BaseResult<>(500, "No user found");
            }
            if (passwordEncoder.matches(account.getRawPassword(), accountDO.getPassword())){
                account.setPassword(account.getRawNewPassword());
                accountMapper.updateCustomer(new AccountDO(account));
            }else return new BaseResult<>(500, "Bad password");
        }
        return new BaseResult<>();
    }

    @Override
    public BaseResult<Object> insertImage(AccountDomain accountDomain) {
        AccountDO accountDO;
        Integer type = accountDomain.getType();
        String username = accountDomain.getUsername();
        if (type == 0){
            accountDO = accountMapper.getGuideByUsername(username);
            if (accountDO == null){
                return new BaseResult<>(500, "No user found");
            }
            if (accountDO.getFile() == null){
                accountMapper.insertGuideImage(new AccountDO(username, accountDomain.getFile()));
            }else accountMapper.updateGuideImg(new AccountDO(username, accountDomain.getFile()));
        }else if (type == 1){
            accountDO = accountMapper.getCustomerByUsername(username);
            if (accountDO == null){
                return new BaseResult<>(500, "No user found");
            }
            if (accountDO.getFile() == null){
                accountMapper.insertCustomerImage(new AccountDO(username, accountDomain.getFile()));
            }else accountMapper.updateCustomerImg(new AccountDO(username, accountDomain.getFile()));
        }else return new BaseResult<>(500, "Wrong user type");
        return new BaseResult<>();
    }

    @Override
    public BaseResult<Object> getImage(String username, Integer type) {
        AccountDO accountDO;
        if (type == 0){
            accountDO = accountMapper.getGuideByUsername(username);
        }else if (type == 1){
            accountDO = accountMapper.getCustomerByUsername(username);
        }else return new BaseResult<>(500, "Wrong user type");
        if (accountDO == null){
            return new BaseResult<>(500, "No user found");
        }
        if (accountDO.getFile() != null){
            return new BaseResult<>(accountDO.getFile());
        }else return new BaseResult<>(500, "No image found");
    }

    @Override
    public BaseResult<Object> getGuides(String guideName) {
        GuideDO[] guideDOS = accountMapper.getGuides(guideName);
        GuideDomain[] guideDomains = new GuideDomain[guideDOS.length];
        if (guideDOS.length != 0){
            for (int i = 0; i < guideDomains.length; i++){
                guideDomains[i] = new GuideDomain(guideDOS[i]);
            }
            return new BaseResult<> (guideDomains);
        }
        return new BaseResult<>(500, "No guides found");
    }

    @Override
    public BaseResult<Object> getGuideByName(String gUsername) {
        GuideDO guideDO = accountMapper.getGuideByName(gUsername);
        if (guideDO != null){
            return new BaseResult<>(new GuideDomain(guideDO));
        }
        return new BaseResult<>(500, "No guide found");
    }

    @Override
    public BaseResult<Object> updateGuideInfo(GuideDomain guideDomain) {
        GuideDO guideDO = accountMapper.getGuideByName(guideDomain.getUsername());
        if (guideDO != null){
            accountMapper.updateGuideInfo(new GuideDO(guideDomain));
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "No guide found");
    }

    @Override
    public BaseResult<Object> payGuide(GuideDomain guideDomain) {
        GuideDO guideDO = accountMapper.getGuideByName(guideDomain.getUsername());
        if (guideDO != null){
            int served = guideDO.getServed();
            float balance = guideDO.getBalance() + guideDomain.getBalance();
            float stars = guideDO.getStars();
            stars = ((stars * served) + guideDomain.getStars())/(served + 1);
            served++;
            accountMapper.payGuide(new GuideDO(guideDomain.getUsername(), stars, balance, served));
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "No guide found");
    }
}
