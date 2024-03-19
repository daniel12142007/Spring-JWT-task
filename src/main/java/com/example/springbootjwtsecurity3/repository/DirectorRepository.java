package com.example.springbootjwtsecurity3.repository;

import com.example.springbootjwtsecurity3.dto.response.CompanyResponse;
import com.example.springbootjwtsecurity3.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface DirectorRepository extends JpaRepository<Director, Long> {

}