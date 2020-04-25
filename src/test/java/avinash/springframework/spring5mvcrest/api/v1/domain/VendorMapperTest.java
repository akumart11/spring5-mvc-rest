package avinash.springframework.spring5mvcrest.api.v1.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import avinash.springframework.spring5mvcrest.domain.Vendor;
import avinash.springframework.spring5mvcrest.mapper.VendorMapper;

public class VendorMapperTest {
	
	private static final long ID = 1L;
	private static final String NAME = "Western Tasty Fruits Ltd.";
	VendorMapper vendorMapper = VendorMapper.INSTANCE;

	@Test
	public void vendorToVendorDTO() throws Exception{

		Vendor vendor = new Vendor();   	

		vendor.setId(ID);
		vendor.setName(NAME);

		VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

		assertEquals(NAME, vendorDTO.getName());
	}
}
