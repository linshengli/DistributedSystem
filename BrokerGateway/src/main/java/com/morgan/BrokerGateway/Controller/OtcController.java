package com.morgan.BrokerGateway.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.morgan.BrokerGateway.Dao.OrderRepository;

import org.springframework.web.bind.annotation.ResponseBody;
import com.morgan.BrokerGateway.Entity.Order;

@RestController
@RequestMapping("/otest")
public class OtcController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	//所有订单
	@RequestMapping("orderAll")
	@ResponseBody
	public List<Order> orderAll(){
		List<Order> orders = orderRepository.findAll();
		return orders;
	}
	//根据id查找
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order getById(@PathVariable("id") String id){
		int iid = Integer.parseInt(id);
        Order order = orderRepository.findById(iid);
        return order;    
    }
	
	

}
