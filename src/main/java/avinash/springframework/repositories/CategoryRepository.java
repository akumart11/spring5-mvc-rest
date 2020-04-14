package avinash.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import avinash.springframework.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
