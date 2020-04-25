package avinash.springframework.spring5mvcrest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import avinash.springframework.spring5mvcrest.api.v1.domain.VendorDTO;
import avinash.springframework.spring5mvcrest.domain.Vendor;

@Mapper
public interface VendorMapper {
	
	VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
	
	VendorDTO vendorToVendorDTO(Vendor vendor);

	Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
