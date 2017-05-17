package com.xay.Service;

import com.xay.Domain.BaseResult;
import com.xay.Domain.AccountDomain;
import com.xay.Domain.GuideDomain;

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

    /**
     * 导游获取
     * @param cityName
     * @return
     */
    BaseResult<Object> getGuides(String cityName);

    /**
     * 导游信息获取
     * @param gUsername
     * @return
     */
    BaseResult<Object> getGuideByName(String gUsername);

    /**
     * 修改地区和联系方式
     * @param guideDomain
     * @return
     */
    BaseResult<Object> updateGuideInfo(GuideDomain guideDomain);

    /**
     * 支付并评分
     * @param guideDomain
     * @return
     */
    BaseResult<Object> payGuide(GuideDomain guideDomain);
}
