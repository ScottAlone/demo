package com.xay.Service;

import com.xay.Domain.BaseResult;
import com.xay.Domain.OrderDomain;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
public interface OrderService {
    BaseResult<Object> payOrder(OrderDomain orderDomain);
    BaseResult<Object> deliverOrder(OrderDomain orderDomain);
    BaseResult<Object> finishOrder(OrderDomain orderDomain);
    BaseResult<Object> postOrder(OrderDomain orderDomain);
    BaseResult<Object> getOrders(Integer guideId);
    BaseResult<Object> getOrderById(Integer orderId);
}
