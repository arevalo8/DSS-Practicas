package es.uca.dss.UCAravana.apirest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import trabajo.Caravan;
import trabajo.CaravanManager;


@RestController
public class CaravanController {

	
	static CaravanManager cm;
	private static CaravanRepository caravanRepository;
	
	CaravanController(CaravanRepository repository, CaravanManager caravanManager) {
			//Se crea el manager de Caravanas con la base de datos JPA-style como sistema de persistencia
		    cm = caravanManager;
		    caravanRepository = repository;
	}
	
	static CaravanRepository getCaravanRepository(){
		return CaravanController.caravanRepository;
	}
	
	static CaravanManager getCaravanManager(){
		return CaravanController.cm;
	}
	
	@GetMapping("/caravanas")
	public CollectionModel<EntityModel<Caravan>> getAllCaravans() {
	    List<EntityModel<Caravan>> caravans = cm.getAllCaravans().stream()
	        .map(caravan -> {
	            EntityModel<Caravan> model = EntityModel.of(caravan,
	                linkTo(methodOn(CaravanController.class).getCaravan(caravan.getId())).withSelfRel(),
	                linkTo(methodOn(CaravanController.class).getAllCaravans()).withRel("caravans"));
	            return model;
	        })
	        .collect(Collectors.toList());

	    return CollectionModel.of(caravans, linkTo(methodOn(CaravanController.class).getAllCaravans()).withSelfRel());
	}

	
	@GetMapping("/caravanas/{id}")
	public EntityModel<Caravan> getCaravan(@PathVariable UUID id) {
		Caravan c = cm.getCaravan(id);
		if(c.getId() != null) {
			return EntityModel.of(c, //
				      linkTo(methodOn(CaravanController.class).getCaravan(id)).withSelfRel(),
				      linkTo(methodOn(CaravanController.class).getAllCaravans()).withRel("caravans"));
		}else {
			throw new CaravanNotFoundException(id);
		}
	}
	
	@GetMapping("/caravanas/plate/{plate}")
	public EntityModel<Caravan> getCaravan(@PathVariable String plate) {
		Caravan c = cm.getCaravan(plate);
		if(c.getId() != null) {
			return EntityModel.of(c, //
				      linkTo(methodOn(CaravanController.class).getCaravan(plate)).withSelfRel(),
				      linkTo(methodOn(CaravanController.class).getAllCaravans()).withRel("caravans"));
		}else {
			throw new CaravanNotFoundException(plate);
		}
	}
	
	/*@PutMapping("/caravanas/{id}")
	public Caravan replaceCaravan(@RequestBody Caravan newCaravan, @PathVariable UUID id) {
		cm.updateCaravan(newCaravan, id);
		return newCaravan;
	}*/
	
	@PutMapping("/caravanas/{id}")
	public Caravan replaceCaravan(@RequestBody Caravan newCaravan, @PathVariable UUID id) {
	    Caravan old = cm.getCaravan(id);
	    
	    if (newCaravan.getPlate().equals("string")) {
	    	newCaravan.setPlate(old.getPlate());
	    }
	    
	    if (newCaravan.getTypeOfVehicle().equals("string")) {
	    	newCaravan.setTypeOfVehicle(old.getTypeOfVehicle());
	    }
	    
	    if (newCaravan.getPricePerDay() == 0) {
	    	newCaravan.setPricePerDay(old.getPricePerDay());
	    }
	    
	    if (newCaravan.getNumberOfSeats() == 0) {
	    	newCaravan.setNumberOfSeats(old.getNumberOfSeats());
	    }
	    
	    if (newCaravan.getWeight() == 0) {
	    	newCaravan.setWeight(old.getWeight());
	    }
	    
	    if (newCaravan.getBrand().equals("string")) {
	    	newCaravan.setBrand(old.getBrand());
	    }
	    
	    if (newCaravan.getModel().equals("string")) {
	    	newCaravan.setModel(old.getModel());
	    }

	    cm.updateCaravan(newCaravan, id);
	    return newCaravan;
	}

	
	@PutMapping("/caravanas/plate/{plate}")
	public Caravan replaceCaravan(@RequestBody Caravan newCaravan, @PathVariable String plate) {
		Caravan old = cm.getCaravan(plate);
	    
	    if (newCaravan.getPlate().equals("string")) {
	    	newCaravan.setPlate(old.getPlate());
	    }
	    
	    if (newCaravan.getTypeOfVehicle().equals("string")) {
	    	newCaravan.setTypeOfVehicle(old.getTypeOfVehicle());
	    }
	    
	    if (newCaravan.getPricePerDay() == 0) {
	    	newCaravan.setPricePerDay(old.getPricePerDay());
	    }
	    
	    if (newCaravan.getNumberOfSeats() == 0) {
	    	newCaravan.setNumberOfSeats(old.getNumberOfSeats());
	    }
	    
	    if (newCaravan.getWeight() == 0) {
	    	newCaravan.setWeight(old.getWeight());
	    }
	    
	    if (newCaravan.getBrand().equals("string")) {
	    	newCaravan.setBrand(old.getBrand());
	    }
	    
	    if (newCaravan.getModel().equals("string")) {
	    	newCaravan.setModel(old.getModel());
	    }
		
		cm.updateCaravan(newCaravan, cm.getCaravan(plate).getId());
		return newCaravan;
	}
	
	@PostMapping("/caravanas")
	public Caravan saveCaravan(@RequestBody Caravan newCaravan) {
		cm.saveCaravan(newCaravan.getPlate(),
				newCaravan.getTypeOfVehicle(),
				newCaravan.getPricePerDay(),
				newCaravan.getNumberOfSeats(),
				newCaravan.getWeight(),
				newCaravan.getBrand(),
				newCaravan.getModel());
		return newCaravan;
	}
	
	
	
	
	@DeleteMapping("/caravanas/{id}")
	public void deleteCaravan(@PathVariable UUID id) {
		cm.deleteCaravan(id);
	}
	
	@DeleteMapping("/caravanas/plate/{plate}")
	public void deleteCaravan(@PathVariable String plate) {
		cm.deleteCaravan(plate);
	}
}
