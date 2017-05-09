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
     *根据首字母获取城市
     * @param firstLetter
     * @return
     */
    BaseResult<Object> getCityByFL(String firstLetter);

    /**
     * 根据省市id和下级辖区首字母获取下级辖区
     * @param parentName
     * @param firstLetter
     * @return
     */
    BaseResult<Object> getCountyByFL(String parentName, String firstLetter);
}
