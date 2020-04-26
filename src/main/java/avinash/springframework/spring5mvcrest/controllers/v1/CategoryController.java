package avinash.springframework.spring5mvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import avinash.springframework.spring5mvcrest.api.v1.domain.CategoryDTO;
import avinash.springframework.spring5mvcrest.api.v1.domain.CategoryListDTO;
import avinash.springframework.spring5mvcrest.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/api/v1/categories",  tags = { "Categories" }, description = " ")
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

	public static final String BASE_URL = "/api/v1/categories";

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@ApiOperation(value = "Lists all the Categories", notes = "Collection of Categories")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CategoryListDTO getAllCategories(){
		
		return new CategoryListDTO(categoryService.getAllCategories());
	}

	@ApiOperation(value = "Finds Category By Id", notes = "Category")
	@GetMapping("{name}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryDTO getCategoryByName(@PathVariable String name){

		return categoryService.getCategoryByName(name);
	}
}
