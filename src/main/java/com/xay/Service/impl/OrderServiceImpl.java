package com.xay.Service.impl;

import com.xay.Domain.BaseResult;
import com.xay.Domain.OrderDomain;
import com.xay.MySQL.DO.AccountDO;
import com.xay.MySQL.DO.OrderDO;
import com.xay.MySQL.Mapper.AccountMapper;
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
    AccountMapper accountMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public BaseResult<Object> postOrder(OrderDomain orderDomain) {
        AccountDO customer = accountMapper.getCustomerByUserId(orderDomain.getCustomerId());
        AccountDO guide = accountMapper.getGuideByUserId(orderDomain.getGuideId());
        if (customer != null && guide != null){
            orderMapper.postOrder(new OrderDO(orderDomain));
        }else if (customer == null){
            return new BaseResult<>(500, "客户不存在");
        }
        return new BaseResult<>(500, "导游不存在");
    }

    @Override
    public BaseResult<Object> getOrders(Integer customerId) {
        OrderDO[] orderDOS = orderMapper.getOrders(customerId);
        OrderDomain[] orderDomains = new OrderDomain[orderDOS.length];
        if (orderDomains.length == 0){
            return new BaseResult<>(500, "没有订单");
        }
        for (int i = 0; i < orderDOS.length; i++){
            orderDomains[i] = new OrderDomain(orderDOS[i]);
        }
        orderMapper.getOrders(customerId);
        return new BaseResult<>(orderDomains);
    }

    @Override
    public BaseResult<Object> getOrderById(Integer orderId) {
        OrderDO orderDO = orderMapper.getOrderById(orderId);
        if (orderDO != null){
            return new BaseResult<>(new OrderDomain(orderDO));
        }
        return new BaseResult<>(500, "订单不存在");
    }

    @Override
    public BaseResult<Object> payOrder(OrderDomain orderDomain) {
        OrderDO orderDO = orderMapper.getOrderById(orderDomain.getOrderId());
        if (orderDO != null){
            orderMapper.payOrder(new OrderDO(orderDomain));
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "订单不存在");
    }

    @Override
    public BaseResult<Object> deliverOrder(OrderDomain orderDomain) {
        OrderDO orderDO = orderMapper.getOrderById(orderDomain.getOrderId());
        if (orderDO != null){
            orderMapper.deliverOrder(new OrderDO(orderDomain));
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "订单不存在");
    }

    @Override
    public BaseResult<Object> finishOrder(OrderDomain orderDomain) {
        OrderDO orderDO = orderMapper.getOrderById(orderDomain.getOrderId());
        if (orderDO != null){
            orderMapper.finishOrder(new OrderDO(orderDomain));
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "订单不存在");
    }
}
