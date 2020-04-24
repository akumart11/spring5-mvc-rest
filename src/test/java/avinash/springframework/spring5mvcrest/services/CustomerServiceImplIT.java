package avinash.springframework.spring5mvcrest.services;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import avinash.springframework.spring5mvcrest.api.v1.domain.CustomerDTO;
import avinash.springframework.spring5mvcrest.bootstrap.Bootstrap;
import avinash.springframework.spring5mvcrest.domain.Customer;
import avinash.springframework.spring5mvcrest.mapper.CustomerMapper;
import avinash.springframework.spring5mvcrest.repositories.CategoryRepository;
import avinash.springframework.spring5mvcrest.repositories.CustomerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CategoryRepository categoryRepository;

	CustomerService customerService;

	@Before
	public void setUp() throws Exception{
		System.out.println("Loading Customer Data");
		System.out.println(customerRepository.findAll().size());
		
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
		bootstrap.run();
		
		customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
	}
	
	@Test
	public void patchCustomerFirstNameTest() throws Exception{
		String updatedName = "UpdatedName";
		Long id = getCustomerIdValue();
		
		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);
		
		String originalFirstName = originalCustomer.getFirstName();
		String originalLastName = originalCustomer.getLastName();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName(updatedName);
		
		customerService.patchCustomer(id, customerDTO);
		
		Customer updatedCustomer = customerRepository.findById(id).get();
		
		assertNotNull(updatedCustomer);
		assertEquals(originalLastName, updatedCustomer.getLastName());
		assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
        assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
	}
	
	@Test
	public void patchCustomerLastNameTest() throws Exception{
		String updatedName = "UpdatedName";
		Long id = getCustomerIdValue();
		
		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);
		
		String originalFirstName = originalCustomer.getFirstName();
		String originalLastName = originalCustomer.getLastName();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setLastName(updatedName);
		
		customerService.patchCustomer(id, customerDTO);
		
		Customer updatedCustomer = customerRepository.findById(id).get();
		
		assertNotNull(updatedCustomer);
		assertEquals(originalFirstName, updatedCustomer.getFirstName());
		assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
        assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
	}
	
	private Long getCustomerIdValue() {
		List<Customer> customers = customerRepository.findAll();
		System.out.println("Customers Found" + customers.size());
		
		return customers.get(0).getId();
	}
}
