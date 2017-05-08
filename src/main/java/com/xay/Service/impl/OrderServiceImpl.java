package com.xay.Service.impl;

import com.xay.Domain.BaseResult;
import com.xay.Domain.OrderDomain;
import com.xay.MySQL.DO.OrderDO;
import com.xay.MySQL.Mapper.OrderMapper;
import com.xay.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public BaseResult postOrder(OrderDomain orderDomain) {
        orderMapper.postOrder(new OrderDO(orderDomain));
        return new BaseResult();
    }

    @Override
    public OrderDomain[] getOrders(Integer customerId) {
        OrderDO[] orderDOS = orderMapper.getOrders(customerId);
        OrderDomain[] orderDomains = new OrderDomain[orderDOS.length];
        if (orderDomains.length == 0){
            return null;
        }
        for (int i = 0; i < orderDOS.length; i++){
            orderDomains[i] = new OrderDomain(orderDOS[i]);
        }
        orderMapper.getOrders(customerId);
        return orderDomains;
    }

    @Override
    public OrderDomain getOrderById(Integer orderId) {
        OrderDO orderDO = orderMapper.getOrderById(orderId);
        if (orderDO != null){
            return new OrderDomain(orderDO);
        }
        return null;
    }

    @Override
    public BaseResult payOrder(OrderDomain orderDomain) {
        OrderDO orderDO = orderMapper.getOrderById(orderDomain.getOrderId());
        if (orderDO != null){
            orderMapper.payOrder(new OrderDO(orderDomain));
            return new BaseResult();
        }
        return new BaseResult(500, "订单不存在");
    }

    @Override
    public BaseResult deliverOrder(OrderDomain orderDomain) {
        OrderDO orderDO = orderMapper.getOrderById(orderDomain.getOrderId());
        if (orderDO != null){
            orderMapper.deliverOrder(new OrderDO(orderDomain));
            return new BaseResult();
        }
        return new BaseResult(500, "订单不存在");
    }

    @Override
    public BaseResult finishOrder(OrderDomain orderDomain) {
        OrderDO orderDO = orderMapper.getOrderById(orderDomain.getOrderId());
        if (orderDO != null){
            orderMapper.finishOrder(new OrderDO(orderDomain));
            return new BaseResult();
        }
        return new BaseResult(500, "订单不存在");
    }
}
