package com.xay.MySQL.Mapper;

import com.xay.MySQL.DO.CityDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Mapper
public interface GetRegionMapper {
    /**
     * 获取所有城市
     * @return
     */
    @Select("SELECT * FROM cities WHERE parent_id=0")
    CityDO[] getCity();

    /**
     * 根据省市id获取下级辖区
     * @param parent_id
     * @return
     */
    @Select("SELECT * FROM cities WHERE parent_id=#{parent_id}")
    CityDO[] getCounty(@Param("parent_id")Integer parent_id);

    /**
     * 查询城市
     * @param cityName
     * @return
     */
    @Select("SELECT * FROM cities WHERE id=#{id}")
    CityDO getCountyByName(@Param("city_name")String cityName);
}
