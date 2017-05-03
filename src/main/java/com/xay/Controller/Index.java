package com.xay.Controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/2.
 */
@RestController
@EnableAutoConfiguration
public class Index {
    @RequestMapping(value = "/greet", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name){
        return "Hello " + name ;
    }
}