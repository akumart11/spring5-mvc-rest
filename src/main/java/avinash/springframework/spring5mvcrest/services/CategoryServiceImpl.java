package avinash.springframework.spring5mvcrest.services;

import java.util.List;
import java.util.stream.Collectors;

import avinash.springframework.spring5mvcrest.api.v1.domain.CategoryDTO;
import avinash.springframework.spring5mvcrest.mapper.CategoryMapper;
import avinash.springframework.spring5mvcrest.repositories.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryMapper categoryMapper;
	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
		this.categoryMapper = categoryMapper;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return categoryRepository.findAll()
				.stream()
				.map(categoryMapper::categoryToCategoryDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
	}

}