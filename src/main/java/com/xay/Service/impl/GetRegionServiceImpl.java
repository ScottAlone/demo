package com.xay.Service.impl;

import com.xay.MySQL.Mapper.GetRegionMapper;
import com.xay.Service.GetRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Service
public class GetRegionServiceImpl implements GetRegionService{
    @Autowired
    private GetRegionMapper getRegionMapper;

    @Override
    public List<Map<String, Object>> getCity() {
        return getRegionMapper.getCity();
    }

    @Override
    public List<Map<String, Object>> getCounty(Integer parentId) {
        return getRegionMapper.getCounty(parentId);
    }
}
