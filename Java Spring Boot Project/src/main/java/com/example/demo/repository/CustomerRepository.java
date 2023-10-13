package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.CustomerDto;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDto, Long> {

	boolean existsByEmail(String email);

	boolean existsByMobileNumber(String string);

	List<CustomerDto> findAllByOrderByIdDesc();

}