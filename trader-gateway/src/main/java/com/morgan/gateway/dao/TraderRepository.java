package com.morgan.gateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.morgan.gateway.Entity.Trader;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Integer> {
	Trader findById(int id);
	Trader findByUsername(String username);
	Trader findByUsernameAndPassword(String username,String password);
}