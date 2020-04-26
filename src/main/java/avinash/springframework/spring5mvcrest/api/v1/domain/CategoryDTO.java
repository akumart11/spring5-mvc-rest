package avinash.springframework.spring5mvcrest.api.v1.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryDTO {

	private Long id;
	@ApiModelProperty(value = "Name of Category",required = true)
	private String name;
}
