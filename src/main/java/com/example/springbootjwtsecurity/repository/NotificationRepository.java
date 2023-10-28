package com.example.springbootjwtsecurity.repository;

import com.example.springbootjwtsecurity.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}