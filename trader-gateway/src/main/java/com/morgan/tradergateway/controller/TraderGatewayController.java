package com.morgan.tradergateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.morgan.tradergateway.dao.TraderRepository;
import com.morgan.tradergateway.model.Order;
import com.morgan.tradergateway.model.Trader;

@RestController
@CrossOrigin
public class TraderGatewayController {

    @GetMapping("/helloworld")
    public String helloworld() {
        return "helloworld";
    }
    /*
     * 测试用例:1.ui点击orderBlotter按钮，触发GET，发送下拉框选择的brokerCompany，返回该brokerCompany的orderBlotter
     */
    @GetMapping("/orderBlotter/{BrokerCompany}/{product}")
    public List<Order> getOrderBlotter(@PathVariable String brokerCompany,@PathVariable String product) throws Exception{
        return Communication.getOrderBlotter(brokerCompany,product);
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
    
    @PostMapping("/login")
    public String login(@RequestBody Trader trader) {
    	Trader trader_in_db = traderRepository.findByUsername(trader.getUsername());
    	if(trader_in_db.getPassword().equals(trader.getPassword())){
    		return "success";
    	}else{
    		return "fail";
    	}
    }
}