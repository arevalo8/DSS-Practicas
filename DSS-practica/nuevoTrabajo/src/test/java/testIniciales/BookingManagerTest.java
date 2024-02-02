package testIniciales;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.Test;

import junit.framework.TestCase;
import mailSender.DummyEmailSender;
import mailSender.MailNotifier;
import trabajo.Booking;
import trabajo.BookingDao;
import trabajo.BookingManager;
import trabajo.Caravan;
import trabajo.CaravanDao;
import trabajo.CaravanManager;
import trabajo.Customer;


public class BookingManagerTest extends TestCase {

	public static void main(String args[]) {
		junit.textui.TestRunner.run(BookingManagerTest.class);
	}
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	
	private BookingManager bm;
	private MailNotifier mockMailNotifier;
	private DummyEmailSender dummyEmailSender;
	
	/*@Before
	public void setUp() {
		dummyEmailSender = new DummyEmailSender();
		BookingDao bd = new BookingDao();
		BookingManager bm = new BookingManager(bd, dummyEmailSender);
	}*/
	
	@Test
	public void testBookingManager() {
		CaravanDao cd = new CaravanDao();
		CaravanManager cm = new CaravanManager(cd);
		
		Caravan caravan = new Caravan("2978 GHP", "Grande", 15, 6, 1500, "Mercedes", "Camper");
		cm.saveCaravan("2978 GHP", "Grande", 15, 6, 1500, "Mercedes", "Camper");
		
		
		String ph = "629748521";
		Customer customer = new Customer("Juan", "Garcia", "Aparicio", "48524869p", ph, "javi8arevalo@gmail.com", "B96");
		
		

		LocalDate ini = LocalDate.of(2024, 06, 22);
		LocalDate end = LocalDate.of(2024, 06, 25);
		
		
		dummyEmailSender = new DummyEmailSender();
		BookingDao bd = new BookingDao();
		BookingManager bm = new BookingManager(bd, dummyEmailSender);		
		
		Booking b = new Booking(ini, end, customer, caravan);
		
		bm.saveBooking(b, cm);
		UUID id = b.getId();
		Booking b2 = bm.getBooking(id);
		
		assertEquals(b.getId(), b2.getId());
		assertEquals(b.getIniDate(), b2.getIniDate());
		assertEquals(b.fromCustomerGetId(), b2.fromCustomerGetId());
		assertEquals(b.fromCaravanGetId(), b2.fromCaravanGetId());
		assertEquals(b.getEndDate(), b2.getEndDate());
		
		
	}
	



	@Test
	public void testCheckIllegalDate() {
		// Para obtener la salida por pantalla
		System.setOut(new PrintStream(outputStreamCaptor));

		CaravanDao cd = new CaravanDao();
		CaravanManager cm = new CaravanManager(cd);

		Caravan caravan = new Caravan("2978 GHP", "Grande", 15, 6, 1500, "Mercedes", "Camper");
		cm.saveCaravan(caravan);

		Customer customer = new Customer("Juan", "Garcia", "Aparicio", "48524869p", "629748521", "juan@gmail.com", "B96");

		DummyEmailSender dummyEmailSender = new DummyEmailSender();
		BookingDao bd = new BookingDao();
		BookingManager bm = new BookingManager(bd, dummyEmailSender);

		// Pasamos una fecha final anterior a la inicial, lo que debería lanzar una excepción
		LocalDate ini = LocalDate.of(2024, 03, 15);
		LocalDate end = LocalDate.of(2024, 03, 13);

		// Utiliza assertThrows para verificar que se lanza la excepción esperada
		Exception exception = assertThrows(BookingManager.InvalidDate.class, () -> {
			Booking b = new Booking(ini, end, customer, caravan);
			UUID id = b.getId();
			bm.saveBooking(b, cm);
		});

		// Verifica el mensaje de la excepción
		String expectedMessage = "Error. Las fechas introducidas son incorrectas.";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	/*@Test
	public void testCheckPolitics() {
		//Para obtener la salida por pantalla
		System.setOut(new PrintStream(outputStreamCaptor));


		CaravanDao cd = new CaravanDao();
		CaravanManager cm = new CaravanManager(cd);

		Caravan caravan = new Caravan("2978 GHP", "Grande", 15, 6, 1500, "Mercedes", "Camper");
		cm.saveCaravan(caravan);


		Customer customer = new Customer("Juan", "Garcia", "Aparicio", "48524869p", "629748521", "juan@gmail.com", "B96");

		MailNotifier mn = new MailNotifier();
		
		BookingDao bd = new BookingDao();
		BookingManager bm = new BookingManager(bd, mn);

		//pasamos una fecha final a menos de 2 dias de comenzar la reserva
		LocalDate ini = LocalDate.now().plusDays(1);
		LocalDate end = LocalDate.now().plusDays(6);


		Booking b = new Booking(ini, end, customer, caravan);
		bm.saveBooking(b, cm);

		String salidaReal = outputStreamCaptor.toString().trim();
		String salidaEsperada = "Lo siento, la reserva debe realizarse con un mínimo de dos días de antelación";

		assertEquals(salidaEsperada, salidaReal);

	}*/
	
	
}	