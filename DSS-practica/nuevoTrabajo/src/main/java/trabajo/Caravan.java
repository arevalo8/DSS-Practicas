package trabajo;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

/**
 * @author Darío Salas Arellano
 *
 */

@jakarta.persistence.Entity
public class Caravan implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private @Id @Column(name="id", nullable = false, unique = true) UUID id;
	//private @Id @GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name="id", nullable = false, unique = true)int id;
	private @Column(name="plate", nullable = false, unique = true) String plate;
	private @Column(name="type_of_vehicle", nullable = false) String typeOfVehicle;
	private @Column(name="price_per_day", nullable = false) int pricePerDay;
	private @Column(name="number_of_seats", nullable = false) int numberOfSeats;
	private @Column(name="weight", nullable = false) int weight;
	private @Column(name="ready_to_use", nullable = false) boolean readyToUse;
	private @Column(name="brand", nullable = false) String brand;
	private @Column(name="model", nullable = false) String model;
	

	public Caravan() {
		this.plate = "";
		this.typeOfVehicle = "";
		this.pricePerDay = 1;
		this.numberOfSeats = 1;
		this.weight = 1;
		this.readyToUse = true;
		this.brand = "";
		this.model = "";
	}
	public Caravan(String plate, String typeOfVehicle, int pricePerDay, int numberOfSeats, int weight, String brand, String model) {
		super();
		
		//this.id = id;
		this.id = UUID.randomUUID();
		this.plate = plate;
		this.typeOfVehicle = typeOfVehicle;
		this.pricePerDay = pricePerDay;
		this.numberOfSeats = numberOfSeats;
		this.weight = weight;
		this.readyToUse = true;
		this.brand = brand;
		this.model = model;
	}
	
	//Para mantener la copia de elementos
	public Caravan(UUID id, String plate, String typeOfVehicle, int pricePerDay, int numberOfSeats, int weight, String brand, String model) {
		super();
		
		//this.id = id;
		this.id = id;
		this.plate = plate;
		this.typeOfVehicle = typeOfVehicle;
		this.pricePerDay = pricePerDay;
		this.numberOfSeats = numberOfSeats;
		this.weight = weight;
		this.readyToUse = true;
		this.brand = brand;
		this.model = model;
	}



	public UUID getId() {
		return id;
	}
	
	
	/*public void setId(int id) {
		this.id=id;
	}*/

	
	public String getPlate() {
		return plate;
	}


	public void setPlate(String plate) {
		this.plate = plate;
	}


	public String getTypeOfVehicle() {
		return typeOfVehicle;
	}


	public void setTypeOfVehicle(String typeOfVehicle) {
		this.typeOfVehicle = typeOfVehicle;
	}


	public int getPricePerDay() {
		return pricePerDay;
	}


	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}


	public int getNumberOfSeats() {
		return numberOfSeats;
	}


	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public boolean isReadyToUse() {
		return readyToUse;
	}


	public void setReadyToUse(boolean isReadyToUse) {
		this.readyToUse = isReadyToUse;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, plate, typeOfVehicle, pricePerDay, numberOfSeats, weight, readyToUse, brand, model);
    }

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Caravan caravan = (Caravan) o;
	    return Objects.equals(id, caravan.id);
	}

	

	@Override
	public String toString() {
		return "Caravan with id  " + id + ": plate=" + plate + ", typeOfVehicle=" + typeOfVehicle + ", pricePerDay="
				+ pricePerDay + ", numberOfSeats=" + numberOfSeats + ", weight=" + weight + ", readyToUse="
				+ readyToUse + ", brand=" + brand + ", model=" + model + "\n";
	}
	
	
}
