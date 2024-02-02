package trabajo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CustomerDao implements Dao<Customer> {

	private List<Customer> customers = new ArrayList<Customer>();

	public CustomerDao() {
		super();
	}

	//obtener cliente a traves de su id
	public Optional<Customer> get(UUID id) {
		// Devuelve un Optional<Customer> con el Customer cuyo id es id o vac�o si no
		// existe ese Customer
		Customer resultado = null;

		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getId().equals(id)) {
				resultado = customers.get(i);
			}
		}
		return Optional.ofNullable(resultado);
	}
	
	//obtener cliente a traves de su clave natural -> dni
	public Optional<Customer> get(String naturalIdentifier) {
		// Devuelve un Optional<Customer> con el Customer cuyo id es id o vac�o si no
		// existe ese Customer
		Customer resultado = null;

		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getDni().equals(naturalIdentifier)) {
				resultado = customers.get(i);
			}
		}
		return Optional.ofNullable(resultado);
	}

	public List<Customer> getAll() {
		return customers;
	}

	public void save(Customer c) {
		customers.add(c);
	}

	public void update(Customer c, String parameterToModify, String[] params) {
    	if(parameterToModify == "name") {
    		Optional<Customer> customer = this.get(c.getId()); 
    		if(customer.isPresent()){
    			(customer.get()).setName(params[0]);
    		}//Si no est� presente no se hace nada
    	}
    	
    	if(parameterToModify == "firstSurname") {
    		Optional<Customer> customer = this.get(c.getId()); 
    		if(customer.isPresent()){
    			(customer.get()).setFirstSurname(params[0]);
    		}//Si no est� presente no se hace nada
    	}
    	
    	if(parameterToModify == "secondSurname") {
    		Optional<Customer> customer = this.get(c.getId()); 
    		if(customer.isPresent()){
    			(customer.get()).setSecondSurname(params[0]);
    		}//Si no est� presente no se hace nada
    	}
    	
    	if(parameterToModify == "dni") {
    		Optional<Customer> customer = this.get(c.getId()); 
    		if(customer.isPresent()){
    			(customer.get()).setDni(params[0]);
    		}//Si no est� presente no se hace nada
    	}
    	
    	if(parameterToModify == "phone") {
    		Optional<Customer> customer = this.get(c.getId()); 
    		if(customer.isPresent()){
    			(customer.get()).setPhone(params[0]);
    		}//Si no est� presente no se hace nada
    	}
    	
    	if(parameterToModify == "email") {
    		Optional<Customer> customer = this.get(c.getId()); 
    		if(customer.isPresent()){
    			(customer.get()).setEmail(params[0]);
    		}//Si no est� presente no se hace nada
    	}
    	
    	if(parameterToModify == "drivingLicenseType") {
    		Optional<Customer> customer = this.get(c.getId()); 
    		if(customer.isPresent()){
    			(customer.get()).setDrivingLicenseType(params[0]);
    		}//Si no est� presente no se hace nada
    	}
    }

	public void delete(Customer c) {
		customers.remove(c);
	}

}
