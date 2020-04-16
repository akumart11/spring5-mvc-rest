package avinash.springframework.spring5mvcrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import avinash.springframework.spring5mvcrest.domain.Category;
import avinash.springframework.spring5mvcrest.domain.Customer;
import avinash.springframework.spring5mvcrest.repositories.CategoryRepository;
import avinash.springframework.spring5mvcrest.repositories.CustomerRepository;

@Component
public class Bootstrap implements CommandLineRunner{
	
	private CategoryRepository categoryRepository;
	private CustomerRepository customerRepository;

	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		loadCustomer();
		loadCategories();
	}

	private void loadCategories() {
		
		Category fruits = new Category();
		fruits.setName("Fruits");
		
		Category dried = new Category();
		dried.setName("Dried");
		
		Category fresh = new Category();
		fresh.setName("Fresh");
		
		Category exotic = new Category();
		exotic.setName("Exotic");
		
		Category nuts = new Category();
		nuts.setName("Nuts");
		
		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);
		
		System.out.println("Data Loaded Category =" + categoryRepository.count());
	}

	private void loadCustomer() {
		
		Customer customer1 = new Customer();
		customer1.setId(1L);
		customer1.setFirstName("Michael");
		customer1.setLastName("Lachappele");
		
		Customer customer2 = new Customer();
		customer2.setId(2L);
		customer2.setFirstName("David");
		customer2.setLastName("Winter");
		
		Customer customer3 = new Customer();
		customer3.setId(3L);
		customer3.setFirstName("Anne");
		customer3.setLastName("Hine");
		
		Customer customer4 = new Customer();
		customer4.setId(4L);
		customer4.setFirstName("Alice");
		customer4.setLastName("Eastman");
		
		Customer customer5 = new Customer();
		customer5.setId(5L);
		customer5.setFirstName("Joe");
		customer5.setLastName("Buck");
		
		Customer customer6 = new Customer();
		customer6.setId(6L);
		customer6.setFirstName("Freddy");
		customer6.setLastName("Meyers");
		
		customerRepository.save(customer1);
		customerRepository.save(customer2);
		customerRepository.save(customer3);
		customerRepository.save(customer4);
		customerRepository.save(customer5);
		customerRepository.save(customer6);
		
		System.out.println("Data Loaded Customer =" + customerRepository.count());
	}

}
