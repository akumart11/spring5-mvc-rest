package avinash.springframework.spring5mvcrest.services;

import java.util.List;

import avinash.springframework.spring5mvcrest.api.v1.domain.VendorDTO;

public interface VendorService {

	List<VendorDTO> getAllVendors();

	VendorDTO getVendorById(Long id);

	VendorDTO createNewVendor(VendorDTO vendorDTO);

	VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

	void deleteVendorById(Long id);

}
