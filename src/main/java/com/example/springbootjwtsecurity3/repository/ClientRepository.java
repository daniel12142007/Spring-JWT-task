package com.example.springbootjwtsecurity3.repository;

import com.example.springbootjwtsecurity3.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select count(o) from Client client join client.orders o where client.id = :id")
    long findByCount(@Param(value = "id") Long id);
}