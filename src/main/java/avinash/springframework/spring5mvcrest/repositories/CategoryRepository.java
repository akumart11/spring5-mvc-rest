package avinash.springframework.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import avinash.springframework.spring5mvcrest.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findByName(String name);

}
