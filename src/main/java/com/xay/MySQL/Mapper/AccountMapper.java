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
    @Update("UPDATE customers SET name=#{name}, password=#{password}, update_time=CURRENT_TIMESTAMP WHERE username=#{username}")
    int updateCustomer(AccountDO accountDO);

    /**
     * 更新导游
     * @param accountDO
     * @return
     */
    @Update("UPDATE guides SET name=#{name}, password=#{password}, update_time=CURRENT_TIMESTAMP WHERE username=#{username}")
    int updateGuide(AccountDO accountDO);

    /**
     * 根据用户名查询导游
     * @param userName
     * @return
     */
    @Select("SELECT * FROM guides WHERE username=#{username}")
    AccountDO getGuideByUsername(@Param("username") String userName);

    /**
     * 根据用户名查询客户
     * @param userName
     * @return
     */
    @Select("SELECT * FROM customers WHERE username=#{username}")
    AccountDO getCustomerByUsername(@Param("username") String userName);

    /**
     * 根据ID查询导游
     * @param guideId
     * @return
     */
    @Select("SELECT * FROM customers WHERE guide_id=#{guide_id}")
    AccountDO getGuiderByUserId(@Param("guide_id") Integer guideId);

    /**
     * 根据ID查询客户
     * @param customerId
     * @return
     */
    @Select("SELECT * FROM customers WHERE customer_id=#{customer_id}")
    AccountDO getCustomerByUserId(@Param("customer_id") Integer customerId);
}
