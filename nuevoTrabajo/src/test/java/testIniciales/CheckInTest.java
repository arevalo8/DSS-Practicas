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


public class CheckInTest extends TestCase {

	public static void main(String args[]) {
		junit.textui.TestRunner.run(CheckInTest.class);
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

		LocalDate ini = LocalDate.of(2023,9,26);
		LocalDate end = LocalDate.of(2023,9,28);

		b = new Booking(ini, end, customer, caravan); 
		bm.saveBooking(b, cm);
		id = b.getId();
	}

	@Test
	public void testCheckIn() {

		int toPay = b.getPendingPay();   //getPricePerDay() * (int) DAYS.between(ini, end);

		//Pagamos la totalidad de la reserva
		bm.payAmount(id, toPay);
		//Comprobamos el estado de la reserva
		Booking b2 = bm.getBooking(id);
		assertEquals("Booked - Paid", b2.getState());

		//Realizamos el Check-In
		bm.checkIn(id);
		Booking b3 = bm.getBooking(id);
		//Comprobamos de nuevo el estado de la reserva
		assertEquals("CheckIn Performed" , b3.getState());
		assertEquals(0, b3.getPendingPay());
	}

	@Test
	public void testCheckInUnpaid() {

		Booking b = bm.getBooking(id);
		//Intentamos realizar el Check-In sin haber pagado la reserva
		bm.checkIn(id);
		Booking booking = bm.getBooking(id);

		assertEquals("No se puede realizar el Check-In\n"
				+ "Cantidad pendiente de pago: " + b.getPendingPay() , outputStreamCaptor.toString()
				.trim());

		assertEquals(booking.getState(), "Booked - Unpaid");

		//Realizamos un pago sin llegar a abonar el total de la reserva
		int toPay = b.getPendingPay(); 
		bm.payAmount(id, toPay-1);
		Booking b2 = bm.getBooking(id);
		assertEquals("Booked - Unpaid", b2.getState());

		//Intentamos realizar el Check-In sin haber realizado el pago total
		bm.checkIn(id);
		//String expected =
		assertEquals("No se puede realizar el Check-In\n"
				+ "Cantidad pendiente de pago: " + b.getPendingPay() , outputStreamCaptor.toString()
				.trim());

		//Pagamos la cantidad que queda y realizamos el Check-In
		bm.payAmount(id, 1);
		Booking b3 = bm.getBooking(id);
		assertEquals("Booked - Paid", b3.getState());
		bm.checkIn(id);
		assertEquals("CheckIn Performed", b3.getState());
	}


}
