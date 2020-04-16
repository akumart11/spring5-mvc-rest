package avinash.springframework.spring5mvcrest.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import avinash.springframework.spring5mvcrest.api.v1.domain.CustomerDTO;
import avinash.springframework.spring5mvcrest.domain.Customer;
import avinash.springframework.spring5mvcrest.mapper.CustomerMapper;
import avinash.springframework.spring5mvcrest.repositories.CustomerRepository;

public class CustomerServiceTest {

	private static final String LAST_NAME = "Buck";
	private static final long ID = 1L;
	private static final String FIRST_NAME = "Joe";

	CustomerService customerService;

	@Mock
	CustomerRepository customerRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
	}

	@Test
	public void getAllCustomerTest() {

		List<Customer> customersList = Arrays.asList(new Customer(),new Customer(),new Customer());

		when(customerRepository.findAll()).thenReturn(customersList);

		List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

		assertEquals(3, customerDTOS.size());

	}
	
	@Test
	public void getCustomerByIdTest() {
		
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setFirstName(FIRST_NAME);
		customer.setLastName(LAST_NAME);
		Optional<Customer> customerOptional = Optional.of(customer);
		
		when(customerRepository.findById(anyLong())).thenReturn(customerOptional);
		
		CustomerDTO customerDTO = customerService.getCustomerById(ID);
		
		assertEquals(FIRST_NAME, customerDTO.getFirstName());
		assertEquals(LAST_NAME, customerDTO.getLastName());
	}

}
