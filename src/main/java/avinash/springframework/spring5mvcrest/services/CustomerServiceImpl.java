package avinash.springframework.spring5mvcrest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import avinash.springframework.spring5mvcrest.api.v1.domain.CustomerDTO;
import avinash.springframework.spring5mvcrest.controllers.v1.CustomerController;
import avinash.springframework.spring5mvcrest.domain.Customer;
import avinash.springframework.spring5mvcrest.mapper.CustomerMapper;
import avinash.springframework.spring5mvcrest.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	
	private final CustomerMapper customerMapper;
	private final CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
		this.customerMapper = customerMapper;
		this.customerRepository = customerRepository;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll()
				.stream()
				.map(customer -> {
					CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
					customerDTO.setCustomerUrl(getCustomerURL(customer.getId()));
					return customerDTO;
				})
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		return customerRepository.findById(id)
				.map(customer ->{
					CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
					customerDTO.setCustomerUrl(getCustomerURL(customer.getId()));
					return customerDTO;
				})
				.orElseThrow(RuntimeException::new);
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
		return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
	}

	private CustomerDTO saveAndReturnDTO(Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		
		CustomerDTO returnedCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
		returnedCustomerDTO.setCustomerUrl(getCustomerURL(savedCustomer.getId()));
		
		return returnedCustomerDTO;
	}

	@Override
	public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
		
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		customer.setId(id);
		
		return saveAndReturnDTO(customer);
	}

	@Override
	public CustomerDTO pathCustomer(Long id, CustomerDTO customerDTO) {
		return customerRepository.findById(id).map(customer ->{
			if(customerDTO.getFirstName() != null) {
				customer.setFirstName(customerDTO.getFirstName());
			}
			if(customerDTO.getLastName() != null) {
				customer.setLastName(customerDTO.getLastName());
			}
			
			CustomerDTO patchedCustomerDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
			patchedCustomerDTO.setCustomerUrl(getCustomerURL(id));
			return patchedCustomerDTO;
		}).orElseThrow(RuntimeException::new);
	}
	
	private String getCustomerURL(Long id) {
		return CustomerController.BASE_URL + "/" + id;
	}
	
	@Override
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);
	}

}
