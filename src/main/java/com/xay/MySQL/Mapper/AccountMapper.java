package com.xay.MySQL.Mapper;

import com.xay.MySQL.DO.AccountDO;
import org.apache.ibatis.annotations.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Mapper
public interface AccountMapper {
    /**
     * 新建客户
     */
    @Insert("INSERT INTO customers(name, username, password) VALUES(#{name}, #{username}, #{password})")
    int insertCustomer(AccountDO accountDO);

    /**
     * 新建导游
     */
    @Insert("INSERT INTO guides(name, username, password) VALUES(#{name}, #{username}, #{password})")
    int insertGuide(AccountDO accountDO);

    /**
     * 更新客户
     */
    @Update("UPDATE customers SET name=#{name}, password=#{password}, update_time=CURRENT_TIMESTAMP WHERE username=#{username}")
    int updateCustomer(AccountDO accountDO);

    /**
     * 更新导游
     */
    @Update("UPDATE guides SET name=#{name}, password=#{password}, update_time=CURRENT_TIMESTAMP WHERE username=#{username}")
    int updateGuide(AccountDO accountDO);

    /**
     *查询导游
     */
    @Select("SELECT * FROM guides WHERE username=#{username}")
    AccountDO getGuideByUsername(@Param("username") String userName);

    /**
     *查询客户
     */
    @Select("SELECT * FROM customers WHERE username=#{username}")
    AccountDO getCustomerByUsername(@Param("username") String userName);
}
