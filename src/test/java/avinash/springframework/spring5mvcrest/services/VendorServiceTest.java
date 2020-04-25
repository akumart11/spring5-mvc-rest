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

import avinash.springframework.spring5mvcrest.api.v1.domain.VendorDTO;
import avinash.springframework.spring5mvcrest.controllers.v1.VendorController;
import avinash.springframework.spring5mvcrest.domain.Vendor;
import avinash.springframework.spring5mvcrest.mapper.VendorMapper;
import avinash.springframework.spring5mvcrest.repositories.VendorRepository;

public class VendorServiceTest {

	private static final long ID = 1L;
	private static final String NAME = "Test Vendor";

	VendorService vendorService;

	@Mock 
	VendorRepository vendorRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
	}

	@Test
	public void getAllVendorsTest() throws Exception {

		List<Vendor> vendorList = Arrays.asList(new Vendor(),new Vendor(),new Vendor());

		when(vendorRepository.findAll()).thenReturn(vendorList);

		List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

		assertEquals(3, vendorDTOS.size());

	}

	@Test
	public void getVendorByIdTest() throws Exception {

		Vendor vendor = new Vendor();
		vendor.setId(ID);
		vendor.setName(NAME);
		Optional<Vendor> vendorOptional = Optional.of(vendor);

		when(vendorRepository.findById(anyLong())).thenReturn(vendorOptional);

		VendorDTO vendorDTO = vendorService.getVendorById(ID);

		assertEquals(NAME, vendorDTO.getName());
	}

	@Test
	public void createNewVendorTest() {
		
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName("New Vendor");
		
		Vendor savedVendor = new Vendor();
		savedVendor.setId(ID);
		savedVendor.setName(vendorDTO.getName());
		
		when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);
		
		VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);
		
		assertEquals(vendorDTO.getName(), savedVendorDTO.getName());
		assertEquals(VendorController.BASE_URL + "/1", savedVendorDTO.getVendorUrl());
	}
	
	@Test
	public void updateVendorTest() {

		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName("New Vendor");
		
		Vendor savedVendor = new Vendor();
		savedVendor.setId(ID);
		savedVendor.setName(vendorDTO.getName());
		
		when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);
		
		VendorDTO savedCustomerDTO = vendorService.saveVendorByDTO(Long.valueOf(1L),vendorDTO);
		
		assertEquals(vendorDTO.getName(), savedCustomerDTO.getName());
		assertEquals(VendorController.BASE_URL + "/1", savedCustomerDTO.getVendorUrl());
	}
	
	@Test
	public void deleteVendorTest() {
		Long id = 1L;
		
		vendorRepository.deleteById(id);
		
		verify(vendorRepository,times(1)).deleteById(id);		
		
		
	}
	
}
