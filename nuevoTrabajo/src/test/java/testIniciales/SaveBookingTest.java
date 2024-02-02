package testIniciales;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.TestCase;
import mailSender.MailNotifier;
import trabajo.Booking;
import trabajo.BookingDao;
import trabajo.BookingManager;
import trabajo.Caravan;
import trabajo.CaravanDao;
import trabajo.CaravanManager;
import trabajo.Customer;



public class SaveBookingTest extends TestCase{
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(SaveBookingTest.class);
	}
	
	@Autowired
	private MailNotifier mailNotifier;
	
	public void testSaveBooking() {
		
		BookingDao bd = new BookingDao();
		BookingManager bm = new BookingManager(bd, mailNotifier);
		
		CaravanDao cd = new CaravanDao();
		CaravanManager cm = new CaravanManager(cd);
		
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
		
		
		cm.saveCaravan(plateC, typeOfCaravanC, pricePerDayC, numberOfSeatsC, weightC, brandC, modelC);
		Caravan caravan = cm.getCaravan(plateC);
		UUID idCa = caravan.getId();
		
		//creamos la reserva
		

		LocalDate ini = LocalDate.of(2023,9,26);
		LocalDate end = LocalDate.of(2023,9,28);
		
		
		Booking booking = new Booking(ini, end, customer, caravan);
		UUID idB = booking.getId();
		bm.saveBooking(booking, cm);
		Booking b = bm.getBooking(idB);
		
		
		
		assertEquals(ini, b.getIniDate());
		assertEquals(end, b.getEndDate());
		assertEquals("Booked - Unpaid", b.getState());
		assertEquals(idCu, b.fromCustomerGetId());
		assertEquals(idCa, b.fromCaravanGetId());
	}
	
	
}
