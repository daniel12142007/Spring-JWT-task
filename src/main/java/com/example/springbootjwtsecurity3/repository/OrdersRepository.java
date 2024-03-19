package com.example.springbootjwtsecurity3.repository;

import com.example.springbootjwtsecurity3.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}