package avinash.springframework.spring5mvcrest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import avinash.springframework.spring5mvcrest.api.v1.domain.CategoryDTO;
import avinash.springframework.spring5mvcrest.domain.Category;

@Mapper
public interface CategoryMapper {
	
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	CategoryDTO categoryToCategoryDTO(Category category);

}
