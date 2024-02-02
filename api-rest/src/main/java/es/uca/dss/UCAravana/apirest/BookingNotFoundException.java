package es.uca.dss.UCAravana.apirest;

import java.util.UUID;

class BookingNotFoundException extends RuntimeException {

	 BookingNotFoundException(UUID id) {
	   super("Could not find booking " + id);
	 }
}