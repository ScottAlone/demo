package com.xay.Service.impl;

import com.xay.Domain.BaseResult;
import com.xay.MySQL.Mapper.GetRegionMapper;
import com.xay.Service.GetRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Service
public class GetRegionServiceImpl implements GetRegionService{
    @Autowired
    private GetRegionMapper getRegionMapper;

    @Override
    public BaseResult<Object> getCity() {
        return new BaseResult<>(getRegionMapper.getCity());
    }

    @Override
    public BaseResult<Object> getCounty(Integer parentId) {
        return new BaseResult<>(getRegionMapper.getCounty(parentId));
    }

    @Override
    public BaseResult<Object> getCountyByName(String cityName) {
        return new BaseResult<>(getRegionMapper.getCountyByName(cityName));
    }
}
