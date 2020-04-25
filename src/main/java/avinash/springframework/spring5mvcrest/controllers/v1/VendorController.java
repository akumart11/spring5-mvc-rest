package avinash.springframework.spring5mvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import avinash.springframework.spring5mvcrest.api.v1.domain.VendorDTO;
import avinash.springframework.spring5mvcrest.api.v1.domain.VendorListDTO;
import avinash.springframework.spring5mvcrest.services.VendorService;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
	
	public static final String BASE_URL = "/api/v1/shop/vendors";
	
	VendorService vendorService;

	public VendorController(VendorService vendorService) {
		super();
		this.vendorService = vendorService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public VendorListDTO getAllVendors(){
		
		return new VendorListDTO(vendorService.getAllVendors());
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO getCategoryById(	@PathVariable Long id){
		
		return vendorService.getVendorById(id);
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){
		
		return vendorService.createNewVendor(vendorDTO);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
		
		return vendorService.saveVendorByDTO(id, vendorDTO);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable Long id){

		vendorService.deleteVendorById(id);
	}
}
