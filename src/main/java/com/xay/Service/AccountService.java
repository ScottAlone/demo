package com.xay.Service;

import com.xay.Domain.BaseResult;
import com.xay.Controller.WebAccount;


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
    BaseResult<Object> register(WebAccount account);

    /**
     * 用户登陆
     * @param account
     * @return
     */
    BaseResult<Object> login(WebAccount account);

    /**
     * 用户登陆
     * @param account
     * @return
     */
    BaseResult<Object> update(WebAccount account);
}
