package avinash.springframework.spring5mvcrest.api.v1.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import avinash.springframework.spring5mvcrest.domain.Category;
import avinash.springframework.spring5mvcrest.mapper.CategoryMapper;

public class CategoryMapperTest {
	
	private static final long ID = 1L;
	private static final String NAME = "Joe";
	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
	
	@Test
	public void categoryToCategoryDTO() throws Exception{
		
		Category category = new Category();   	
		category.setName(NAME);
		category.setId(ID);
		
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
		
		assertEquals(Long.valueOf(ID), categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
	}

}
