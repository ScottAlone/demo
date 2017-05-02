package com.xay.Controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/2.
 */
@RestController
@EnableAutoConfiguration
public class test {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}