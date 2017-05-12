package com.xay.Service;

import com.xay.Domain.BaseResult;
import com.xay.Domain.OrderDomain;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public interface OrderService {
    /**
     * 提交订单
     * @param orderDomain
     * @return
     */
    BaseResult<Object> postOrder(OrderDomain orderDomain);

    /**
     * 支付订单
     * @param orderId
     * @return
     */
    BaseResult<Object> payOrder(Integer orderId);

    /**
     * 接受订单
     * @param orderId
     * @return
     */
    BaseResult<Object> acceptOrder(Integer orderId);

    /**
     * 交付订单
     * @param orderId
     * @return
     */
    BaseResult<Object> deliverOrder(Integer orderId);

    /**
     * 完成订单
     * @param orderId
     * @return
     */
    BaseResult<Object> finishOrder(Integer orderId);

    /**
     * 根据导游名获取所有订单
     * @param gUsername
     * @return
     */
    BaseResult<Object> getOrdersByGuideName(String gUsername);

    /**
     * 根据导游名和订单状态筛选订单
     * @param gUsername
     * @return
     */
    BaseResult<Object> getOrdersByGuideNameAndStatus(String gUsername, Integer status);

    /**
     * 根据客户名获取所有订单
     * @param cUsername
     * @return
     */
    BaseResult<Object> getOrdersByCustomerName(String cUsername);

    /**
     * 根据客户名和订单状态筛选订单
     * @param cUsername
     * @return
     */
    BaseResult<Object> getOrdersByCustomerNameAndStatus(String cUsername,Integer status);

    /**
     * 根据订单id获取订单
     * @param orderId
     * @return
     */
    BaseResult<Object> getOrderById(Integer orderId);
}
