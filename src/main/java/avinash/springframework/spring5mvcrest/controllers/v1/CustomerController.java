package avinash.springframework.spring5mvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import avinash.springframework.spring5mvcrest.api.v1.domain.CustomerDTO;
import avinash.springframework.spring5mvcrest.api.v1.domain.CustomerListDTO;
import avinash.springframework.spring5mvcrest.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/api/v1/customers",  tags = { "Customers" }, description = " ")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

	public static final String BASE_URL = "/api/v1/customers";

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@ApiOperation(value = "Lists all the Customers", notes = "Collection of customers")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerListDTO getAllCategories(){
		
		return new CustomerListDTO(customerService.getAllCustomers());
	}
	@ApiOperation(value = "Finds Customer By Id", notes = "Customer")
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO getCustomerById(	@PathVariable Long id){
		
		return customerService.getCustomerById(id);
	}
	
	@ApiOperation(value = "Creates new Customer", notes = "Customer")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){
		
		return customerService.createNewCustomer(customerDTO);
	}
	
	@ApiOperation(value = "Updates a Customer", notes = "Customer")
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
		
		return customerService.saveCustomerByDTO(id, customerDTO);
	}
	
	@ApiOperation(value = "Patch Update a Customer", notes = "Customer")
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
		
		return customerService.patchCustomer(id, customerDTO);
	}

	@ApiOperation(value = "Deletes a Customer", notes = "Customer")
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable Long id){

		customerService.deleteCustomerById(id);
	}
}
