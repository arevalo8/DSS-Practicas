package com.example.Serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import trabajo.Customer;
import trabajo.Dao;


public class CustomerSerializerDao implements Dao<Customer> {

	public CustomerSerializerDao() {
		super();
	}



	//obtener cliente a traves de su id
	@Override
	public Optional<Customer> get(UUID id) {
		// Devuelve un Optional<Customer> con el Customer cuyo id es id o vac�o si no
		// existe ese Customer
		Customer resultado = null;
		List<Customer> customers = getAll();

		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getId().equals(id)) {
				resultado = customers.get(i);
			}
		}
		return Optional.ofNullable(resultado);
	}

	//obtener cliente a traves de su clave natural -> dni
	@Override
	public Optional<Customer> get(String naturalIdentifier) {
		// Devuelve un Optional<Customer> con el Customer cuyo id es id o vac�o si no
		// existe ese Customer
		Customer resultado = null;
		List<Customer> customers = getAll();

		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getDni().equals(naturalIdentifier)) {
				resultado = customers.get(i);
			}
		}
		return Optional.ofNullable(resultado);
	}


	@Override
	public List<Customer> getAll() {
		List<Customer> customers = DeserializationList();
		return customers;
	}


	@Override
	public void save(Customer c) {
		// Obtener la lista actual de objetos Customer del archivo
		List<Customer> customers = DeserializationList();

		// Agregar el nuevo objeto Customer a la lista
		customers.add(c);

		// Guardar la lista actualizada en el archivo
		SerializationList(customers);
	}



	@Override
	public void update(Customer c, String parameterToModify, String[] params) {
		if(parameterToModify == "name") {
			Optional<Customer> customer = this.get(c.getId()); 
			if(customer.isPresent()){
				(customer.get()).setName(params[0]);
				delete(c);
				save(customer.get());
			}//Si no est� presente no se hace nada
		}

		if(parameterToModify == "firstSurname") {
			Optional<Customer> customer = this.get(c.getId()); 
			if(customer.isPresent()){
				(customer.get()).setFirstSurname(params[0]);
				delete(c);
				save(customer.get());
			}//Si no est� presente no se hace nada
		}

		if(parameterToModify == "secondSurname") {
			Optional<Customer> customer = this.get(c.getId()); 
			if(customer.isPresent()){
				(customer.get()).setSecondSurname(params[0]);
				delete(c);
				save(customer.get());
			}//Si no est� presente no se hace nada
		}

		if(parameterToModify == "dni") {
			Optional<Customer> customer = this.get(c.getId()); 
			if(customer.isPresent()){
				(customer.get()).setDni(params[0]);
				delete(c);
				save(customer.get());
			}//Si no est� presente no se hace nada
		}


		if(parameterToModify == "phone") {
			Optional<Customer> customer = this.get(c.getId()); 
			if(customer.isPresent()){
				(customer.get()).setPhone(params[0]);
				delete(c);
				save(customer.get());
			}//Si no est� presente no se hace nada
		}

		if(parameterToModify == "email") {
			Optional<Customer> customer = this.get(c.getId()); 
			if(customer.isPresent()){
				(customer.get()).setEmail(params[0]);
				delete(c);
				save(customer.get());
			}//Si no est� presente no se hace nada
		}

		if(parameterToModify == "drivingLicenseType") {
			Optional<Customer> customer = this.get(c.getId()); 
			if(customer.isPresent()){
				(customer.get()).setDrivingLicenseType(params[0]);
				delete(c);
				save(customer.get());
			}//Si no est� presente no se hace nada
		}	
	}

	@Override
	public void delete(Customer c) {
		List<Customer> customers = getAll();

		// Encuentra y elimina el objeto
		boolean removed = customers.removeIf(customer -> c.getId().equals(customer.getId()));

		if (removed) {
			// Objeto eliminado con éxito, escribe los datos actualizados en el archivo
			SerializationList(customers);
			
			//System.out.println("Cliente eliminado correctamente.");
		} else {
			//System.out.println("No se encontró el cliente.");
		}
	}




	//Para la serializacion de objetos Booking
	public void SerializationObject(Customer customer) {
		Map<UUID, Customer> customerData = new HashMap<>();
		File file = new File("./customers");

		customerData.put(customer.getId(), customer);

		try (FileOutputStream fout = new FileOutputStream(file);
				ObjectOutputStream object = new ObjectOutputStream(fout)) {
			object.writeObject(customer);
			object.flush();
		} catch (IOException e) {
			throw new RuntimeException("Error al guardar el fichero", e);
		}

		//System.out.println("Cliente serializado correctamente");
	}

	public Customer DeserializationObject() {
		Customer customer;
		try (FileInputStream file = new FileInputStream("./customers");
				ObjectInputStream object = new ObjectInputStream(file)) {
			customer = (Customer) object.readObject();

			//System.out.println(customer.toString());
		} catch (IOException | 	ClassNotFoundException e) {
			throw new RuntimeException("Error al recuperar objetos", e);
			//Booking booking = new Booking();
		}

		//System.out.println("Cliente deserializado correctamente");

		return customer;
	}    

	//Para la serializacion de listas de objetos Booking
	public void SerializationList(List<Customer> customers) {
		Map<UUID, Customer> customersData = new HashMap<>();
		File file = new File("./customers");

		for(Customer c : customers) {
			customersData.put(c.getId(), c);
		}

		try (FileOutputStream fout = new FileOutputStream(file);
				ObjectOutputStream object = new ObjectOutputStream(fout)) {
			object.writeObject(new ArrayList(customersData.values()));
			object.flush();
		} catch (IOException e) {
			throw new RuntimeException("Error al guardar el fichero", e);
		}

		//System.out.println("Clientes serializodas correctamente");
	}

	/*private List<Customer> DeserializationList() {
	    List<Customer> customers = new ArrayList<Customer>();
	    try (FileInputStream file = new FileInputStream("./customers");
	            ObjectInputStream object = new ObjectInputStream(file)) {
	        // Lee la lista de objetos Customer en lugar de un solo Customer
	        customers = (List<Customer>) object.readObject();
	        for (Customer c : customers) {
	            System.out.println(c.toString());
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        throw new RuntimeException("Error al recuperar objetos", e);
	    }

	    System.out.println("Clientes deserializados correctamente");

	    return customers;
	}*/
	private List<Customer> DeserializationList() {
	    List<Customer> customers = new ArrayList<Customer>();
	    try (FileInputStream file = new FileInputStream("./customers");
	            ObjectInputStream object = new ObjectInputStream(file)) {
	        Object data = object.readObject();
	        if (data instanceof Customer) {
	            // Si es un solo objeto Customer, lo agregamos a la lista
	            customers.add((Customer) data);
	        } else if (data instanceof List<?>) {
	            // Si es una lista de objetos Customer, la asignamos directamente
	            customers = (List<Customer>) data;
	        }
	        /*for (Customer c : customers) {
	            System.out.println(c.toString());
	        }*/
	    } catch (FileNotFoundException e) {
	        // Si el archivo no existe, simplemente devolvemos una lista vacía
	        System.out.println("El archivo no existe.");
	    } catch (IOException | ClassNotFoundException e) {
	        throw new RuntimeException("Error al recuperar objetos", e);
	    }

	    //System.out.println("Clientes deserializados correctamente");

	    return customers;
	}





	/*//Para BookingManager
		private void Serialization(CustomerManager cm) throws IOException {
			Map<UUID, Customer> customersData = new HashMap<>();
			File file = new File("./customers");
			List<Customer> customers = cm.getAllCustomers();

			for(Customer c : customers) {
				customersData.put(c.getId(), c);
			}

			try (FileOutputStream fout = new FileOutputStream(file);
					ObjectOutputStream object = new ObjectOutputStream(fout)) {
				object.writeObject(new ArrayList(customersData.values()));
				object.flush();
			} catch (IOException e) {
				throw new RuntimeException("Error al guardar el fichero", e);
			}

			System.out.println("Clientes serializadas correctamente");
		}


		private void Deserialization(CustomerManager cm) throws IOException, ClassNotFoundException {
			List<Customer> customers;
			try (FileInputStream file = new FileInputStream("./customers");
					ObjectInputStream object = new ObjectInputStream(file)) {
				customers = (List<Customer>) object.readObject();
				for(Customer c : customers) {
					System.out.println(c.toString());
					cm.saveCustomer(c);
				}
			} catch (IOException | 	ClassNotFoundException e) {
				throw new RuntimeException("Error al recuperar objetos", e);
				//Booking booking = new Booking();
			}

			System.out.println("Clientes deserializados correctamente");

			//return bookings;
		}    */

}



