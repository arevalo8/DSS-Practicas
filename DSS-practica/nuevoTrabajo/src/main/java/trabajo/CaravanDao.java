package trabajo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CaravanDao implements Dao<Caravan> {

	private List<Caravan> caravans = new ArrayList<Caravan>();

	public CaravanDao() {
		super();
	}

	public Optional<Caravan> get(UUID id) {
		// Devuelve un Optional<Caravan> con la Caravan cuyo id es id o vac�o si no
		// existe esa Caravan
		Caravan resultado = null;

		for (int i = 0; i < caravans.size(); i++) {
			if (caravans.get(i).getId().equals(id)) {
			    resultado = caravans.get(i);
			}

		}
		return Optional.ofNullable(resultado);
	}

	//Obtenemos la caravana a traves de su identificador natural -> matricula
	public Optional<Caravan> get(String naturalIdentifier) {
		// Devuelve un Optional<Caravan> con la Caravan cuyo id es id o vac�o si no
		// existe esa Caravan
		Caravan resultado = null;

		for (int i = 0; i < caravans.size(); i++) {
			if (caravans.get(i).getPlate().equals(naturalIdentifier) /*(caravans.get(i)).getPlate() == naturalIdentifier*/) {
				resultado = caravans.get(i);
			}
		}
		return Optional.ofNullable(resultado);
	}
	
	public List<Caravan> getAll() {
		return caravans;
	}

	public void save(Caravan c) {
		caravans.add(c);
	}

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
	    		}//Si no est� presente no se hace nada
	    	}
	    	
	    	if(parameterToModify == "typeofvehicle") {
	    		Optional<Caravan> caravan = this.get(c.getId()); 
	    		if(caravan.isPresent()){
	    			(caravan.get()).setTypeOfVehicle(params[0]);
	    		}//Si no est� presente no se hace nada
	    	}
	    	
	    	if(parameterToModify == "priceperday") {
	    		Optional<Caravan> caravan = this.get(c.getId()); 
	    		if(caravan.isPresent()){
	    			(caravan.get()).setPricePerDay(Integer.parseInt(params[0]));
	    		}//Si no est� presente no se hace nada
	    	}
	    	
	    	if(parameterToModify == "weight") {
	    		Optional<Caravan> caravan = this.get(c.getId()); 
	    		if(caravan.isPresent()){
	    			(caravan.get()).setWeight(Integer.parseInt(params[0]));
	    		}//Si no est� presente no se hace nada
	    	}
	    	
	    	if(parameterToModify == "brand") {
	    		Optional<Caravan> caravan = this.get(c.getId()); 
	    		if(caravan.isPresent()){
	    			(caravan.get()).setBrand(params[0]);
	    		}//Si no est� presente no se hace nada
	    	}
	    	
	    	if(parameterToModify == "model") {
	    		Optional<Caravan> caravan = this.get(c.getId()); 
	    		if(caravan.isPresent()){
	    			(caravan.get()).setModel(params[0]);
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

	public void delete(Caravan c) {
		caravans.remove(c);
	}

}
