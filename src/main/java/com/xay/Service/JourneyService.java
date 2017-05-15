package com.xay.Service;

import com.xay.Domain.BaseResult;
import com.xay.Domain.JourneyDomain;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public interface JourneyService {
    /**
     * 新建行程
     * @param journeyDomain
     * @return
     */
    BaseResult<Object> postJourney(JourneyDomain journeyDomain);

    /**
     * 根据行程id获取单个行程
     * @param journeyId
     * @return
     */
    BaseResult<Object> getJourneyByJourneyId(Integer journeyId);

    /**
     * 根据地区获取行程
     * @param cityName
     * @return
     */
    BaseResult<Object> getJourneyByCityName(String cityName);

    /**
     * 选择导游
     * @param journeyId
     * @return
     */
    BaseResult<Object> selectJourney(Integer journeyId);

    /**
     * 支付行程
     * @param journeyId
     * @return
     */
    BaseResult<Object> payJourney(Integer journeyId);

    /**
     * 根据用户名获取所有行程
     * @param username
     * @return
     */
    BaseResult<Object> getJourneyByUsername(String username);

}
