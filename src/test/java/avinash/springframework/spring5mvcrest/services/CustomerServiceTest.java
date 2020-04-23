package avinash.springframework.spring5mvcrest.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
	
	@Test
	public void createNewCustomerTest() {
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Avinash");
		customerDTO.setLastName("Kumar");
		
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		savedCustomer.setId(1L);
		
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		
		CustomerDTO savedCustomerDTO = customerService.createNewCustomer(customerDTO);
		
		assertEquals(customerDTO.getFirstName(), savedCustomerDTO.getFirstName());
		assertEquals(customerDTO.getLastName(), savedCustomerDTO.getLastName());
		assertEquals("api/v1/customers/1", savedCustomerDTO.getCustomerUrl());
	}
	
	@Test
	public void updateCustomerTest() {
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Rahul");
		customerDTO.setLastName("Roy");
		
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		savedCustomer.setId(1L);
		
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		
		CustomerDTO savedCustomerDTO = customerService.saveCustomerByDTO(Long.valueOf(1L),customerDTO);
		
		assertEquals(customerDTO.getFirstName(), savedCustomerDTO.getFirstName());
		assertEquals(customerDTO.getLastName(), savedCustomerDTO.getLastName());
		assertEquals("api/v1/customers/1", savedCustomerDTO.getCustomerUrl());
	}
	
	@Test
	public void deleteCustomerTest() {
		Long id = 1L;
		
		customerRepository.deleteById(id);
		
		verify(customerRepository,times(1)).deleteById(id);		
		
		
	}

}
