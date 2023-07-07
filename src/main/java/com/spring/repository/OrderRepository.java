package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Orders;


public interface OrderRepository extends JpaRepository<Orders, Integer> {
	@Query(value = "SELECT SUM(orderprice) \r\n" + "FROM orders", nativeQuery = true)
	double tongtiendonhang();
}
