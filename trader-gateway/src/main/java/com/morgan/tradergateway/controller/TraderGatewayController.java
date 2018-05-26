package com.morgan.tradergateway.controller;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.morgan.tradergateway.dao.TraderRepository;
import com.morgan.tradergateway.model.Order;
import com.morgan.tradergateway.model.Trader;

@RestController
public class TraderGatewayController {

    @GetMapping("/helloworld")
    public String helloworld() {
        return "helloworld";
    }
    /*
     * 测试用例:1.ui点击orderBlotter按钮，触发GET，发送下拉框选择的brokerCompany，返回该brokerCompany的orderBlotter
     */
    @GetMapping("/orderBlotter/{BrokerCompany}")
    public List<Order> getOrderBlotter(@PathVariable String brokerCompany) throws Exception{
        return Communication.getOrderBlotter(brokerCompany);
    }
    /*
     * 测试用例：1.ui确定发送一个order后，将该order添加入下拉框选择的brokerCompany，返回添加成功后的order，
     * （同时需要实现一旦order成交后返回成交结果）
     */
    @PostMapping("/addOrder/{BrokerCompany}")
    public Order addOrder(@PathVariable String brokerCompany,@RequestBody Order order) throws Exception{
    	return Communication.addOrder(order,brokerCompany);
    }
    
    @Autowired
    private TraderRepository traderRepository;
    
    @PostMapping("/addPerson")
    public String addPerson(@RequestBody Trader trader) {
    	traderRepository.save(trader);
    	return "success";
    }
}