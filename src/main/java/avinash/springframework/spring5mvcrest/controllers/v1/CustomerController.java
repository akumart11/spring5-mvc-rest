package avinash.springframework.spring5mvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<CustomerDTO> getCategoryById(@PathVariable String id){
			
		return new ResponseEntity<CustomerDTO>(
				customerService.getCustomerById(Long.valueOf(id)),HttpStatus.OK);
	}
}
