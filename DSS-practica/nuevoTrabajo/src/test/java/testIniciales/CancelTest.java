package testIniciales;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
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


public class CancelTest extends TestCase {
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(CancelTest.class);
	}

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Autowired
	private MailNotifier mailNotifier;

	
	@Test
	public void testCancel() {


		// creamos  el cliente

		BookingDao bd = new BookingDao();
		BookingManager bm = new BookingManager(bd, mailNotifier);

		CaravanDao cd = new CaravanDao();
		CaravanManager cm = new CaravanManager(cd);



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


		LocalDate ini = LocalDate.of(2023,11,26);
		LocalDate end = LocalDate.of(2023,11,28);


		Booking b = new Booking(ini, end, customer, caravan);
		UUID id = b.getId();
		bm.saveBooking(b, cm);
		//Booking b = bm.getBooking(id);


		bm.cancel(id);

		List<Booking> bookings = bd.getAll();
		List<Booking> expected = new ArrayList<Booking>();


		assertEquals(bookings, expected);
	}
	
	
	//realizamos la cancelacion a 1 solo dia de la fecha inicial de la reserva
	/*@Test
	public void testPoliticCancel() {
		
		//Para obtener la salida por pantalla
		System.setOut(new PrintStream(outputStreamCaptor));

		// creamos  el cliente

		BookingDao bd = new BookingDao();
		BookingManager bm = new BookingManager(bd);

		CaravanDao cd = new CaravanDao();
		CaravanManager cm = new CaravanManager(cd);



		String name = "Paco";
		String firstSurname = "Pérez";
		String secondSurname = "García";
		String dni = "54879545j";
		String phone = "652847952";
		ArrayList<String> ph = new ArrayList<String>();
		ph.add(phone);
		String email = "paco.perez@gmail.com";
		String drivingLicenseType = "B";

		Customer customer = new Customer(name, firstSurname, secondSurname, dni, ph, email, drivingLicenseType);

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


		LocalDate ini = LocalDate.now().plusDays(6);
		LocalDate end = LocalDate.now().plusDays(8);


		Booking b = new Booking(ini, end, customer, caravan);
		UUID id = b.getId();
		bm.saveBooking(b, cm);
		//Booking b = bm.getBooking(id);
		

		bm.cancel(id);

		String salidaReal = outputStreamCaptor.toString().trim();
		String salidaEsperada = "Error. La cancelacion de la reserva debe realizarse con un minimo de "
				+ "2 dias de antelacion";

		assertEquals(salidaEsperada, salidaReal);

	}*/
	
	

}
