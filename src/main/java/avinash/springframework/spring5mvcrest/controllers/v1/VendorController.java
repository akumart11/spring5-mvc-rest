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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/api/v1/shop/vendors",  tags = { "Vendors" }, description = " ")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
	
	public static final String BASE_URL = "/api/v1/shop/vendors";
	
	VendorService vendorService;

	public VendorController(VendorService vendorService) {
		super();
		this.vendorService = vendorService;
	}
	
	@ApiOperation(value = "Lists all the Vendors", notes = "Collection of Vendors")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public VendorListDTO getAllVendors(){
		
		return new VendorListDTO(vendorService.getAllVendors());
	}
	
	@ApiOperation(value = "Finds Vendor By Id", notes = "Vendor")
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO getVendorById(	@PathVariable Long id){
		
		return vendorService.getVendorById(id);
	}
	
	@ApiOperation(value = "Creates new Vendor", notes = "Vendor")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){
		
		return vendorService.createNewVendor(vendorDTO);
	}
	
	@ApiOperation(value = "Updates a Vendor", notes = "Vendor")
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
		
		return vendorService.saveVendorByDTO(id, vendorDTO);
	}

	@ApiOperation(value = "Deletes a Vendor", notes = "Vendor")
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable Long id){

		vendorService.deleteVendorById(id);
	}
}
