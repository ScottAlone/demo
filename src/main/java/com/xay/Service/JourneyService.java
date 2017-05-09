package com.xay.Service;

import com.xay.Domain.BaseResult;
import com.xay.Domain.JourneyDomain;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public interface JourneyService {
    BaseResult<Object> getJourneyByCustomerId(Integer customerId);
    BaseResult<Object> postJourney(JourneyDomain journeyDomain);
    BaseResult<Object> getJourneyByJourneyId(Integer journeyId);
    BaseResult<Object> updateJourney(JourneyDomain journeyDomain);
}
