package es.uca.dss.UCAravana.apirest;

import java.util.UUID;

class CaravanNotFoundException extends RuntimeException {

  CaravanNotFoundException(UUID id) {
    super("Could not find caravan " + id);
  }
  CaravanNotFoundException(String plate) {
	    super("Could not find caravan " + plate);
	  }
}