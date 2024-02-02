package trabajo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

/**
 * @author Dar�o
 *
 */
@Service
public class CaravanManager {

	private Dao<Caravan> daoCaravans;

	// Constructor
	public CaravanManager(Dao<Caravan> daoCaravans) {
		super();
		this.daoCaravans = daoCaravans; // Se le asigna a DaoCaravans el tipo de sistema de persistencia espec�fico
	}

	public List<Caravan> getAllCaravans() {
		return daoCaravans.getAll();
	}

	public void saveCaravan(String plate, String typeOfVehicle, int pricePerDay, int numberOfSeats, int weight,
			String brand, String model) {
		// Hay que comprobar que la Caravan no tiene un id que ya existe en la base de
		// datos, o mejor ponerlo como precondici�n
		boolean isUnique = CaravanUnique(plate);
		// comprobamos que sea única
		if (isUnique) {

			// creamos la caravana
			Caravan c = new Caravan(plate, typeOfVehicle, pricePerDay, numberOfSeats, weight, brand, model);
			// guardamos la caravana
			daoCaravans.save(c); // Aqu� se recurre a los m�todos de la interfaz para guardar la Caravan
			System.out.println("La caravana se ha almacenado con exito");
		}
	}

	public void saveCaravan(Caravan c) {
		// Hay que comprobar que la Caravan no tiene un id que ya existe en la base de
		// datos, o mejor ponerlo como precondici�n
		boolean isUnique = CaravanUnique(c.getPlate());
		// comprobamos que sea única
		if (isUnique) {
			// guardamos la caravana
			daoCaravans.save(c); // Aqu� se recurre a los m�todos de la interfaz para guardar la Caravan
			System.out.println("La caravana se ha almacenado con exito");
		}
	}

	public void editCaravanPlate(UUID id, String newPlate) {
		// Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = newPlate;

		boolean isUnique = CaravanUnique(newPlate);
		if (isUnique) {
			daoCaravans.update(this.getRawCaravan(id), "plate", parametros);
		}
	}

	public void editCaravanPlate(String plate, String newPlate) {
		// Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = newPlate;

		boolean isUnique = CaravanUnique(newPlate);
		if (isUnique) {
			daoCaravans.update(this.getRawCaravan(plate), "plate", parametros);
		}
	}

	public void editCaravanTypeofVehicle(UUID id, String typeOfVehicle) {
		// Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = typeOfVehicle;

		daoCaravans.update(this.getRawCaravan(id), "typeofvehicle", parametros);
	}

	public void editCaravanTypeofVehicle(String plate, String typeOfVehicle) {
		// Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = typeOfVehicle;

		daoCaravans.update(this.getRawCaravan(plate), "typeofvehicle", parametros);
	}

	public void editCaravanPricePerDay(UUID id, int pricePerDay) {
		// Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = String.valueOf(pricePerDay);

		daoCaravans.update(this.getRawCaravan(id), "priceperday", parametros);
	}

	public void editCaravanPricePerDay(String plate, int pricePerDay) {
		// Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = String.valueOf(pricePerDay);

		daoCaravans.update(this.getRawCaravan(plate), "priceperday", parametros);
	}

	public void editCaravanWeight(UUID id, int weight) {
		// Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = String.valueOf(weight);

		daoCaravans.update(this.getRawCaravan(id), "weight", parametros);
	}

	public void editCaravanWeight(String plate, int weight) {
		// Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = String.valueOf(weight);

		daoCaravans.update(this.getRawCaravan(plate), "weight", parametros);
	}

	public void editCaravanBrand(UUID id, String brand) {
		// Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = brand;

		daoCaravans.update(this.getRawCaravan(id), "brand", parametros);
	}

	public void editCaravanBrand(String plate, String brand) {
		// Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = brand;

		daoCaravans.update(this.getRawCaravan(plate), "brand", parametros);
	}

	public void editCaravanModel(UUID id, String model) {
		// Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = model;

		daoCaravans.update(this.getRawCaravan(id), "model", parametros);
	}

