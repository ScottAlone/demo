package com.xay.Service;

import com.xay.Domain.BaseResult;
import com.xay.Domain.JourneyDomain;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public interface JourneyService {
    JourneyDomain[] getJourneyByCustomerId(Integer customerId);
    BaseResult postJourney(JourneyDomain journeyDomain);
    JourneyDomain getJourneyByJourneyId(Integer journeyId);
    BaseResult updateJourney(JourneyDomain journeyDomain);
}
