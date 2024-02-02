package es.uca.dss.UCAravana.apirest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import trabajo.Customer;
import trabajo.CustomerManager;


@RestController
public class CustomerController {

	private CustomerManager cm;
	
	private static CustomerRepository customerRepository;
	
	CustomerController(CustomerRepository repository, CustomerManager cm) {
			//Se crea el manager de Customers con la base de datos JPA-style como sistema de persistencia
		    this.cm = cm; //new CustomerManager(new CustomerJPADao(repository));
		    customerRepository = repository;
	}
	
	static CustomerRepository getCustomerRepository(){
		return CustomerController.customerRepository;
	}
	
	@GetMapping("/customers")
	public CollectionModel<EntityModel<Customer>> getAllCustomers() {
	    List<EntityModel<Customer>> customers = cm.getAllCustomers().stream()
	        .map(customer -> {
	            EntityModel<Customer> model = EntityModel.of(customer,
	                linkTo(methodOn(CustomerController.class).getCustomer(customer.getId())).withSelfRel(),
	                linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));
	            return model;
	        })
	        .collect(Collectors.toList());

	    return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel());
	}

	
	@GetMapping("/customers/{id}")
	public EntityModel<Customer> getCustomer(@PathVariable UUID id) {
		Customer c = cm.getCustomer(id);
		if(c.getId() != null) {
			return EntityModel.of(c, //
				      linkTo(methodOn(CustomerController.class).getCustomer(id)).withSelfRel(),
				      linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));
		}else {
			throw new CustomerNotFoundException(id);
		}
	}
	
	@GetMapping("/customers/dni/{dni}")
	public EntityModel<Customer> getCustomer(@PathVariable String dni) {
		Customer c = cm.getCustomer(dni);
		if(c.getId() != null) {
			return EntityModel.of(c, //
				      linkTo(methodOn(CustomerController.class).getCustomer(dni)).withSelfRel(),
				      linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));
		}else {
			throw new CustomerNotFoundException(dni);
		}
	}
	
	@PutMapping("/customers/{id}")
	public Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable UUID id) {
		Customer old = cm.getCustomer(id);
		if(newCustomer.getName().equals("string")) {
			newCustomer.setName(old.getName());
		}
		if(newCustomer.getFirstSurname().equals("string")) {
			newCustomer.setFirstSurname(old.getFirstSurname());
		}
		if(newCustomer.getSecondSurname().equals("string")) {
			newCustomer.setSecondSurname(old.getSecondSurname());
		}
		if(newCustomer.getDni().equals("string")) {
			newCustomer.setDni(old.getDni());
		}
		if(newCustomer.getPhone().equals("string")) {
			newCustomer.setPhone(old.getPhone());
		}
		if(newCustomer.getEmail().equals("string")) {
			newCustomer.setEmail(old.getEmail());
		}
		if(newCustomer.getDrivingLicenseType().equals("string")) {
			newCustomer.setDrivingLicenseType(old.getDrivingLicenseType());
		}
		
		cm.updateCustomer(newCustomer, id);
		return newCustomer;
	}
	
	@PutMapping("/customers/dni/{dni}")
	public Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable String dni) {
		Customer old = cm.getCustomer(dni);
		if(newCustomer.getName().equals("string")) {
			newCustomer.setName(old.getName());
		}
		if(newCustomer.getFirstSurname().equals("string")) {
			newCustomer.setFirstSurname(old.getFirstSurname());
		}
		if(newCustomer.getSecondSurname().equals("string")) {
			newCustomer.setSecondSurname(old.getSecondSurname());
		}
		if(newCustomer.getDni().equals("string")) {
			newCustomer.setDni(old.getDni());
		}
		if(newCustomer.getPhone().equals("string")) {
			newCustomer.setPhone(old.getPhone());
		}
		if(newCustomer.getEmail().equals("string")) {
			newCustomer.setEmail(old.getEmail());
		}
		if(newCustomer.getDrivingLicenseType().equals("string")) {
			newCustomer.setDrivingLicenseType(old.getDrivingLicenseType());
		}
		
		cm.updateCustomer(newCustomer, old.getId());
		return newCustomer;
	}
	
	@PostMapping("/customers")
	public Customer saveCustomer(@RequestBody Customer newCustomer) {
		cm.saveCustomer(newCustomer.getName(),
				newCustomer.getFirstSurname(),
				newCustomer.getSecondSurname(),
				newCustomer.getDni(),
				newCustomer.getPhone(),
				newCustomer.getEmail(),
				newCustomer.getDrivingLicenseType());
		return newCustomer;
	}
	
	
	@DeleteMapping("/customers/{id}")
	public void deleteCustomer(@PathVariable UUID id) {
		cm.deleteCustomer(id);
	}
	
	@DeleteMapping("/customers/dni/{dni}")
	public void deleteCustomer(@PathVariable String dni) {
		cm.deleteCustomer(dni);
	}
}
