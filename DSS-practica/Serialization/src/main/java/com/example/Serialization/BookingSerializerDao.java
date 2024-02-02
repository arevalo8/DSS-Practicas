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

import trabajo.Booking;
import trabajo.Dao;


public class BookingSerializerDao implements Dao<Booking> {
	

	
	public BookingSerializerDao() {
		super();
	}
	
	
	//Para la serializacion de objetos Booking
	@Override
	public void save(Booking b) {
		// Obtener la lista actual de objetos Customer del archivo
		List<Booking> bookings = (List<Booking>) DeserializationList();

		// Agregar el nuevo objeto Caravan a la lista
		bookings.add(b);

		// Guardar la lista actualizada en el archivo
		SerializationList(bookings);

	}
	/*@Override
	public void save(Booking b)   {
		Map<UUID, Booking> bookingData = new HashMap<>();
		File file = new File("./bookings");
		
		bookingData.put(b.getId(), b);
		
		try (FileOutputStream fout = new FileOutputStream(file);
				ObjectOutputStream object = new ObjectOutputStream(fout)) {
			object.writeObject(b);
			object.flush();
		} catch (IOException e) {
			throw new RuntimeException("Error al guardar el fichero", e);
		}
		
		System.out.println("Reserva serializada correctamente");
	}*/
	
	
	/*@Override
	public Booking DeserializationObject() throws IOException, ClassNotFoundException {
		Booking booking;
		try (FileInputStream file = new FileInputStream("./bookings");
				ObjectInputStream object = new ObjectInputStream(file)) {
			booking = (Booking) object.readObject();
			
			System.out.println(booking.toString());
		} catch (IOException | 	ClassNotFoundException e) {
			throw new RuntimeException("Error al recuperar objetos", e);
			//Booking booking = new Booking();
		}
		
		System.out.println("Deserializado correctamente");
		
		return booking;
	}    */
	

	
	
	@Override
	public List<Booking> getAll() {
		List<Booking> bookings = this.DeserializationList();
		return bookings;
	}

	
	
	

	@Override
	public Optional<Booking> get(UUID id) {
		// Devuelve un Optional<Booking> con la Booking cuyo id es id o vac�o si no
		// existe esa Booking
		Booking resultado = null;


		List<Booking> bookings;
		try (FileInputStream file = new FileInputStream("./bookings");
				ObjectInputStream object = new ObjectInputStream(file)) {
			bookings = (List<Booking>) object.readObject();
			for(Booking b : bookings) {
				if(b.getId().equals(id)) {
					resultado = b;
				}
			}
		} catch (IOException | 	ClassNotFoundException e) {
			throw new RuntimeException("Error al recuperar objetos", e);
			//Booking booking = new Booking();
		}

		//System.out.println("Deserializado correctamente");


		return Optional.ofNullable(resultado);
	}
	
	
	@Override
	public void update(Booking c, String parameterToModify, String[] params) {
		if(parameterToModify == "state" ||
				parameterToModify == "pendingPay") {
	    	if(parameterToModify == "state") {
	    		Optional<Booking> booking = this.get(c.getId()); 
	    		if(booking.isPresent()){
	    			(booking.get()).setState(params[0]);
	    			delete(c);
	    			save(booking.get());
	    		}//Si no est� presente no se hace nada
	    	}
	    	
	    	if(parameterToModify == "pendingPay") {
	    		Optional<Booking> booking = this.get(c.getId()); 
	    		if(booking.isPresent()){
	    			(booking.get()).setPendingPay(Integer.parseInt(params[0]));
	    			delete(c);
	    			save(booking.get());
	    		}//Si no est� presente no se hace nada
	    	}
		}else {
			//Código de modificar todos los datos de la caravana
    		Optional<Booking> booking = this.get(UUID.fromString(params[0])); 
    		if(booking.isPresent()){
    			this.delete(booking.get());
    			this.save(c);
    		}//Si no está presente no se hace nada
		}	
    	
	}
	
	@Override
	public void delete(Booking b) {
		 // Método para eliminar un objeto del archivo

	        List<Booking> bookings = getAll();

	        // Encuentra y elimina el objeto
	        boolean removed = bookings.removeIf(booking -> b.getId().equals(booking.getId()));

	        if (removed) {
	            // Objeto eliminado con éxito, escribe los datos actualizados en el archivo
	            SerializationList(bookings);
	            //System.out.println("Reserva eliminada correctamente.");
	        } else {
	            System.out.println("No se encontró la reserva con id " + b.getId());
	        }
	 }
	
	//Lo añadimos por compatibilidad con el Dao
	@Override
	public Optional<Booking> get(String naturalIdentifier) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	private List<Booking> DeserializationList() {
	    List<Booking> bookings = new ArrayList<Booking>();
	    try (FileInputStream file = new FileInputStream("./bookings");
	            ObjectInputStream object = new ObjectInputStream(file)) {
	        Object data = object.readObject();
	        if (data instanceof Booking) {
	            // Si es un solo objeto Booking, lo agregamos a la lista
	            bookings.add((Booking) data);
	        } else if (data instanceof List<?>) {
	            // Si es una lista de objetos Booking, la asignamos directamente
	            bookings = (List<Booking>) data;
	        }
	        // Aquí verificamos si la lista está vacía y la devolvemos vacía en ese caso
	        if (bookings.isEmpty() || bookings.get(0).getCustomer() == null) {
	            return new ArrayList<Booking>();
	        }
	    } catch (FileNotFoundException e) {
	        // Si el archivo no existe, simplemente devolvemos una lista vacía
	        return new ArrayList<Booking>();
	    } catch (IOException | ClassNotFoundException e) {
	        throw new RuntimeException("Error al recuperar objetos", e);
	    }

	    return bookings;
	}


	//Para la serializacion de listas de objetos Booking
	
	public void SerializationList(List<Booking> bookings) {
		Map<UUID, Booking> bookingsData = new HashMap<>();
		File file = new File("./bookings");
		
		for(Booking b : bookings) {
			bookingsData.put(b.getId(), b);
		}
		
		try (FileOutputStream fout = new FileOutputStream(file);
				ObjectOutputStream object = new ObjectOutputStream(fout)) {
			object.writeObject(new ArrayList(bookingsData.values()));
			object.flush();
		} catch (IOException e) {
			throw new RuntimeException("Error al guardar el fichero", e);
		}
		
		//System.out.println("Reservas serializadas correctamente");
	}

	
}

