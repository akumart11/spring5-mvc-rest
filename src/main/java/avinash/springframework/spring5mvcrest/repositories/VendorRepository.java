package avinash.springframework.spring5mvcrest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import avinash.springframework.spring5mvcrest.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{
	Optional<Vendor> findById(Long id);
}
