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
     * 更新行程
     * @param journeyDomain
     * @return
     */
    BaseResult<Object> updateJourney(JourneyDomain journeyDomain);

    /**
     * 根据用户名获取所有行程
     * @param username
     * @return
     */
    BaseResult<Object> getJourneyByUsername(String username);

}
