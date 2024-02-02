package es.uca.dss.UCAravana.apirest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import trabajo.Caravan;
import trabajo.Dao;

public class CaravanJPADao implements Dao<Caravan> {
	
	CaravanRepository repository;
	
	//@Autowired
	CaravanJPADao(CaravanRepository repository){
		//Se le asigna una instancia de una clase que implementa CaravanRepository (de las que crea Spring)
		this.repository = repository;
	}
	
	@Override
	public List<Caravan> getAll() {
		return repository.findAll();
	}
	
	@Override
	public Optional<Caravan> get(UUID id) {
		return repository.findById(id);
	}
	

	/*public Optional<Caravan> getByPlate(String plate) {
		return repository.findByPlate(plate);
	}*/

	@Override
	public void save(Caravan c) {
		//Necesita otra vuelta, como está ahora modifica la caravana con id el de la nueva caravana, en vez de updatear los
		//valores de otra de otro id distinto
		//
		//SOLUCIÓN: Dejarlo como estaba antes y añadir el método update en CaravanManager, para poder llamarlo directamente en
		//CaravanController, en vez de tener que llamar a save() o a algún parameterupdater() concreto
		repository.save(c);
	}

	/*@Override
	public void update(Caravan t, String parameterToModify, String[] params) {
		//Sustituye los datos de la caravana con id params[0] por los de la caravana t, si no existe, guarda la caravana en la BD
		Optional<Caravan> caravan = repository.findById(UUID.fromString(params[0]));
			      if(caravan.isPresent()){
			        caravan.get().setPlate(t.getPlate());
			        caravan.get().setTypeOfVehicle(t.getTypeOfVehicle());
			        caravan.get().setPricePerDay(t.getPricePerDay());
			        caravan.get().setNumberOfSeats(t.getNumberOfSeats());
			        caravan.get().setWeight(t.getWeight());
			        caravan.get().setReadyToUse(t.isReadyToUse());
			        caravan.get().setBrand(t.getBrand());
			        caravan.get().setModel(t.getModel());
			        
			        repository.save(caravan.get());
			      }else {
				    repository.save(t);
			      };
	}*/
	
	@Override
	public void update(Caravan t, String parameterToModify, String[] params) {
		
		//Sustituye los datos de la caravana con id params[0] por los de la caravana t, si no existe, guarda la caravana en la BD
		Optional<Caravan> caravan = repository.findById(UUID.fromString(params[0]));
			      if(caravan.isPresent()){
			        caravan.get().setPlate(t.getPlate());
			        caravan.get().setTypeOfVehicle(t.getTypeOfVehicle());
			        caravan.get().setPricePerDay(t.getPricePerDay());
			        caravan.get().setNumberOfSeats(t.getNumberOfSeats());
			        caravan.get().setWeight(t.getWeight());
			        caravan.get().setReadyToUse(t.isReadyToUse());
			        caravan.get().setBrand(t.getBrand());
			        caravan.get().setModel(t.getModel());
			        
			        repository.save(caravan.get());
			      }else {
				    repository.save(t);
			      };
	}

	@Override
	public void delete(Caravan t) {
		repository.deleteById(t.getId());
		
	}

	@Override
	public Optional<Caravan> get(String naturalIdentifier) {
		
		Optional<Caravan> resultado = repository.findByPlate(naturalIdentifier);
				
		return resultado;
	}
	
	
}
