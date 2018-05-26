package com.morgan.BrokerGateway.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.morgan.BrokerGateway.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	Order findById(int id);
}
