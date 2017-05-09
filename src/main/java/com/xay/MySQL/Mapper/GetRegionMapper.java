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
    @Select("SELECT * FROM city WHERE parent_id=0")
    CityDO[] getCity();

    /**
     * 根据省市id获取下级辖区
     * @param parent_id
     * @return
     */
    @Select("SELECT * FROM city WHERE parent_id=#{parent_id}")
    CityDO[] getCounty(@Param("parent_id")Integer parent_id);

    /**
     * 根据首字母获取城市
     * @return
     */
    @Select("SELECT * FROM city WHERE first_letter=#{first_letter} AND parent_id=0")
    CityDO[] getCityByFL(@Param("first_letter")String first_letter);

    /**
     * 根据省市和下级辖区首字母获取下级辖区
     * @param parentName
     * @param first_letter
     * @return
     */
    @Select("SELECT * FROM city WHERE name=#{name} AND first_letter=#{first_letter}")
    CityDO[] getCountyByFL(@Param("name")String parentName, @Param("first_letter")String first_letter);
}
