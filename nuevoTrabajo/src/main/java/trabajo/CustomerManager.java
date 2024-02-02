package trabajo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import trabajo.CaravanManager.CaravanNotFoundException;

/**
 * @author Dar�o Salas Arellano
 *
 */

@Service
public class CustomerManager {
	
	private Dao<Customer> daoCustomers;
	
	//Constructor
	public CustomerManager(Dao<Customer> daoCustomers) {
		super();
		this.daoCustomers = daoCustomers; 	//Se le asigna a DaoCustomers el tipo de sistema de persistencia espec�fico
	}
	
	public List<Customer> getAllCustomers() {
		return daoCustomers.getAll();
	}
	
	public void saveCustomer(Customer c) {
		//Hay que comprobar que el Customer no tiene un id que ya existe en la base de datos
		boolean isUnique = customerUnique(c.getDni());
		if(isUnique) {
			daoCustomers.save(c);				//Aqu� se recurre a los m�todos de la interfaz para guardar al Customer
			System.out.println("El cliente se ha almacenado con exito");
		}
	}
	
	public void saveCustomer(String name, String firstSurname, String secondSurname, String dni, String phone,
			String email, String drivingLicenseType) {
		//Hay que comprobar que el Customer no tiene un id que ya existe en la base de datos
		boolean isUnique = customerUnique(dni);
		if(isUnique) {
			Customer c = new Customer(name, firstSurname, secondSurname, dni, phone, email, drivingLicenseType);
			daoCustomers.save(c);				//Aqu� se recurre a los m�todos de la interfaz para guardar al Customer
			System.out.println("El cliente se ha almacenado con exito");
		}
	}
	
	public void editCustomerName(UUID id, String name) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = name;

		daoCustomers.update(this.getRawCustomer(id), "name",  parametros);
	}
	
	public void editCustomerName(String dni, String name) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = name;

		daoCustomers.update(this.getRawCustomer(dni), "name",  parametros);
	}
	
	public void editCustomerFirstSurname(UUID id, String firstSurname) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = firstSurname;

		daoCustomers.update(this.getRawCustomer(id), "firstSurname",  parametros);
	}
	
	public void editCustomerFirstSurname(String dni, String firstSurname) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = firstSurname;

		daoCustomers.update(this.getRawCustomer(dni), "firstSurname",  parametros);
	}
	
	public void editCustomerSecondSurname(UUID id, String secondSurname) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = secondSurname;

		daoCustomers.update(this.getRawCustomer(id), "secondSurname",  parametros);
	}
	
	public void editCustomerSecondSurname(String dni, String secondSurname) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = secondSurname;

		daoCustomers.update(this.getRawCustomer(dni), "secondSurname",  parametros);
	}
	
	public void editCustomerdni(UUID id, String newDni) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = newDni;
		
		boolean isUnique = customerUnique(newDni);
		if(isUnique) {
			daoCustomers.update(this.getRawCustomer(id), "dni",  parametros);
		} else {
			System.out.println("Error. Ya existe un cliente con el mismo dni");
		}
	}
	
	public void editCustomerdni(String dni, String newDni) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = newDni;

		daoCustomers.update(this.getRawCustomer(dni), "dni",  parametros);
	}

	public void editCustomerPhoneNumber(UUID id, String phoneNumber) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = phoneNumber;


		daoCustomers.update(this.getRawCustomer(id), "phone",  parametros);
	}
	
	public void editCustomerPhoneNumber(String dni, String phoneNumber) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = phoneNumber;


		daoCustomers.update(this.getRawCustomer(dni), "phone",  parametros);
	}
	
	public void editCustomerEmail(UUID id, String email) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = email;

		daoCustomers.update(this.getRawCustomer(id), "email",  parametros);
	}
	
	public void editCustomerEmail(String dni, String email) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = email;

		daoCustomers.update(this.getRawCustomer(dni), "email",  parametros);
	}
	
	public void editCustomerDrivingLicenseType(UUID id, String drivingLicenseType) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = drivingLicenseType;

		daoCustomers.update(this.getRawCustomer(id), "drivingLicenseType",  parametros);
	}
	
	public void editCustomerDrivingLicenseType(String dni, String drivingLicenseType) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = drivingLicenseType;

		daoCustomers.update(this.getRawCustomer(dni), "drivingLicenseType",  parametros);
	}
	
	public void updateCustomer(Customer c, UUID id) {
		//Se crea el String[] con el id del customer que se va a modificar
		String[] parametros = new String[1];
		parametros[0] = String.valueOf(id);

		daoCustomers.update(c, "old",  parametros);
	}

	//obtenemos el cliente a traves de su id
	public Customer getCustomer(UUID id) {
		
		//Obtenemos el Customer con el m�todo privado getRawCustomer()
		Customer customerToView = this.getRawCustomer(id);
		
		//Copiamos el Customer obtenido en uno nuevo, para no devolver el real
		
		Customer customerCopy = new Customer(customerToView.getId(),
											customerToView.getName(),
											customerToView.getFirstSurname(),
											customerToView.getSecondSurname(),
											customerToView.getDni(),
											customerToView.getPhone(),
											customerToView.getEmail(),
											customerToView.getDrivingLicenseType()
											);
		return customerCopy;
	
	}
	

	//obtenemos el cliente a traves de su dni
	public Customer getCustomer(String dni) {
			
		//Obtenemos el Customer con el m�todo privado getRawCustomer()
		Customer customerToView = this.getRawCustomer(dni);
			
		//Copiamos el Customer obtenido en uno nuevo, para no devolver el real
			

		Customer customerCopy = new Customer(customerToView.getId(),
											customerToView.getName(),
											customerToView.getFirstSurname(),
											customerToView.getSecondSurname(),
											customerToView.getDni(),
											customerToView.getPhone(),
											customerToView.getEmail(),
											customerToView.getDrivingLicenseType()
											);
		return customerCopy;
		
	}	
	
	
	public void deleteCustomer(UUID id) {
		daoCustomers.delete(this.getRawCustomer(id));
	}
	
	public void deleteCustomer(String dni) {
		daoCustomers.delete(this.getRawCustomer(dni));
	}
	
	//obtener cliente a traves de su id
	private Customer getRawCustomer(UUID id) {
		
		Optional<Customer> resultedCustomer= daoCustomers.get(id);
		
		if(!resultedCustomer.isPresent()) {
			//Devolvemos una caravana inválida, que se interpretará como que el Customer obtenido no está en la BD
			
			//return new Customer("", "", "", "", "", "", "");
			throw new CustomerNotFoundException("No se encontró ningun cliente con id: " + id);
		}
		
		return resultedCustomer.get();
	}
	
	//obtener cliente a traves de su dni
	private Customer getRawCustomer(String dni) {
		
		Optional<Customer> resultedCustomer= daoCustomers.get(dni);
		
		if(!resultedCustomer.isPresent()) {
			//Devolvemos una caravana inválida, que se interpretará como que el Customer obtenido no está en la BD
			
			//return new Customer("", "", "", "","", "", "");
			throw new CustomerNotFoundException("No se encontró ningun cliente con dni: " + dni);
		}
		
		return resultedCustomer.get();
	}
	
	private boolean customerUnique(String dni) {
		
		boolean unique = true;
		
		List<Customer> customers = this.getAllCustomers();
		
		for(Customer c : customers) {
			if(c.getDni().equals(dni)) {
				System.out.println("Error. Existe ya un cliente con ese dni.");
				unique = false;
			}
		}
		
		return unique;
	}
	
	public class CustomerNotFoundException extends RuntimeException {
		public CustomerNotFoundException(String message) {
			super(message);
		}
	}
	
}
