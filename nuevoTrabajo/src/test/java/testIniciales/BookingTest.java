package testIniciales;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Test;

import junit.framework.TestCase;
import trabajo.Booking;
import trabajo.Caravan;
import trabajo.Customer;

public class BookingTest extends TestCase {
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(BookingTest.class);
	}
	
	@Test 
	public void testBooking() {
		
		// creamos  el cliente
		
		
		String name = "Paco";
		String firstSurname = "Pérez";
		String secondSurname = "García";
		String dni = "54879545j";
		String phone = "652847952";
		String email = "paco.perez@gmail.com";
		String drivingLicenseType = "B";
		
		Customer customer = new Customer(name, firstSurname, secondSurname, dni, phone, email, drivingLicenseType);
		UUID idCu = customer.getId();
		
		//creamos la caravana
		
		String plateC = "2079 GHL";
		String typeOfCaravanC = "Fifth Wheeler";
		int pricePerDayC = 45;
		int numberOfSeatsC = 5;
		int weightC = 4325;
		String brandC = "Fendt";
		String modelC = "perfilada";

		
		Caravan caravan = new Caravan(plateC, typeOfCaravanC, pricePerDayC, numberOfSeatsC, weightC, brandC, modelC);
		UUID idCa = caravan.getId();
		
		//creamos la reserva
		
		LocalDate ini = LocalDate.of(2023,03,26);
		LocalDate end = LocalDate.of(2023,03,28);
		String state = "Booked - Unpaid";

		
		Booking b = new Booking(ini, end, customer, caravan);
		
		
		//assertEquals(id, b.getId());
		assertEquals(ini, b.getIniDate());
		assertEquals(end, b.getEndDate());
		assertEquals(state, b.getState());
		assertEquals(idCu, b.fromCustomerGetId());
		assertEquals(idCa, b.fromCaravanGetId());	
	}
}
