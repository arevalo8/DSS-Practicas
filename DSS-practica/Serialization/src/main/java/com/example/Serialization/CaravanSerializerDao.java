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

import trabajo.Caravan;
import trabajo.Customer;
import trabajo.Dao;

public class CaravanSerializerDao implements Dao<Caravan> {
    
	public CaravanSerializerDao() {
		super();
	}

		@Override
		public Optional<Caravan> get(UUID id) {
			// Devuelve un Optional<Caravan> con la Caravan cuyo id es id o vac�o si no
			// existe esa Caravan
			Caravan resultado = null;
			List<Caravan> caravans = this.DeserializationList();
			
			for (int i = 0; i < caravans.size(); i++) {
				if (caravans.get(i).getId().equals(id)) {
				    resultado = caravans.get(i);
				}

			}
			return Optional.ofNullable(resultado);
		}
		
		
		@Override
		//Obtenemos la caravana a traves de su identificador natural -> matricula
		public Optional<Caravan> get(String naturalIdentifier) {
			// Devuelve un Optional<Caravan> con la Caravan cuyo id es id o vac�o si no
			// existe esa Caravan
			Caravan resultado = null;
			List<Caravan> caravans = this.DeserializationList();
			
			for (int i = 0; i < caravans.size(); i++) {
				if (caravans.get(i).getPlate().equals(naturalIdentifier) /*(caravans.get(i)).getPlate() == naturalIdentifier*/) {
					resultado = caravans.get(i);
				}
			}
			return Optional.ofNullable(resultado);
		}
		
		@Override
		public List<Caravan> getAll() {
			List<Caravan> caravans = this.DeserializationList();
			return caravans;
		}
		
		@Override
		public void save(Caravan c) {
			// Obtener la lista actual de objetos Customer del archivo
			List<Caravan> caravans = DeserializationList();
			
			// Agregar el nuevo objeto Caravan a la lista
			caravans.add(c);
			
			// Guardar la lista actualizada en el archivo
			SerializationList(caravans);
		}
		
		
		@Override
		public void update(Caravan c, String parameterToModify, String[] params) {
			//if dejado por retrocompatibilidad
			if(parameterToModify == "plate" ||
					parameterToModify == "typeofvehicle" ||
					parameterToModify == "priceperday" ||
					parameterToModify == "weight" ||
					parameterToModify == "brand" ||
					parameterToModify == "model")
			{
				
		    	if(parameterToModify == "plate") {
		    		Optional<Caravan> caravan = this.get(c.getId()); 
		    		if(caravan.isPresent()){
		    			(caravan.get()).setPlate(params[0]);
		    			delete(c);
		    			save(caravan.get());
		    		}//Si no est� presente no se hace nada
		    	}
		    	
		    	if(parameterToModify == "typeofvehicle") {
		    		Optional<Caravan> caravan = this.get(c.getId()); 
		    		if(caravan.isPresent()){
		    			(caravan.get()).setTypeOfVehicle(params[0]);
		    			delete(c);
		    			save(caravan.get());
		    		}//Si no est� presente no se hace nada
		    	}
		    	
		    	if(parameterToModify == "priceperday") {
		    		Optional<Caravan> caravan = this.get(c.getId()); 
		    		if(caravan.isPresent()){
		    			(caravan.get()).setPricePerDay(Integer.parseInt(params[0]));
		    			delete(c);
		    			save(caravan.get());
		    		}//Si no est� presente no se hace nada
		    	}
		    	
		    	if(parameterToModify == "weight") {
		    		Optional<Caravan> caravan = this.get(c.getId()); 
		    		if(caravan.isPresent()){
		    			(caravan.get()).setWeight(Integer.parseInt(params[0]));
		    			delete(c);
		    			save(caravan.get());
		    		}//Si no est� presente no se hace nada
		    	}
		    	
		    	if(parameterToModify == "brand") {
		    		Optional<Caravan> caravan = this.get(c.getId()); 
		    		if(caravan.isPresent()){
		    			(caravan.get()).setBrand(params[0]);
		    			delete(c);
		    			save(caravan.get());
		    		}//Si no est� presente no se hace nada
		    	}
		    	
		    	if(parameterToModify == "model") {
		    		Optional<Caravan> caravan = this.get(c.getId()); 
		    		if(caravan.isPresent()){
		    			(caravan.get()).setModel(params[0]);
		    			delete(c);
		    			save(caravan.get());
		    		}//Si no est� presente no se hace nada
		    	}
			}else {
				//Código de modificar todos los datos de la caravana
	    		Optional<Caravan> caravan = this.get(UUID.fromString(params[0])); 
	    		if(caravan.isPresent()){
	    			this.delete(caravan.get());
	    			this.save(c);
	    		}//Si no está presente no se hace nada
			}
		}
		
		@Override
		public void delete(Caravan c) {
			List<Caravan> caravans = getAll();
			
			 // Encuentra y elimina el objeto
	        boolean removed = caravans.removeIf(caravan -> c.getId().equals(caravan.getId()));
	        
	        if (removed) {
	            // Objeto eliminado con éxito, escribe los datos actualizados en el archivo
	            SerializationList(caravans);
	           // System.out.println("Caravana eliminada correctamente.");
	        } else {
	           // System.out.println("No se encontró la caravana.");
	        }
		}


