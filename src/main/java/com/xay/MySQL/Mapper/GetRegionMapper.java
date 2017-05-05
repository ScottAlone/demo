package com.xay.MySQL.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Mapper
public interface GetRegionMapper {
    @Select("SELECT  city_id, name, first_letter FROM city WHERE parent_id=0")
    List<Map<String, Object>> getCity();

    @Select("SELECT city_id, name, first_letter FROM city WHERE parent_id=#{parent_id}")
    List<Map<String, Object>> getCounty(@Param("parent_id")Integer parent_id);
}
