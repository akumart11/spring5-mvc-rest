package avinash.springframework.spring5mvcrest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import avinash.springframework.spring5mvcrest.api.v1.domain.CustomerDTO;
import avinash.springframework.spring5mvcrest.domain.Customer;

@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
	CustomerDTO customerToCustomerDTO(Customer customer);
}
