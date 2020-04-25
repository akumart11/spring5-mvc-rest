package avinash.springframework.spring5mvcrest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import avinash.springframework.spring5mvcrest.api.v1.domain.VendorDTO;
import avinash.springframework.spring5mvcrest.controllers.v1.VendorController;
import avinash.springframework.spring5mvcrest.domain.Vendor;
import avinash.springframework.spring5mvcrest.mapper.VendorMapper;
import avinash.springframework.spring5mvcrest.repositories.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

	private VendorMapper vendorMapper;
	private VendorRepository vendorRepository;

	public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
		super();
		this.vendorMapper = vendorMapper;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public List<VendorDTO> getAllVendors() {

		return vendorRepository.findAll()
				.stream()
				.map(vendor -> {
					VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
					vendorDTO.setVendorUrl("/shop/vendors/" + vendor.getId());
					return vendorDTO;
				})
				.collect(Collectors.toList());
	}

	@Override
	public VendorDTO getVendorById(Long id) {
		return vendorRepository.findById(id)
				.map(vendor ->{
					VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
					vendorDTO.setVendorUrl("/shop/vendors/" + vendor.getId());
					return vendorDTO;
				})
				.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public VendorDTO createNewVendor(VendorDTO vendorDTO) {
		return saveAndReturnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
	}

	@Override
	public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
	
		Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
		vendor.setId(id);
		
		return saveAndReturnDTO(vendor);
	}

	private VendorDTO saveAndReturnDTO(Vendor vendor) {
		Vendor savedVendor = vendorRepository.save(vendor);

		VendorDTO returnedVendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
		returnedVendorDTO.setVendorUrl(getVendorURL(savedVendor.getId()));

		return returnedVendorDTO;
	}

	private String getVendorURL(Long id) {
		return VendorController.BASE_URL + "/" + id;
	}

	@Override
	public void deleteVendorById(Long id) {
		vendorRepository.deleteById(id);
	}
	


}
