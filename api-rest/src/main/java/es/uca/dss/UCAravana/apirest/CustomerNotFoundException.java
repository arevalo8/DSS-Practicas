package es.uca.dss.UCAravana.apirest;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {

	CustomerNotFoundException(UUID id) {
	    super("Could not find customer " + id);
	}
	
	CustomerNotFoundException(String dni) {
	    super("Could not find customer " + dni);
	}
}
