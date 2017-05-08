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
     * 获取用户所有订单
     * @param guideId
     * @return
     */
    @Select("SELECT * FROM orders WHERE guide_id=#{guide_id}")
    OrderDO[] getOrders(@Param("guide_id")Integer guideId);

    /**
     * 新建订单
     * @param orderDO
     * @return
     */
    @Insert("INSERT INTO orders(customer_id, guide_id, price, status) " +
            "VALUES(#{customer_id}, #{guide_id}, #{price}, #{status})")
    int postOrder(OrderDO orderDO);

    /**
     * 支付订单
     * @param orderDO
     * @return
     */
    @Update("UPDATE orders SET status=#{status}, pay_time=CURRENT_TIMESTAMP WHERE order_id=#{order_id}")
    int payOrder(OrderDO orderDO);

    /**
     * 交付订单
     * @param orderDO
     * @return
     */
    @Update("UPDATE orders SET status=#{status}, deliver_time=CURRENT_TIMESTAMP WHERE order_id=#{order_id}")
    int deliverOrder(OrderDO orderDO);

    /**
     * 完成订单
     * @param orderDO
     * @return
     */
    @Update("UPDATE orders SET status=#{status}, deal_time=CURRENT_TIMESTAMP WHERE order_id=#{order_id}")
    int finishOrder(OrderDO orderDO);

    /**
     * 获取单个订单信息
     * @param orderId
     * @return
     */
    @Select("SELECT * FROM orders WHERE order_id=#{order_id}")
    OrderDO getOrderById(@Param("order_id") Integer orderId);
}
