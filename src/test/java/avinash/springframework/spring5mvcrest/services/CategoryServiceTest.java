package avinash.springframework.spring5mvcrest.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import avinash.springframework.spring5mvcrest.api.v1.domain.CategoryDTO;
import avinash.springframework.spring5mvcrest.domain.Category;
import avinash.springframework.spring5mvcrest.mapper.CategoryMapper;
import avinash.springframework.spring5mvcrest.repositories.CategoryRepository;

public class CategoryServiceTest {

	public static final Long ID = 2L;
	public static final String NAME = "Jimmy";
	
	CategoryService categoryService;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
			
		categoryService =  new CategoryServiceImpl(CategoryMapper.INSTANCE,categoryRepository);
	}
	
	@Test
	public void getAllCategoriesTest() {
				
		List<Category> categories = Arrays.asList(new Category(),new Category(),new Category());
		
		when(categoryRepository.findAll()).thenReturn(categories);
		
		List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
		
		assertEquals(3, categoryDTOS.size());
		
	}
	
	@Test
	public void getCategoriesByNameTest() {
		
		Category category =  new Category();
		category.setName(NAME);
		category.setId(ID);
		
		when(categoryRepository.findByName(anyString())).thenReturn(category);
		
		CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);
		
		assertEquals(NAME, categoryDTO.getName());
		assertEquals(ID, categoryDTO.getId());
	}
	
}
