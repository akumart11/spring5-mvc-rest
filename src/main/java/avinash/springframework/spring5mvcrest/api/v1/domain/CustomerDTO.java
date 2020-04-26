package avinash.springframework.spring5mvcrest.api.v1.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	@ApiModelProperty(value = "First Name of Customer",required = true)
	private String firstName;
	@ApiModelProperty(value = "Last Name of Customer",required = true)
	private String lastName;
	@ApiModelProperty(value = "URL of Customer",required = false)
	private String customerUrl;
}

