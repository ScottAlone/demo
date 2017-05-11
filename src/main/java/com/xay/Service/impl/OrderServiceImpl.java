package com.xay.Service.impl;

import com.xay.Domain.BaseResult;
import com.xay.Domain.OrderDomain;
import com.xay.MySQL.DO.AccountDO;
import com.xay.MySQL.DO.JourneyDO;
import com.xay.MySQL.DO.OrderDO;
import com.xay.MySQL.Mapper.AccountMapper;
import com.xay.MySQL.Mapper.JourneyMapper;
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
    JourneyMapper journeyMapper;

    @Autowired
    private OrderMapper orderMapper;

    private BaseResult<Object> DOSToDomains(OrderDO[] orderDOS){
        OrderDomain[] orderDomains = new OrderDomain[orderDOS.length];
        if (orderDomains.length == 0){
            return new BaseResult<>(500, "没有订单");
        }
        for (int i = 0; i < orderDOS.length; i++){
            orderDomains[i] = new OrderDomain(orderDOS[i]);
        }
        return new BaseResult<>(orderDomains);
    }

    @Override
    public BaseResult<Object> postOrder(OrderDomain orderDomain) {
        OrderDO orderDO = orderMapper.getOrderByJourneyId(orderDomain.getJourneyId());
        if (orderDO != null){
            return new BaseResult<>(500, "请勿重复创建订单");
        }
        AccountDO customer = accountMapper.getCustomerByUsername(orderDomain.getcUsername());
        AccountDO guide = accountMapper.getGuideByUsername(orderDomain.getgUsername());
        JourneyDO journeyDO = journeyMapper.getJourneyByJourneyId(orderDomain.getJourneyId());
        if (customer != null && guide != null){
            if (journeyDO != null){
                orderMapper.postOrder(new OrderDO(orderDomain));
                return new BaseResult<>();
            }else return new BaseResult<>(500, "行程不存在");
        }else if (customer == null){
            return new BaseResult<>(500, "客户不存在");
        }
        return new BaseResult<>(500, "导游不存在");
    }

    @Override
    public BaseResult<Object> getOrdersByGuideName(String gUsername) {
        OrderDO[] orderDOS = orderMapper.getOrdersByGuideName(gUsername);
        return DOSToDomains(orderDOS);
    }

    @Override
    public BaseResult<Object> getOrdersByGuideNameAndStatus(String gUsername, Integer status) {
        OrderDO[] orderDOS = orderMapper.getOrdersByGuideNameAndStatus(gUsername, status);
        return DOSToDomains(orderDOS);
    }

    @Override
    public BaseResult<Object> getOrdersByCustomerName(String cUsername) {
        OrderDO[] orderDOS = orderMapper.getOrdersByCustomerName(cUsername);
        return DOSToDomains(orderDOS);
    }

    @Override
    public BaseResult<Object> getOrdersByCustomerNameAndStatus(String cUsername, Integer status) {
        OrderDO[] orderDOS = orderMapper.getOrdersByCustomerNameAndStatus(cUsername, status);
        return DOSToDomains(orderDOS);
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
    public BaseResult<Object> payOrder(Integer orderId) {
        OrderDO orderDO = orderMapper.getOrderById(orderId);
        if (orderDO != null){
            orderMapper.payOrder(orderId);
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "订单不存在");
    }

    @Override
    public BaseResult<Object> deliverOrder(Integer orderId) {
        OrderDO orderDO = orderMapper.getOrderById(orderId);
        if (orderDO != null){
            orderMapper.deliverOrder(orderId);
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "订单不存在");
    }

    @Override
    public BaseResult<Object> finishOrder(Integer orderId) {
        OrderDO orderDO = orderMapper.getOrderById(orderId);
        if (orderDO != null){
            orderMapper.finishOrder(orderId);
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "订单不存在");
    }
}
