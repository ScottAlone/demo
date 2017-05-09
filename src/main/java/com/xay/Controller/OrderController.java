package com.xay.Controller;

import com.xay.Domain.BaseResult;
import com.xay.Domain.OrderDomain;
import com.xay.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public BaseResult<Object> postJourney(@RequestBody OrderDomain orderDomain){
        return orderService.postOrder(orderDomain);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public BaseResult<Object> getJourneyByCustomerId(Integer guideId){
        return orderService.getOrders(guideId);
    }

    @RequestMapping(value = "/orders/order", method = RequestMethod.GET)
    public BaseResult<Object> getJourney(Integer orderId){
        return orderService.getOrderById(orderId);
    }

    @RequestMapping(value = "/orders/pay", method = RequestMethod.PATCH)
    public BaseResult<Object> createOrder(@RequestBody OrderDomain orderDomain){
        return orderService.payOrder(orderDomain);
    }

    @RequestMapping(value = "/orders/deliver", method = RequestMethod.PATCH)
    public BaseResult<Object> deliverOrder(@RequestBody OrderDomain orderDomain){
        return orderService.deliverOrder(orderDomain);
    }

    @RequestMapping(value = "/orders/finish", method = RequestMethod.PATCH)
    public BaseResult<Object> finishOrder(@RequestBody OrderDomain orderDomain){
        return orderService.finishOrder(orderDomain);
    }
}