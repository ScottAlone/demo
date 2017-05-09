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
    @Select("SELECT * FROM journey WHERE c_username=#{c_username}")
    JourneyDO[] getJourneyByUsername(@Param("c_username")String c_username);

    /**
     * 新建行程
     * @param journeyDO
     * @return
     */
    @Insert("INSERT INTO journey(journey_name, phone_num, city_id, members, tour_type, low_price, high_price, start_time, end_time, tags, others, c_username, due_date, price) " +
            "VALUES(#{journey_name}, #{phone_num}, #{city_id}, #{members}, #{tour_type}, #{low_price}, #{high_price}, #{start_time}, #{end_time}, #{tags}, #{others}, #{c_username}, #{due_date}, #{price})")
    int postJourney(JourneyDO journeyDO);

    /**
     * 修改行程
     * @param journeyDO
     * @return
     */
    @Update("UPDATE journey SET journey_name=#{journey_name}, phone_num=#{phone_num}, city_id=#{city_id}, members=#{members}, " +
            "tour_type=#{tour_type}, low_price=#{low_price}, high_price=#{high_price}, start_time=#{start_time}, " +
            "end_time=#{end_time}, tags=#{tags}, others=#{others}, due_date=#{due_date}, price=#{price}, update_time=CURRENT_TIMESTAMP WHERE journey_id=#{journey_id}")
    int updateJourney(JourneyDO journeyDO);

    /**
     * 获取单个行程
     * @param journeyId
     * @return
     */
    @Select("SELECT * FROM journey WHERE journey_id=#{journey_id}")
    JourneyDO getJourneyByJourneyId(@Param("journey_id") Integer journeyId);
}
