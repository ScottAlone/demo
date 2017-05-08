package com.xay.Controller;

import com.xay.Domain.BaseResult;
import com.xay.Domain.JourneyDomain;
import com.xay.Service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
@RestController
public class JourneyController {
    @Autowired
    JourneyService journeyService;

    @RequestMapping(value = "journeys", method = RequestMethod.POST)
    public BaseResult postJourney(@RequestBody JourneyDomain journeyDomain){
        return journeyService.postJourney(journeyDomain);
    }

    @RequestMapping(value = "journeys", method = RequestMethod.GET)
    public JourneyDomain[] getJourneyByCustomerId(Integer customerId){
        return journeyService.getJourneyByCustomerId(customerId);
    }

    @RequestMapping(value = "journeys/journey", method = RequestMethod.GET)
    public JourneyDomain getJourney(Integer journeyId){
        return journeyService.getJourneyByJourneyId(journeyId);
    }

    @RequestMapping(value = "journeys/journey", method = RequestMethod.PATCH)
    public BaseResult updateJourney(@RequestBody JourneyDomain journeyDomain){
        return journeyService.updateJourney(journeyDomain);
    }
}