		//Para la serializacion de objetos Booking
		public void SerializationObject(Caravan caravan)  {
			Map<UUID, Caravan> caravanData = new HashMap<>();
			File file = new File("./caravans");

			caravanData.put(caravan.getId(), caravan);

			try (FileOutputStream fout = new FileOutputStream(file);
					ObjectOutputStream object = new ObjectOutputStream(fout)) {
				object.writeObject(caravan);
				object.flush();
			} catch (IOException e) {
				throw new RuntimeException("Error al guardar el fichero", e);
			}

			//System.out.println("Caravana serializada correctamente");
		}


		private Caravan DeserializationObject() throws IOException, ClassNotFoundException {
			Caravan caravan;
			try (FileInputStream file = new FileInputStream("./caravans");
					ObjectInputStream object = new ObjectInputStream(file)) {
				caravan = (Caravan) object.readObject();

				//System.out.println(caravan.toString());
			} catch (IOException | 	ClassNotFoundException e) {
				throw new RuntimeException("Error al recuperar objetos", e);
				//Booking booking = new Booking();
			}

			//System.out.println("Caravana deserializado correctamente");

			return caravan;
		}    

		//Para la serializacion de listas de objetos Booking
		private void SerializationList(List<Caravan> caravans) {
			Map<UUID, Caravan> caravansData = new HashMap<>();
			File file = new File("./caravans");

			for(Caravan c : caravans) {
				caravansData.put(c.getId(), c);
			}

			try (FileOutputStream fout = new FileOutputStream(file);
					ObjectOutputStream object = new ObjectOutputStream(fout)) {
				object.writeObject(new ArrayList(caravansData.values()));
				object.flush();
			} catch (IOException e) {
				throw new RuntimeException("Error al guardar el fichero", e);
			}

			//System.out.println("Reservas serializadas correctamente");
		}
		
		private List<Caravan> DeserializationList() {
		    List<Caravan> caravans = new ArrayList<Caravan>();
		    try (FileInputStream file = new FileInputStream("./caravans");
		            ObjectInputStream object = new ObjectInputStream(file)) {
		        Object data = object.readObject();
		        if (data instanceof Caravan) {
		            // Si es un solo objeto Caravan, lo agregamos a la lista
		            caravans.add((Caravan) data);
		        } else if (data instanceof List<?>) {
		            // Si es una lista de objetos Caravan, la asignamos directamente
		            caravans = (List<Caravan>) data;
		        }
		      //  for (Caravan c : caravans) {
		      //      System.out.println(c.toString());
		      //  }
		    } catch (FileNotFoundException e) {
		        // Si el archivo no existe, simplemente devolvemos una lista vacía
		        //System.out.println("El archivo no existe.");
		    } catch (IOException | ClassNotFoundException e) {
		        throw new RuntimeException("Error al recuperar objetos", e);
		    }

		  //  System.out.println("Caravanas deserializadas correctamente");

		    return caravans;
		}



		/*private List<Caravan> DeserializationList()  {
			List<Caravan> caravans;
			try (FileInputStream file = new FileInputStream("./caravans");
					ObjectInputStream object = new ObjectInputStream(file)) {
				caravans = (List<Caravan>) object.readObject();
				for(Caravan c : caravans) {
					System.out.println(c.toString());
				}
			} catch (IOException | 	ClassNotFoundException e) {
				throw new RuntimeException("Error al recuperar objetos", e);
				//Booking booking = new Booking();
			}

			return caravans;
		}    */

		/*
			//Para BookingManager
			public void Serialization(CaravanManager cm) throws IOException {
				Map<UUID, Caravan> caravansData = new HashMap<>();
				File file = new File("./caravans");
				List<Caravan> caravans = cm.getAllCaravans();

				for(Caravan c : caravans) {
					caravansData.put(c.getId(), c);
				}

				try (FileOutputStream fout = new FileOutputStream(file);
						ObjectOutputStream object = new ObjectOutputStream(fout)) {
					object.writeObject(new ArrayList(caravansData.values()));
					object.flush();
				} catch (IOException e) {
					throw new RuntimeException("Error al guardar el fichero", e);
				}

				System.out.println("Caravanas serializadas correctamente");
			}


			public void Deserialization(CaravanManager cm) throws IOException, ClassNotFoundException {
			  	List<Caravan> caravans;
				try (FileInputStream file = new FileInputStream("./caravans");
						ObjectInputStream object = new ObjectInputStream(file)) {
					caravans = (List<Caravan>) object.readObject();
					for(Caravan c : caravans) {
						System.out.println(c.toString());
						cm.saveCaravan(c);
					}
				} catch (IOException | 	ClassNotFoundException e) {
					throw new RuntimeException("Error al recuperar objetos", e);
					//Booking booking = new Booking();
				}

				System.out.println("Caravanas deserializadas correctamente");

				//return bookings;
			}  
		 */
}

