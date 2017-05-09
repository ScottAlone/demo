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
     * @param orderDomain
     * @return
     */
    BaseResult<Object> payOrder(OrderDomain orderDomain);

    /**
     * 交付订单
     * @param orderDomain
     * @return
     */
    BaseResult<Object> deliverOrder(OrderDomain orderDomain);

    /**
     * 完成订单
     * @param orderDomain
     * @return
     */
    BaseResult<Object> finishOrder(OrderDomain orderDomain);

    /**
     * 根据导游名获取所有订单
     * @param gUsername
     * @return
     */
    BaseResult<Object> getOrdersByGuideName(String gUsername);

    /**
     * 根据客户名获取所有订单
     * @param cUsername
     * @return
     */
    BaseResult<Object> getOrdersByCustomerName(String cUsername);

    /**
     * 根据订单id获取订单
     * @param orderId
     * @return
     */
    BaseResult<Object> getOrderById(Integer orderId);
}
