package avinash.springframework.spring5mvcrest.api.v1.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {
	@ApiModelProperty(value = "Name of Vendor",required = true)
	private String name;
	@ApiModelProperty(value = "URL of Vendor",required = false)
	private String vendorUrl;
}
