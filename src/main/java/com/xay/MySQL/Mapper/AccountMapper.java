package com.xay.MySQL.Mapper;

import com.xay.MySQL.DO.AccountDO;
import com.xay.MySQL.DO.GuideDO;
import org.apache.ibatis.annotations.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Mapper
public interface AccountMapper {
    /**
     * 新建客户
     * @param accountDO
     * @return
     */
    @Insert("INSERT INTO customers(name, username, password) VALUES(#{name}, #{username}, #{password})")
    int insertCustomer(AccountDO accountDO);

    /**
     * 新建导游
     * @param accountDO
     * @return
     */
    @Insert("INSERT INTO guides(name, username, password) VALUES(#{name}, #{username}, #{password})")
    int insertGuide(AccountDO accountDO);

    /**
     *  更新客户
     * @param accountDO
     * @return
     */
    @Update("UPDATE customers SET name=#{name}, password=#{password}, update_time=CURRENT_TIMESTAMP WHERE binary username=#{username}")
    int updateCustomer(AccountDO accountDO);

    /**
     * 更新导游
     * @param accountDO
     * @return
     */
    @Update("UPDATE guides SET name=#{name}, password=#{password}, update_time=CURRENT_TIMESTAMP WHERE binary username=#{username}")
    int updateGuide(AccountDO accountDO);

    /**
     * 根据用户名查询导游
     * @param userName
     * @return
     */
    @Select("SELECT * FROM guides WHERE binary username=#{username}")
    AccountDO getGuideByUsername(@Param("username") String userName);

    /**
     * 根据用户名查询客户
     * @param userName
     * @return
     */
    @Select("SELECT * FROM customers WHERE binary username=#{username}")
    AccountDO getCustomerByUsername(@Param("username") String userName);

    /**
     * 导游头像上传
     * @param accountDO
     * @return
     */
    @Update("UPDATE guides SET file=#{file,jdbcType=BLOB} WHERE binary username=#{username}")
    int insertGuideImage(AccountDO accountDO);

    /**
     * 客户头像上传
     * @param accountDO
     * @return
     */
    @Update("UPDATE customers SET file=#{file,jdbcType=BLOB} WHERE binary username=#{username}")
    int insertCustomerImage(AccountDO accountDO);

    /**
     * 导游头像更新
     * @param accountDO
     * @return
     */
    @Update("UPDATE guides SET file=#{file,jdbcType=BLOB}, update_time=CURRENT_TIMESTAMP WHERE binary username=#{username}")
    int updateGuideImg(AccountDO accountDO);

    /**
     * 客户头像更新
     * @param accountDO
     * @return
     */
    @Update("UPDATE customers SET file=#{file,jdbcType=BLOB}, update_time=CURRENT_TIMESTAMP WHERE binary username=#{username}")
    int updateCustomerImg(AccountDO accountDO);

    /**
     * 根据地区获取导游信息
     * @param cityName
     * @return
     */
    @Select("SELECT file, username, stars, phone_num FROM guides WHERE city_name=#{cityName}")
    GuideDO[] getGuides(@Param("cityName") String cityName);

    /**
     * 获取导游信息
     * @param gUsername
     * @return
     */
    @Select("SELECT username, stars, balance, served, phone_num, city_name FROM guides WHERE username=#{gUsername}")
    GuideDO getGuideByName(@Param("gUsername") String gUsername);

    /**
     * 支付并评分
     * @param guideDO
     * @return
     */
    @Update("UPDATE guides SET stars=#{stars}, balance=#{balance}, served=#{served} WHERE username=#{username}")
    int payGuide(GuideDO guideDO);

    /**
     * 修改地区和联系方式
     * @param guideDO
     * @return
     */
    @Update("UPDATE guides SET city_name=#{city_name}, phone_num=#{phone_num} WHERE username=#{username}")
    int updateGuideInfo(GuideDO guideDO);
}
