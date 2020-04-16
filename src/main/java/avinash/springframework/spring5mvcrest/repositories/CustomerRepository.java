package avinash.springframework.spring5mvcrest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import avinash.springframework.spring5mvcrest.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Optional<Customer> findById(Long id);
	
}
