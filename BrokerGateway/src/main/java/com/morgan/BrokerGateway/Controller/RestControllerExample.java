package com.morgan.BrokerGateway.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerExample {

    @RequestMapping(value = "/test")
    public String test() {
        return "789";
    }

}
