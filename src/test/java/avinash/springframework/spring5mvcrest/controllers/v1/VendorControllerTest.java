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

import avinash.springframework.spring5mvcrest.api.v1.domain.VendorDTO;
import avinash.springframework.spring5mvcrest.services.ResourceNotFoundException;
import avinash.springframework.spring5mvcrest.services.VendorService;

public class VendorControllerTest {
	
	private static final String NAME = "Test Vendor Name 1";
	
	@Mock
	VendorService vendorService;

	@InjectMocks
	VendorController vendorController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler())
				.build();
	}

	@Test
	public void listVendorsTest() throws Exception {

		VendorDTO vendor1 = new VendorDTO(); 
		vendor1.setName(NAME);
		
		VendorDTO vendor2 = new VendorDTO(); 
		vendor2.setName("Test Vendor Name 2");

		List<VendorDTO> vendorList = Arrays.asList(vendor1,vendor2); 

		when(vendorService.getAllVendors()).thenReturn(vendorList);

		mockMvc.perform(get(VendorController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.vendors", hasSize(2)));
	}
	
	@Test
	public void getByIdVendorTest() throws Exception {

		VendorDTO vendor = new VendorDTO(); 
		vendor.setName(NAME);

		when(vendorService.getVendorById(anyLong())).thenReturn(vendor);

		mockMvc.perform(get(VendorController.BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name",equalTo(NAME)));
	}
	
	@Test
	public void createNewVendorTest() throws Exception {

		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName("New Vendor");

		VendorDTO returnedVendor = new VendorDTO();
		returnedVendor.setName(vendorDTO.getName());
		returnedVendor.setVendorUrl(VendorController.BASE_URL + "/1");

		when(vendorService.createNewVendor(any(VendorDTO.class))).thenReturn(returnedVendor);

		mockMvc.perform(post(VendorController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(vendorDTO)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.name", equalTo("New Vendor")))
		.andExpect(jsonPath("$.vendorUrl", equalTo(VendorController.BASE_URL + "/1")));
	}

	@Test
	public void updateVendorTest() throws Exception {

		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName("New Vendor");

		VendorDTO returnedVendor = new VendorDTO();
		returnedVendor.setName(vendorDTO.getName());
		returnedVendor.setVendorUrl(VendorController.BASE_URL + "/1");

		when(vendorService.saveVendorByDTO(anyLong(),any(VendorDTO.class))).thenReturn(returnedVendor);

		mockMvc.perform(put(VendorController.BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(vendorDTO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", equalTo("New Vendor")))
		.andExpect(jsonPath("$.vendorUrl", equalTo(VendorController.BASE_URL + "/1")));
	}

	@Test
	public void deleteVendorTest() throws Exception {
		mockMvc.perform(delete(VendorController.BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		verify(vendorService).deleteVendorById(anyLong());
	}

	@Test
	public void testNotFoundException() throws Exception {

		when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

		mockMvc.perform(get(VendorController.BASE_URL + "/222")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

}
