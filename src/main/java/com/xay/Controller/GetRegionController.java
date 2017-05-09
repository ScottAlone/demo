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
     * 根据首字母查询省和直辖市
     * @return
     */
    @RequestMapping(value = "/getCityByFL", method = RequestMethod.GET)
    public BaseResult<Object> getCityByFL(@RequestParam("cityFL") String cityFL){
        return getRegionService.getCityByFL(cityFL);
    }

    /**
     * 根据首字母查询市辖区
     * @param parentName
     * @return
     */
    @RequestMapping(value = "/getCountyByFL", method = RequestMethod.GET)
    public BaseResult<Object> getCountyByFL(@RequestParam("parentName") String parentName,
                                          @RequestParam("countyFL") String countyFL){
        return getRegionService.getCountyByFL(parentName, countyFL);
    }
}
