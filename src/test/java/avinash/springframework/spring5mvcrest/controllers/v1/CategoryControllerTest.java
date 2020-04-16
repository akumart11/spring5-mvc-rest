package avinash.springframework.spring5mvcrest.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import avinash.springframework.spring5mvcrest.api.v1.domain.CategoryDTO;
import avinash.springframework.spring5mvcrest.services.CategoryService;

public class CategoryControllerTest {
	
	private static final String NAME = "Jim";

	@Mock
	CategoryService categoryService;
	
	@InjectMocks
	CategoryController categoryController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}

	@Test
	public void listCategoriesTest() throws Exception {
		CategoryDTO category1 = new CategoryDTO();
		category1.setId(1L);
		category1.setName(NAME);
		
		CategoryDTO category2 = new CategoryDTO();
		category2.setId(2L);
		category2.setName("Bob");
		
		List<CategoryDTO> categoryList = Arrays.asList(category1,category2); 
		
		when(categoryService.getAllCategories()).thenReturn(categoryList);
		
		mockMvc.perform(get("/api/v1/categories")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.categories", hasSize(2)));
		
	}
	
	@Test
	public void getByNameCategoriesTest() throws Exception {
		
		CategoryDTO category = new CategoryDTO();
		category.setId(1L);
		category.setName(NAME);
		
		when(categoryService.getCategoryByName(anyString())).thenReturn(category);
		
		mockMvc.perform(get("/api/v1/categories/Jim")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name",equalTo(NAME)));
	}

}
