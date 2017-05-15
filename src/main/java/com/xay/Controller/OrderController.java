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

    @RequestMapping(value = "/orders/guide/status", method = RequestMethod.GET)
    public BaseResult<Object> getOrdersByGuideNameAndStatus(@RequestParam("gUsername") String gUsername,
                                                            @RequestParam("status") Integer status){
        return orderService.getOrdersByGuideNameAndStatus(gUsername, status);
    }

    @RequestMapping(value = "/orders/customer", method = RequestMethod.GET)
    public BaseResult<Object> getOrdersByCustomerName(@RequestParam("cUsername") String cUsername){
        return orderService.getOrdersByCustomerName(cUsername);
    }

    @RequestMapping(value = "/orders/customer/status", method = RequestMethod.GET)
    public BaseResult<Object> getOrdersByCustomerNameAndStatus(@RequestParam("cUsername") String cUsername,
                                                               @RequestParam("status") Integer status){
        return orderService.getOrdersByCustomerNameAndStatus(cUsername, status);
    }

    @RequestMapping(value = "/orders/id", method = RequestMethod.GET)
    public BaseResult<Object> getOrder(@RequestParam("orderId")Integer orderId){
        return orderService.getOrderById(orderId);
    }

    @RequestMapping(value = "/orders/journeyId", method = RequestMethod.GET)
    public BaseResult<Object> getOrderByJourneyId(@RequestParam("journeyId")Integer journeyId){
        return orderService.getOrderByJourneyId(journeyId);
    }

    @RequestMapping(value = "/orders/pay", method = RequestMethod.PATCH)
    public BaseResult<Object> payOrder(@RequestParam("orderId")Integer orderId){
        return orderService.payOrder(orderId);
    }

    @RequestMapping(value = "/orders/accept", method = RequestMethod.PATCH)
    public BaseResult<Object> acceptOrder(@RequestParam("orderId")Integer orderId){
        return orderService.acceptOrder(orderId);
    }

    @RequestMapping(value = "/orders/deliver", method = RequestMethod.PATCH)
    public BaseResult<Object> deliverOrder(@RequestParam("orderId")Integer orderId){
        return orderService.deliverOrder(orderId);
    }

    @RequestMapping(value = "/orders/finish", method = RequestMethod.PATCH)
    public BaseResult<Object> finishOrder(@RequestParam("orderId")Integer orderId){
        return orderService.finishOrder(orderId);
    }
}