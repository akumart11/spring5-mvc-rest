package avinash.springframework.spring5mvcrest.api.v1.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryListDTO {
	
	List<CategoryDTO> categories;	
}
