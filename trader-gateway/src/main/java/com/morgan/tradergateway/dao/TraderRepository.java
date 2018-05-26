package com.morgan.tradergateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.morgan.tradergateway.model.Trader;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Integer> {
	Trader findById(int id);
}