package com.example.springbootjwtsecurity.repository;

import com.example.springbootjwtsecurity.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PublicationRepository extends JpaRepository<Publication, Long> {
}