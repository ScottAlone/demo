package com.xay.Controller;

import com.xay.Domain.BaseResult;
import com.xay.Domain.JourneyDomain;
import com.xay.Service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
@RestController
public class JourneyController {
    @Autowired
    JourneyService journeyService;

    @RequestMapping(value = "journeys", method = RequestMethod.POST)
    public BaseResult<Object> postJourney(@RequestBody JourneyDomain journeyDomain){
        return journeyService.postJourney(journeyDomain);
    }

    @RequestMapping(value = "journeys", method = RequestMethod.GET)
    public BaseResult<Object> getJourneyByUsername(@RequestParam("username") String cUsername){
        return journeyService.getJourneyByUsername(cUsername);
    }

    @RequestMapping(value = "journeys/city", method = RequestMethod.GET)
    public BaseResult<Object> getJourneyByCityName(@RequestParam("cityName") String cityName){
        return journeyService.getJourneyByCityName(cityName);
    }

    @RequestMapping(value = "journeys/id", method = RequestMethod.GET)
    public BaseResult<Object> getJourney(Integer journeyId){
        return journeyService.getJourneyByJourneyId(journeyId);
    }

    @RequestMapping(value = "journeys/id", method = RequestMethod.PATCH)
    public BaseResult<Object> updateJourney(@RequestBody JourneyDomain journeyDomain){
        return journeyService.updateJourney(journeyDomain);
    }
}
