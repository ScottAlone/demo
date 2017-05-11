package com.xay.Controller;

import com.xay.Domain.BaseResult;
import com.xay.Service.GetRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/3.
 */
@RestController
public class GetRegionController {
    @Autowired
    private GetRegionService getRegionService;

    /**
     * 查询省和直辖市
     * @return
     */
    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    public BaseResult<Object> getCity(){
        return getRegionService.getCity();
    }

    /**
     * 查询市辖区
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/getCounty", method = RequestMethod.GET)
    public BaseResult<Object> getCounty(@RequestParam("parentId") Integer parentId){
        return getRegionService.getCounty(parentId);
    }

    /**
     * 查询城市
     * @param cityName
     * @return
     */
    @RequestMapping(value = "/getCounty/name", method = RequestMethod.GET)
    public BaseResult<Object> getCountyByName(@RequestParam("cityName")String cityName){
        return getRegionService.getCountyByName(cityName);
    }
}
