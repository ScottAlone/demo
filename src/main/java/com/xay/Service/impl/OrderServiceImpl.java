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

import java.text.SimpleDateFormat;

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

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private BaseResult<Object> DOSToDomains(OrderDO[] orderDOS){
        OrderDomain[] orderDomains = new OrderDomain[orderDOS.length];
        if (orderDomains.length == 0){
            return new BaseResult<>(500, "No order found");
        }
        for (int i = 0; i < orderDOS.length; i++){
            orderDomains[i] = new OrderDomain(orderDOS[i],
                    (orderDOS[i].getCreate_time()==null)?"null":simpleDateFormat.format(orderDOS[i].getCreate_time()),
                    (orderDOS[i].getPay_time()==null)?"null":simpleDateFormat.format(orderDOS[i].getPay_time()),
                    (orderDOS[i].getDeliver_time()==null)?"null":simpleDateFormat.format(orderDOS[i].getDeliver_time()),
                    (orderDOS[i].getDeal_time()==null)?"null":simpleDateFormat.format(orderDOS[i].getDeal_time()));
        }
        return new BaseResult<>(orderDomains);
    }

    @Override
    public BaseResult<Object> postOrder(OrderDomain orderDomain) {
        OrderDO orderDO = orderMapper.getOrderByJourneyId(orderDomain.getJourneyId());
        if (orderDO != null){
            return new BaseResult<>(500, "Order has already been created");
        }
        AccountDO customer = accountMapper.getCustomerByUsername(orderDomain.getcUsername());
        AccountDO guide = accountMapper.getGuideByUsername(orderDomain.getgUsername());
        JourneyDO journeyDO = journeyMapper.getJourneyByJourneyId(orderDomain.getJourneyId());
        if (customer != null && guide != null){
            if (journeyDO != null){
                orderMapper.postOrder(new OrderDO(orderDomain));
                return new BaseResult<>();
            }else return new BaseResult<>(500, "No journey found");
        }else if (customer == null){
            return new BaseResult<>(500, "No customer found");
        }
        return new BaseResult<>(500, "No guide found");
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
            return new BaseResult<>(new OrderDomain(orderDO,
                    (orderDO.getCreate_time()==null)?"null":simpleDateFormat.format(orderDO.getCreate_time()),
                    (orderDO.getPay_time()==null)?"null":simpleDateFormat.format(orderDO.getPay_time()),
                    (orderDO.getDeliver_time()==null)?"null":simpleDateFormat.format(orderDO.getDeliver_time()),
                    (orderDO.getDeal_time()==null)?"null":simpleDateFormat.format(orderDO.getDeal_time())));
        }
        return new BaseResult<>(500, "No order found");
    }

    @Override
    public BaseResult<Object> acceptOrder(Integer orderId) {
        OrderDO orderDO = orderMapper.getOrderById(orderId);
        if (orderDO != null){
            if (orderDO.getStatus() >= 2){
                return new BaseResult<>(500, "Order has already been accepted");
            }else orderMapper.acceptOrder(orderId);
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "No order found");
    }

    @Override
    public BaseResult<Object> payOrder(Integer orderId) {
        OrderDO orderDO = orderMapper.getOrderById(orderId);
        if (orderDO != null){
            if (orderDO.getStatus() >= 1){
                return new BaseResult<>(500, "Order has already been paid");
            }else orderMapper.payOrder(orderId);
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "No order found");
    }

    @Override
    public BaseResult<Object> deliverOrder(Integer orderId) {
        OrderDO orderDO = orderMapper.getOrderById(orderId);
        if (orderDO != null){
            if (orderDO.getStatus() >= 3){
                return new BaseResult<>(500, "Order has already been delivered");
            }else orderMapper.deliverOrder(orderId);
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "No order found");
    }

    @Override
    public BaseResult<Object> finishOrder(Integer orderId) {
        OrderDO orderDO = orderMapper.getOrderById(orderId);
        if (orderDO != null){
            if (orderDO.getStatus() >= 4){
                return new BaseResult<>(500, "Order has already finished");
            }else orderMapper.finishOrder(orderId);
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "No order found");
    }
}
