package avinash.springframework.spring5mvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import avinash.springframework.spring5mvcrest.api.v1.domain.CustomerDTO;
import avinash.springframework.spring5mvcrest.api.v1.domain.CustomerListDTO;
import avinash.springframework.spring5mvcrest.services.CustomerService;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public ResponseEntity<CustomerListDTO> getAllCategories(){
			
		return new ResponseEntity<CustomerListDTO>(
				new CustomerListDTO(customerService.getAllCustomers()),HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CustomerDTO> getCategoryById(	@PathVariable Long id){
			
		return new ResponseEntity<CustomerDTO>(
				customerService.getCustomerById(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
		
		return new ResponseEntity<CustomerDTO>(
				customerService.createNewCustomer(customerDTO),HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
		
		return new ResponseEntity<CustomerDTO>(
				customerService.saveCustomerByDTO(id, customerDTO),HttpStatus.OK);
	}
}
