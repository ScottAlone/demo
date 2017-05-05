package com.xay.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public interface GetRegionService {
    /**
     *查询省和直辖市
     * @return
     */
    List<Map<String, Object>> getCity();

    /**
     * 查询市辖区
     * @param parentId
     * @return
     */
    List<Map<String, Object>> getCounty(Integer parentId);
}
