package testIniciales;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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

public class CheckOutTest extends TestCase {

	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(CheckOutTest.class);
	}
	
	private BookingDao bd;
	private BookingManager bm; 
	private CaravanDao cd;
	private CaravanManager cm;
	private Customer customer;
	private Caravan caravan;
	private Booking b;
	private UUID id;
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Autowired
	private MailNotifier mailNotifier;

	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	
		bd = new BookingDao();
		BookingManager bm = new BookingManager(bd, mailNotifier);
		
		cd = new CaravanDao();
		cm = new CaravanManager(cd);
		
		// creamos  el cliente
		
		String name = "Paco";
		String firstSurname = "Pérez";
		String secondSurname = "García";
		String dni = "54879545j";
		String phone = "652847952";
		String email = "paco.perez@gmail.com";
		String drivingLicenseType = "B";
		
		Customer customer = new Customer(name, firstSurname, secondSurname, dni, phone, email, drivingLicenseType);
		
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
		
		//creamos la reserva
		
		//int id = 123;
		LocalDate ini = LocalDate.of(2023,9,26);
		LocalDate end = LocalDate.of(2023,9,28);
		
		Booking b = new Booking(ini, end, customer, caravan);
		id = b.getId();
		bm.saveBooking(b, cm);
		bm.payAmount(id, b.getPendingPay());
		bm.checkIn(id);
	}
	
	@Test
	public void testCheckOut() {
		LocalDate check = LocalDate.of(2023,9,28);
		bm.CheckOut(id, check);
		Booking b2 = bm.getBooking(id);
		String expectedState = "CheckOut performed";
		
		assertEquals(b2.getState(), expectedState); 
	}
	
	
	/*@Test
	public void pay() {
		
		int total = b.getPendingPay();
		bm.payAmount(id, total - 1);
		Booking b2 = bm.getBooking(id);
		
		assertEquals(b2.getPendingPay(), 1);
		assertEquals(b2.getState(), "Booked - Unpaid");
		
		bm.payAmount(id, 1);
		
		assertEquals(b.getPendingPay(), 1);
		assertEquals(b.getState(), "Booked - Paid");
		
	}*/
	
}
