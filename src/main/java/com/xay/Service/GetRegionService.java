package com.xay.Service;

import com.xay.Domain.BaseResult;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public interface GetRegionService {
    /**
     *查询省和直辖市
     * @return
     */
    BaseResult<Object> getCity();

    /**
     * 查询市辖区
     * @param parentId
     * @return
     */
    BaseResult<Object> getCounty(Integer parentId);

    /**
     * 查询城市
     * @param cityName
     * @return
     */
    BaseResult<Object> getCountyByName(String cityName);
}
