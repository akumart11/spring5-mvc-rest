package avinash.springframework.spring5mvcrest.api.v1.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorListDTO {
	
	List<VendorDTO> vendors;
}
