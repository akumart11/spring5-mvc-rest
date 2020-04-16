package avinash.springframework.spring5mvcrest.services;

import java.util.List;

import avinash.springframework.spring5mvcrest.api.v1.domain.CustomerDTO;

public interface CustomerService {

	List<CustomerDTO> getAllCustomers();

	CustomerDTO getCustomerById(Long id);
	
	CustomerDTO createNewCustomer(CustomerDTO customerDTO);	
	
}