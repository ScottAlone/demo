package com.xay.MySQL.Mapper;

import com.xay.MySQL.DO.JourneyDO;
import org.apache.ibatis.annotations.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
@Mapper
public interface JourneyMapper {

    /**
     * 获取用户所有行程
     * @param c_username
     * @return
     */
    @Select("SELECT * FROM journey WHERE binary c_username=#{c_username}")
    JourneyDO[] getJourneyByUsername(@Param("c_username")String c_username);

    /**
     * 新建行程
     * @param journeyDO
     * @return
     */
    @Insert("INSERT INTO journey(journey_name, phone_num, city_name, members, tour_type, low_price, high_price, start_time, end_time, tags, others, c_username, due_date, price) " +
            "VALUES(#{journey_name}, #{phone_num}, #{city_name}, #{members}, #{tour_type}, #{low_price}, #{high_price}, #{start_time}, #{end_time}, #{tags}, #{others}, #{c_username}, #{due_date}, #{price})")
    int postJourney(JourneyDO journeyDO);

    /**
     * 选择导游
     * @param journeyId
     * @return
     */
    @Update("UPDATE journey SET paid=0, update_time=CURRENT_TIMESTAMP WHERE journey_id=#{journey_id}")
    int selectJourney(@Param("journey_id") Integer journeyId);

    /**
     * 支付行程
     * @param journeyId
     * @return
     */
    @Update("UPDATE journey SET paid=1, update_time=CURRENT_TIMESTAMP WHERE journey_id=#{journey_id}")
    int payJourney(@Param("journey_id") Integer journeyId);

    /**
     * 获取单个行程
     * @param journeyId
     * @return
     */
    @Select("SELECT * FROM journey WHERE journey_id=#{journey_id}")
    JourneyDO getJourneyByJourneyId(@Param("journey_id") Integer journeyId);

    /**
     * 根据地区获取行程
     * @param cityName
     * @return
     */
    @Select("SELECT * FROM journey WHERE city_name=#{city_name}")
    JourneyDO[] getJourneyByCityName(@Param("city_name") String cityName);
}
