package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.CustomerDto;
import com.example.demo.exception.UserException;

import jakarta.validation.Valid;

public interface CustomerService {

	List<CustomerDto> getAllCustomers(CustomerDto customerDto);

	ResponseEntity<?> getCustomerById(Long id);

	Map<String, String> createUser(@Valid CustomerDto customerDto);

	ResponseEntity<?> updateCustomer(Long id, @Valid CustomerDto customerDetails);

	boolean deleteCustomer(Long id) throws UserException;

	//Map<String, String> updateForCustomer(Long id, CustomerDto customerDto);

}
