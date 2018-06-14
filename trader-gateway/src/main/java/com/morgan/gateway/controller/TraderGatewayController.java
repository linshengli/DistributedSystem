package com.morgan.gateway.controller;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.morgan.gateway.Entity.Order;
import com.morgan.gateway.Entity.Trader;
import com.morgan.gateway.dao.TraderRepository;
import com.morgan.gateway.rmi.OrderService;

@RestController
@CrossOrigin
public class TraderGatewayController {

	public List<String> BrokerCompany = Arrays.asList("SHFE","CDE");
	public String TraderCompany = "morganstanley";
	
	@Autowired
	@Qualifier("rmiService")
	private Map<String,RmiProxyFactoryBean> factoryBeans;
	
    @GetMapping("/helloworld")
    public String helloworld() throws Exception {
    	OrderService service=(OrderService)factoryBeans.get("SHFE").getObject();
    	service.hello();
        return "helloworld";
    }
    /*
     * 测试用例:1.ui点击orderBlotter按钮，触发GET，发送下拉框选择的brokerCompany，返回该brokerCompany的orderBlotter
     */
    @GetMapping("/orderBlotter/{BrokerCompany}/{product}")
    public List<Order> getOrderBlotter(@PathVariable(name="BrokerCompany") String brokerCompany,@PathVariable(name="product") String product) throws Exception{
    	System.out.println(brokerCompany);
    	System.out.println(product);
    	OrderService service=(OrderService)factoryBeans.get(brokerCompany).getObject();
    	return service.GetOrderBlotter(product);
    }
    /*
     * 测试用例：1.ui确定发送一个order后，将该order添加入下拉框选择的brokerCompany，返回添加成功后的order，
     * （同时需要实现一旦order成交后返回成交结果）
     */
    @PostMapping("/addOrder/{BrokerCompany}/{isLarge}")
    public void addOrder(@PathVariable(name="BrokerCompany") String brokerCompany,@PathVariable(name="isLarge") int isLarge,@RequestBody Order order) throws Exception{
    	System.out.println(brokerCompany);
    	System.out.println("order type"+order.getType());
		order.setItradercom(TraderCompany);
		if(isLarge == 0){
	    	OrderService service=(OrderService)factoryBeans.get(brokerCompany).getObject();
			service.addOrder(order);  
		}else{
			Timer timer = new Timer();
	    	IceburgTask itask = new IceburgTask(brokerCompany,factoryBeans,order);
	    	Date now = new Date();
	    	timer.scheduleAtFixedRate(itask, now, 15 * 1000L);
		}
    }
    
    @Autowired
    private TraderRepository traderRepository;
    
    @PostMapping("/login")
    public String login(@RequestBody Trader trader) {
    	Trader trader_in_db = traderRepository.findByUsernameAndPassword(trader.getUsername(),trader.getPassword());
    	if(trader_in_db==null){
    		return "fail";
    	}else{
    		return "success";
    	}
    }
    /*
     * 获取历史订单
     */
    @GetMapping("/historyOrders/{username}/{page}")
    public List<Order> getHistoryOrders(@PathVariable(name="username") String username,@PathVariable(name="page") int page) throws Exception{
    	System.out.println(username);
    	System.out.println(page);
    	ArrayList<Order> historyOrders = new ArrayList<Order>();
    	for(int i = 0;i < BrokerCompany.size(); i++){
    		OrderService service=(OrderService)factoryBeans.get(BrokerCompany.get(i)).getObject();
        	historyOrders.addAll(service.GetHistoryOrders(username,TraderCompany,page));
    	}
        return historyOrders;
    }
    
    @GetMapping("/cancelOrder/{BrokerCompany}/{orderId}")
    public void cancelOrder(@PathVariable(name="BrokerCompany") String brokerComp,@PathVariable(name="orderId") int orderId) throws Exception{
    	System.out.println(brokerComp);
    	System.out.println(orderId);
    	OrderService service=(OrderService)factoryBeans.get(brokerComp).getObject();
		service.cancelOrder(orderId); 
    }
}