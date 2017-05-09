package com.xay.Controller;

import com.xay.Domain.BaseResult;
import com.xay.Domain.OrderDomain;
import com.xay.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public BaseResult<Object> postOrder(@RequestBody OrderDomain orderDomain){
        return orderService.postOrder(orderDomain);
    }

    @RequestMapping(value = "/orders/guide", method = RequestMethod.GET)
    public BaseResult<Object> getOrdersByGuideName(@RequestParam("gUsername") String gUsername){
        return orderService.getOrdersByGuideName(gUsername);
    }

    @RequestMapping(value = "/orders/customer", method = RequestMethod.GET)
    public BaseResult<Object> getOrdersByCustomerName(@RequestParam("cUsername") String cUsername){
        return orderService.getOrdersByCustomerName(cUsername);
    }

    @RequestMapping(value = "/orders/id", method = RequestMethod.GET)
    public BaseResult<Object> getOrder(@RequestParam("orderId")Integer orderId){
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