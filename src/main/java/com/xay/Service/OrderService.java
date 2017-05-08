package com.xay.Service;


import com.xay.Domain.BaseResult;
import com.xay.Domain.OrderDomain;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public interface OrderService {
    BaseResult payOrder(OrderDomain orderDomain);
    BaseResult deliverOrder(OrderDomain orderDomain);
    BaseResult finishOrder(OrderDomain orderDomain);
    BaseResult postOrder(OrderDomain orderDomain);
    OrderDomain[] getOrders(Integer guideId);
    OrderDomain getOrderById(Integer orderId);
}
