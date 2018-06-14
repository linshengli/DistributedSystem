package com.morgan.gateway.rmi;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.morgan.gateway.rmi.OrderService;

@Configuration
public class RMIClientConfig {

	public static Map<String, String> rmiUrl_brokerComp = new HashMap<String, String>();

	@PostConstruct
	public void init() {
		rmiUrl_brokerComp.put("SHFE", "rmi://192.168.43.96:1099/rmiService");
		rmiUrl_brokerComp.put("CDE", "rmi://192.168.43.96:1098/rmiService");
	}

	@Bean(name = "rmiService")
	public Map<String, RmiProxyFactoryBean> initRmiProxyFactoryBean() throws Exception {
		Map<String, RmiProxyFactoryBean> factoryBeans = new HashMap<String, RmiProxyFactoryBean>();
		for (Map.Entry<String, String> entry : rmiUrl_brokerComp.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			System.out.println("key=" + key + " value=" + value);

	        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();  
	        factoryBean.setServiceUrl(value);  
	        factoryBean.setServiceInterface(OrderService.class);  
	        factoryBean.afterPropertiesSet(); 
			factoryBeans.put(key, factoryBean);
		}
		return factoryBeans;
	}
}