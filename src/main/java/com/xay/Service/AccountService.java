package com.xay.Service;

import com.xay.Domain.BaseResult;
import com.xay.Domain.AccountDomain;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public interface AccountService {
    /**
     * 用户注册
     * @param account
     * @return
     */
    BaseResult<Object> register(AccountDomain account);

    /**
     * 用户登陆
     * @param account
     * @return
     */
    BaseResult<Object> update(AccountDomain account) throws NoSuchAlgorithmException;

    /**
     * 头像/附件上传
     * @param accountDomain
     * @return
     * @throws IOException
     */
    BaseResult<Object> insertImage(AccountDomain accountDomain);

    /**
     * 头像获取
     * @param username
     * @param type
     * @return
     */
    BaseResult<Object> getImage(String username, Integer type);
}
