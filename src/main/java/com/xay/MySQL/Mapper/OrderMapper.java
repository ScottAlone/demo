package com.xay.MySQL.Mapper;

import com.xay.MySQL.DO.OrderDO;
import org.apache.ibatis.annotations.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
@Mapper
public interface OrderMapper {
    /**
     * 新建订单
     * @param orderDO
     * @return
     */
    @Insert("INSERT INTO orders(journey_id, c_username, g_username, price) " +
            "VALUES(#{journey_id}, #{c_username}, #{g_username}, #{price})")
    int postOrder(OrderDO orderDO);

    /**
     * 支付订单
     * @param order_id
     * @return
     */
    @Update("UPDATE orders SET status=1, pay_time=CURRENT_TIMESTAMP WHERE order_id=#{order_id}")
    int payOrder(@Param("order_id")Integer order_id);

    /**
     * 交付订单
     * @param order_id
     * @return
     */
    @Update("UPDATE orders SET status=2, deliver_time=CURRENT_TIMESTAMP WHERE order_id=#{order_id}")
    int deliverOrder(@Param("order_id")Integer order_id);

    /**
     * 完成订单
     * @param order_id
     * @return
     */
    @Update("UPDATE orders SET status=3, deal_time=CURRENT_TIMESTAMP WHERE order_id=#{order_id}")
    int finishOrder(@Param("order_id")Integer order_id);

    /**
     * 获取导游所有订单
     * @param g_username
     * @return
     */
    @Select("SELECT * FROM orders WHERE g_username=#{g_username}")
    OrderDO[] getOrdersByGuideName(@Param("g_username")String g_username);

    /**
     * 根据导游名和订单状态筛选订单
     * @param g_username
     * @param status
     * @return
     */
    @Select("SELECT * FROM orders WHERE g_username=#{g_username} AND status=#{status}")
    OrderDO[] getOrdersByGuideNameAndStatus(@Param("g_username")String g_username, @Param("status")Integer status);

    /**
     * 获取客户所有订单
     * @param c_username
     * @return
     */
    @Select("SELECT * FROM orders WHERE c_username=#{c_username}")
    OrderDO[] getOrdersByCustomerName(@Param("c_username")String c_username);

    /**
     * 根据客户名和订单状态筛选订单
     * @param c_username
     * @param status
     * @return
     */
    @Select("SELECT * FROM orders WHERE c_username=#{c_username} AND status=#{status}")
    OrderDO[] getOrdersByCustomerNameAndStatus(@Param("c_username")String c_username, @Param("status")Integer status);

    /**
     * 获取单个订单信息
     * @param orderId
     * @return
     */
    @Select("SELECT * FROM orders WHERE order_id=#{order_id}")
    OrderDO getOrderById(@Param("order_id") Integer orderId);

    /**
     * 根据行程获取单个订单信息
     * @param journey_id
     * @return
     */
    @Select("SELECT * FROM orders WHERE journey_id=#{journey_id}")
    OrderDO getOrderByJourneyId(@Param("journey_id") Integer journey_id);
}
