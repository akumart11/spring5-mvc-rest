package avinash.springframework.spring5mvcrest.api.v1.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import avinash.springframework.spring5mvcrest.domain.Customer;
import avinash.springframework.spring5mvcrest.mapper.CustomerMapper;

public class CustomerMapperTest {

	private static final long ID = 1L;
	private static final String FIRST_NAME = "Joe";
	private static final String LAST_NAME = "Buck";
	CustomerMapper customerMapper = CustomerMapper.INSTANCE;

	@Test
	public void customerToCustomerDTO() throws Exception{

		Customer customer = new Customer();   	
		customer.setFirstName(FIRST_NAME);
		customer.setLastName(LAST_NAME);
		customer.setId(ID);

		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

		assertEquals(FIRST_NAME, customerDTO.getFirstName());
		assertEquals(LAST_NAME, customerDTO.getLastName());
	}
}
