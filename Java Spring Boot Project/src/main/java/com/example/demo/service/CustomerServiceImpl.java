package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.dto.CustomerDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CustomerRepository;
import jakarta.validation.Valid;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<CustomerDto> getAllCustomers(@RequestBody CustomerDto customerDto) {
		List<CustomerDto> records = customerRepository.findAllByOrderByIdDesc();
		return records;
	}

	@Override
	public Map<String, String> createUser(@Valid @RequestBody CustomerDto customerDto) {
		Map<String, String> responseMapFor = new HashMap<>();
		int number1 = 0;
		int number2 = 0;
		if (customerRepository.existsByEmail(customerDto.getEmail())) {
			number1++;
			String emailResponse = responseMapFor.put("email", "Email should be unique");
		} else if (customerRepository.existsByMobileNumber(customerDto.getMobileNumber())) {
			number2++;
			String emailResponse = responseMapFor.put("mobile", "Mobile number should be unique");
		}
		if (customerRepository.existsByEmail(customerDto.getEmail())
				&& customerRepository.existsByMobileNumber(customerDto.getMobileNumber())) {
			Map<String, String> responseMap = new HashMap<>();
			String emailResponse = responseMap.put("email", "Email should be unique");
			String mobileNumberResponse = responseMap.put("mobile", "Mobile number should be unique");
			return (Map<String, String>) responseMap;
		}
		if (number1 == 1) {
			return responseMapFor;
		} else if (number2 == 1) {
			return responseMapFor;
		}
		customerRepository.save(customerDto);
		Map<String, String> responseFor = new HashMap<>();
		responseFor.put("Success", "Customer Successsfully Registered");
		return (Map<String, String>) responseFor;
	}

	@Override
	public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
		Optional<CustomerDto> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			CustomerDto data = customer.get();
			return ResponseEntity.ok(data); // Return the data if found.
		} else {
			String errorMessage = "Record not found with ID: " + id;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		}

	}

	@Override
	public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDetails) {
		CustomerDto customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));
//		/Optional<CustomerDto> optionalEntity = customerRepository.findById(id);
		Map<String, String> responseMapFor = new HashMap<>();
		if (optionalEntity.isPresent()) {

		if (customerRepository.findById(id) != null) {
			customer.setFirstName(customerDetails.getFirstName());
			customer.setLastName(customerDetails.getLastName());
			customer.setAddress1(customerDetails.getAddress1());
			customer.setAddress2(customerDetails.getAddress2());
			customer.setDateOfBirth(customerDetails.getDateOfBirth());
			customer.setAge(customerDetails.getAge());
			customer.setGender(customerDetails.getGender());
			customer.setMobileNumber(customerDetails.getMobileNumber());
			customer.setEmail(customerDetails.getEmail());
			customerRepository.save(customer);
			Map<String, String> responseForYou = new HashMap<>();
			responseForYou.put("Success", "Customer Successsfully Updated");
			return (ResponseEntity<?>) ResponseEntity.ok("User Updated Successfully");
		} else {
			String errorMessage = "Record not found with ID: " + id;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
			// return (ResponseEntity<?>) ResponseEntity.ok("Not found");
		}
	}

	@Override
	public boolean deleteCustomer(Long id) {
		Optional<CustomerDto> optionalEntity = customerRepository.findById(id);
		if (optionalEntity.isPresent()) {
			customerRepository.delete(optionalEntity.get());
			return true;
		} else {
			return false;
		}
	}

}
