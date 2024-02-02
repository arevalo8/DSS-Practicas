package es.uca.dss.UCAravana.apirest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import trabajo.Customer;
import trabajo.Dao;


public class CustomerJPADao implements Dao<Customer>{

	CustomerRepository repository;
	
	//@Autowired
	CustomerJPADao(CustomerRepository repository){
		//Se le asigna una instancia de una clase que implementa CustomerRepository (de las que crea Spring)
		this.repository = repository;
	}
	
	@Override
	public List<Customer> getAll() {
		return repository.findAll();
	}
	
	@Override
	public Optional<Customer> get(UUID id) {
		return repository.findById(id);
	}

	@Override
	public void save(Customer c) {
		repository.save(c);
	}

	/*@Override
	public void update(Customer t, String parameterToModify, String[] params) {
		//Sustituye los datos del cliente con id params[0] por los del cliente t, si no existe, guarda el cliente en la BD
		Optional<Customer> customer = repository.findById(UUID.fromString(params[0]));
			      if(customer.isPresent()){
			        customer.get().setName(t.getName());
			        customer.get().setFirstSurname(t.getFirstSurname());
			        customer.get().setSecondSurname(t.getSecondSurname());
			        customer.get().setDni(t.getDni());
			        customer.get().setPhones(t.getPhones());
			        customer.get().setEmail(t.getEmail());
			        customer.get().setDrivingLicenseType(t.getDrivingLicenseType());
			        
			        repository.save(customer.get());
			      }else {
				    repository.save(t);
			      };
			      
		
	}*/
	
	public void update(Customer t, String parameterToModify, String[] params) {
		//Sustituye los datos del cliente con id params[0] por los del cliente t, si no existe, guarda el cliente en la BD
		Optional<Customer> customer = repository.findById(UUID.fromString(params[0]));
		if(customer.isPresent()) {
	    	/*if(parameterToModify == "name") {
	    			(customer.get()).setName(params[0]);
	    	}else if(parameterToModify == "firstSurname") {
	    		
	    			(customer.get()).setFirstSurname(params[0]);
	    			
	    	}else if(parameterToModify == "secondSurname") {
	    		
	    			(customer.get()).setSecondSurname(params[0]);

	    	}else if(parameterToModify == "dni") {
	    		
	    			(customer.get()).setDni(params[0]);
	    	}else if(parameterToModify == "editPhone") {
	    		
	    			(customer.get()).setPhone(params[0]);
	    	}else if(parameterToModify == "email") {
	    		
	    			(customer.get()).setEmail(params[0]);
	    		
	    	}else if(parameterToModify == "drivingLicenseType") {
	    		
	    			(customer.get()).setDrivingLicenseType(params[0]);
	    			
	    	} else {*/
	    		customer.get().setName(t.getName());
	    		customer.get().setFirstSurname(t.getFirstSurname());
	    		customer.get().setSecondSurname(t.getSecondSurname());
	    		customer.get().setDni(t.getDni());
	    		customer.get().setPhone(t.getPhone());
	    		customer.get().setEmail(t.getEmail());
	    		customer.get().setDrivingLicenseType(t.getDrivingLicenseType());
	    	//}
	    	
	    	repository.save(customer.get());
	    	
		} else {
			repository.save(t);
		}
    }

	@Override
	public void delete(Customer t) {
		repository.deleteById(t.getId());
		
	}



	@Override
	public Optional<Customer> get(String naturalIdentifier) {
		
		Optional<Customer> resultado = repository.findByDni(naturalIdentifier);
		
		return resultado;
	}

}
