package avinash.springframework.spring5mvcrest.services;

import java.util.List;

import avinash.springframework.spring5mvcrest.api.v1.domain.CategoryDTO;

public interface CategoryService {
	
	List<CategoryDTO> getAllCategories();
	CategoryDTO getCategoryByName(String name);

}