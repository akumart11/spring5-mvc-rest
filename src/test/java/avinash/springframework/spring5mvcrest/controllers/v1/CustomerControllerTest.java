package avinash.springframework.spring5mvcrest.controllers.v1;

import static avinash.springframework.spring5mvcrest.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import avinash.springframework.spring5mvcrest.api.v1.domain.CustomerDTO;
import avinash.springframework.spring5mvcrest.services.CustomerService;
import avinash.springframework.spring5mvcrest.services.ResourceNotFoundException;

public class CustomerControllerTest {

	private static final String FIRST_NAME = "Joe";
	private static final String LAST_NAME = "Buck";

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(customerController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler())
				.build();
	}

	@Test
	public void listCustomersTest() throws Exception {

		CustomerDTO customer1 = new CustomerDTO();
		customer1.setFirstName(FIRST_NAME);
		customer1.setLastName(LAST_NAME);

		CustomerDTO customer2 = new CustomerDTO();
		customer2.setFirstName("Samuel");
		customer2.setLastName("Jackson");

		List<CustomerDTO> customerList = Arrays.asList(customer1,customer2); 

		when(customerService.getAllCustomers()).thenReturn(customerList);

		mockMvc.perform(get(CustomerController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.customers", hasSize(2)));

	}

	@Test
	public void getByNameCategoriesTest() throws Exception {

		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName(FIRST_NAME);
		customer.setLastName(LAST_NAME);

		when(customerService.getCustomerById(anyLong())).thenReturn(customer);

		mockMvc.perform(get(CustomerController.BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName",equalTo(FIRST_NAME)))
		.andExpect(jsonPath("$.lastName",equalTo(LAST_NAME)));
	}

	@Test
	public void createNewCustomerTest() throws Exception {

		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fred");
		customer.setLastName("Flintstone");

		CustomerDTO returnedCustomer = new CustomerDTO();
		returnedCustomer.setFirstName(customer.getFirstName());
		returnedCustomer.setLastName(customer.getLastName());
		returnedCustomer.setCustomerUrl(CustomerController.BASE_URL + "/1");

		when(customerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(returnedCustomer);

		mockMvc.perform(post(CustomerController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.firstName", equalTo("Fred")))
		.andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/1")));
	}

	@Test
	public void updateCustomerTest() throws Exception {

		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fred");
		customer.setLastName("Flintstone");

		CustomerDTO returnedCustomer = new CustomerDTO();
		returnedCustomer.setFirstName(customer.getFirstName());
		returnedCustomer.setLastName(customer.getLastName());
		returnedCustomer.setCustomerUrl(CustomerController.BASE_URL + "/1");

		when(customerService.saveCustomerByDTO(anyLong(),any(CustomerDTO.class))).thenReturn(returnedCustomer);

		mockMvc.perform(put(CustomerController.BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName", equalTo("Fred")))
		.andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
		.andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/1")));
	}

	@Test
	public void patchCustomerTest() throws Exception {
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fred");

		CustomerDTO returnedCustomer = new CustomerDTO();
		returnedCustomer.setFirstName(customer.getFirstName());
		returnedCustomer.setLastName("Flintstone");
		returnedCustomer.setCustomerUrl(CustomerController.BASE_URL + "/1");

		when(customerService.pathCustomer(anyLong(),any(CustomerDTO.class))).thenReturn(returnedCustomer);

		mockMvc.perform(patch(CustomerController.BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())))
		.andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
		.andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/1")));
	}

	@Test
	public void deleteCustomerTest() throws Exception {
		mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		verify(customerService).deleteCustomerById(anyLong());
	}

	@Test
	public void testNotFoundException() throws Exception {

		when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

		mockMvc.perform(get(CustomerController.BASE_URL + "/222")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
}