	public void editCaravanModel(String plate, String model) {
		// Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = model;

		daoCaravans.update(this.getRawCaravan(plate), "model", parametros);
	}

	public void updateCaravan(Caravan c, UUID id) {
		// Se crea el String[] con el id de la caravana que se va a modificar
		String[] parametros = new String[1];
		parametros[0] = String.valueOf(id);

		daoCaravans.update(c, "old", parametros);
	}

	// Obtenemos la caravana a través de su id
	public Caravan getCaravan(UUID id) {

		// Obtenemos la Caravan con el m�todo privado getCaravan()
		Caravan caravanToGet = this.getRawCaravan(id);

		// Copiamos la Caravan obtenida en una nueva, para no devolver la real
		Caravan caravanCopy = new Caravan(caravanToGet.getId(), caravanToGet.getPlate(),
				caravanToGet.getTypeOfVehicle(), caravanToGet.getPricePerDay(), caravanToGet.getNumberOfSeats(),
				caravanToGet.getWeight(), caravanToGet.getBrand(), caravanToGet.getModel());
		// int id, String plate, String typeOfVehicle, int pricePerDay, int
		// numberOfSeats, int weight
		return caravanCopy;

	}

	// Obtenemos la caravana a traves de su matricula
	public Caravan getCaravan(String plate) {

		// Obtenemos la Caravan con el m�todo privado getCaravan()
		Caravan caravanToGet = this.getRawCaravan(plate);

		// Copiamos la Caravan obtenida en una nueva, para no devolver la real
		Caravan caravanCopy = new Caravan(caravanToGet.getId(), caravanToGet.getPlate(),
				caravanToGet.getTypeOfVehicle(), caravanToGet.getPricePerDay(), caravanToGet.getNumberOfSeats(),
				caravanToGet.getWeight(), caravanToGet.getBrand(), caravanToGet.getModel());
		// int id, String plate, String typeOfVehicle, int pricePerDay, int
		// numberOfSeats, int weight
		return caravanCopy;

	}

	public void deleteCaravan(UUID id) {
		daoCaravans.delete(this.getRawCaravan(id));
	}

	public void deleteCaravan(String dni) {
		daoCaravans.delete(this.getRawCaravan(dni));
	}

	private Caravan getRawCaravan(UUID id) {

		Optional<Caravan> resultedCaravan = daoCaravans.get(id);

		if (resultedCaravan.isEmpty()) {
			// Devolvemos una caravana inválida, que se interpretará como que la caravana
			// obtenida no está en la BD
			//return new Caravan("", "", 1, 1, 1, "", "");
			throw new CaravanNotFoundException("No se encontró ninguna caravana con id: " + id);
		}

		return resultedCaravan.get();
	}

	private Caravan getRawCaravan(String plate) {

		Optional<Caravan> resultedCaravan = daoCaravans.get(plate);

		if (resultedCaravan.isEmpty()) {
			// Devolvemos una caravana inválida, que se interpretará como que la caravana
			// obtenida no está en la BD
			// return new Caravan("nula", "", 1, 1, 1, "", "");
			throw new CaravanNotFoundException("No se encontró ninguna caravana con la matrícula: " + plate);
		}

		return resultedCaravan.get();
	}

	private boolean CaravanUnique(String plate) {

		boolean unique = true;

		List<Caravan> caravans = this.getAllCaravans();

		for (Caravan c : caravans) {
			if (c.getPlate().equals(plate)) {
				//System.out.println("Error. Existe ya una caravana con la misma matricula.");
				
				unique = false;
				throw new RepeatedPlate("Error. Ya existe una caravana con matricula: " + plate);
			}
		}

		return unique;
	}

	public class CaravanNotFoundException extends RuntimeException {
		public CaravanNotFoundException(String message) {
			super(message);
		}
	}
	
	public class RepeatedPlate extends RuntimeException {
		public RepeatedPlate(String message) {
			super(message);
		}
	}
	
	

}