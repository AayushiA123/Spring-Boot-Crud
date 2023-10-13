package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerDto;
import com.example.demo.exception.UserException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

import jakarta.validation.Valid;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;

	// get all customers
	@GetMapping("/customers")
	public List<CustomerDto> getAllCustomers(@RequestBody CustomerDto customerDto) {
		return customerService.getAllCustomers(customerDto);
	}

	//create customer
	@PostMapping("/customers")
	public Map<String, String> createUser(@Valid @RequestBody CustomerDto customerDto) {
		return customerService.createUser(customerDto);
	}

	// get customer by id rest api
	@GetMapping("/customers/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}

	// update customer rest api
	@PutMapping("/customers/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDetails) {
		return customerService.updateCustomer(id, customerDetails);
	}

	// delete customer rest api
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id) throws UserException {
		boolean deleted = customerService.deleteCustomer(id);
		if (deleted) {
			return ResponseEntity.ok("Record deleted successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
		}
	}

}