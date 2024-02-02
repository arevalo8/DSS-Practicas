package testIniciales;

import junit.framework.TestCase;

public class manageAvailabilityTest extends TestCase {

	public static void main(String args[]) {
		junit.textui.TestRunner.run(manageAvailabilityTest.class);
	}

/*
	public void testManageAvailability() {
		
		BookingDao bd = new BookingDao();
		BookingManager bm = new BookingManager(bd);
		
		CaravanDao cd = new CaravanDao();
		CaravanManager cm = new CaravanManager(cd);

		// creamos el cliente

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

		// creamos la caravana

		String plateC = "2079 GHL";
		String typeOfCaravanC = "Fifth Wheeler";
		int pricePerDayC = 45;
		int numberOfSeatsC = 5;
		int weightC = 4325;
		String brandC = "Fendt";
		String modelC = "perfilada";

		
		cm.saveCaravan(plateC, typeOfCaravanC, pricePerDayC, numberOfSeatsC, weightC, brandC, modelC);
		Caravan caravan = cm.getCaravan(plateC);
		
		// creamos la reserva

		LocalDate ini = LocalDate.of(2024, 7, 22);
		LocalDate end = LocalDate.of(2024, 7, 28);
		
		
		Booking b = new Booking(ini, end, customer, caravan);
		bm.saveBooking(b, cm);

		// Creamos segunda reserva

		// creamos el cliente

		String name2 = "Pedro";
		String firstSurname2 = "Álvarez";
		String secondSurname2 = "Tomillo";
		String dni2 = "65428952t";
		String phone2 = "685214523";
		ArrayList<String> ph2 = new ArrayList<String>();
		ph2.add(phone2);
		String email2 = "paco.perez@gmail.com";
		String drivingLicenseType2 = "B";

		Customer customer2 = new Customer(name2, firstSurname2, secondSurname2, dni2, ph2, email2,
				drivingLicenseType2);

		// creamos la caravana

		String plateC2 = "8541 RTL";
		String typeOfCaravanC2 = "Fifth Wheeler";
		int pricePerDayC2 = 55;
		int numberOfSeatsC2 = 5;
		int weightC2 = 4325;
		String brandC2 = "Fendt";
		String modelC2 = "perfilada";
		
		
		cm.saveCaravan(plateC2, typeOfCaravanC2, pricePerDayC2, numberOfSeatsC2, weightC2, brandC2,
				modelC2);
		Caravan caravan2 = cm.getCaravan(plateC2);
		
		// creamos la reserva

		LocalDate ini2 = LocalDate.of(2024, 7, 20);
		LocalDate end2 = LocalDate.of(2024, 7, 24);
		
		
		Booking b2 = new Booking(ini2, end2, customer2, caravan2);
		bm.saveBooking(b2, cm);

		// Creamos tercera reserva

		// creamos el cliente

		String name3 = "Juan";
		String firstSurname3 = "Pérez";
		String secondSurname3 = "Salinas";
		String dni3 = "98542514p";
		String phone3 = "658412358";
		ArrayList<String> ph3 = new ArrayList<String>();
		ph3.add(phone3);
		String email3 = "juan.perez@gmail.com";
		String drivingLicenseType3 = "B";

		Customer customer3 = new Customer( name3, firstSurname3, secondSurname3, dni3, ph3, email3, drivingLicenseType3);

		// creamos la caravana

		String plateC3 = "9852 TRK";
		String typeOfCaravanC3 = "Fifth Wheeler";
		int pricePerDayC3 = 50;
		int numberOfSeatsC3 = 6;
		int weightC3 = 4257;
		String brandC3 = "Fendt";
		String modelC3 = "perfilada";
		
		cm.saveCaravan(plateC3, typeOfCaravanC3, pricePerDayC3, numberOfSeatsC3, weightC3, brandC3,
				modelC3);
		Caravan caravan3 = cm.getCaravan(plateC3);
		
		// creamos la reserva

		LocalDate ini3 = LocalDate.of(2024, 8, 06);
		LocalDate end3 = LocalDate.of(2024, 8, 10);

		Booking b3 = new Booking(ini3, end3, customer3, caravan3);
		bm.saveBooking(b3, cm);

		// otra reserva para la misma caravana del cliente 1

		LocalDate ini4 = LocalDate.of(2024, 7, 21);
		LocalDate end4 = LocalDate.of(2024, 7, 23);
		
		Booking b4 = new Booking(ini4, end4, customer, caravan3);
		bm.saveBooking(b4, cm);

		
		
		List<Booking> bookings = bm.getAllBookings();
		for(Booking book : bookings) {
			System.out.println(book.toString());
		}
		

		
		// Test1
		LocalDate iniTest1 = LocalDate.of(2024, 7, 20);
		LocalDate endTest1 = LocalDate.of(2024, 7, 22);

		manageAvailability m = new manageAvailability();
		List<Caravan> L1 = m.viewAvailability(iniTest1, endTest1, cm, bm);
		List<Caravan> expectedTest1 = new ArrayList<Caravan>();
		expectedTest1.add(caravan);
		
		Objects.equals(L1, expectedTest1);
		
		// Test2
		LocalDate iniTest2 = LocalDate.of(2024, 7, 24);
		LocalDate endTest2 = LocalDate.of(2024, 7, 29);

		manageAvailability m2 = new manageAvailability();
		List<Caravan> L2 = m2.viewAvailability(iniTest2, endTest2, cm, bm);
		List<Caravan> expectedTest2 = new ArrayList<Caravan>();
		expectedTest2.add(caravan2);
		expectedTest2.add(caravan3);

		Objects.equals(L2, expectedTest2);

		// Test3
		LocalDate iniTest3 = LocalDate.of(2024, 7, 25);
		LocalDate endTest3 = LocalDate.of(2024, 7, 28);

		manageAvailability m3 = new manageAvailability();
		List<Caravan> L3 = m3.viewAvailability(iniTest3, endTest3, cm, bm);
		List<Caravan> expectedTest3 = new ArrayList<Caravan>();
		expectedTest3.add(caravan2);
		expectedTest3.add(caravan3);

		
		Objects.equals(L3, expectedTest3);

		// Test4
		LocalDate iniTest4 = LocalDate.of(2024, 8, 06);
		LocalDate endTest4 = LocalDate.of(2024, 8, 14);

		manageAvailability m4 = new manageAvailability();
		List<Caravan> L4 = m4.viewAvailability(iniTest4, endTest4, cm, bm);
		List<Caravan> expectedTest4 = new ArrayList<Caravan>();
		expectedTest4.add(caravan);
		expectedTest4.add(caravan2);

		Objects.equals(L4, expectedTest4);

		// Test5
		LocalDate iniTest5 = LocalDate.of(2024, 7, 22);
		LocalDate endTest5 = LocalDate.of(2024, 8, 14);

		manageAvailability m5 = new manageAvailability();
		List<Caravan> L5 = m5.viewAvailability(iniTest5, endTest5, cm, bm);
		List<Caravan> expectedTest5 = new ArrayList<Caravan>();

		Objects.equals(L5, expectedTest5);

		// test6
		LocalDate iniTest6 = LocalDate.of(2024, 8, 12);
		LocalDate endTest6 = LocalDate.of(2024, 8, 14);

		manageAvailability m6 = new manageAvailability();
		List<Caravan> L6 = m6.viewAvailability(iniTest6, endTest6, cm, bm);
		List<Caravan> expectedTest6 = new ArrayList<Caravan>();
		expectedTest6.add(caravan);
		expectedTest6.add(caravan2);
		expectedTest6.add(caravan3);

		Objects.equals(L6, expectedTest6);
		
		
		
		

	}
*/
}
