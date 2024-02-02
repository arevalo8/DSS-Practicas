package com.example.Serialization;

public class BookingSerializerTest {
	
	/*public static void main(String args[]) {
		junit.textui.TestRunner.run(BookingSerializerTest.class);
	}*/

	/*@Test
	public void testBookingSerializer() throws IOException, ClassNotFoundException {
		
		BookingDao bd = new BookingDao();
		BookingManager bm = new BookingManager(bd);
		
		CaravanDao cd = new CaravanDao();
		CaravanManager cm = new CaravanManager(cd);
		
		
		// creamos  el cliente
		
				int idCu = 7;
				String name = "Paco";
				String firstSurname = "Pérez";
				String secondSurname = "García";
				String dni = "54879545j";
				String phone = "652847952";
				ArrayList<String> ph = new ArrayList<String>();
				ph.add(phone);
				String email = "paco.perez@gmail.com";
				String drivingLicenseType = "B";
				
				Customer customer = new Customer(idCu, name, firstSurname, secondSurname, dni, ph, email, drivingLicenseType);
				
				//creamos la caravana
				
				int idCa = 12;
				String plateC = "2079 GHL";
				String typeOfCaravanC = "Fifth Wheeler";
				int pricePerDayC = 45;
				int numberOfSeatsC = 5;
				int weightC = 4325;
				String brandC = "Fendt";
				String modelC = "perfilada";
				
				
				cm.saveCaravan(plateC, typeOfCaravanC, pricePerDayC, numberOfSeatsC, weightC, brandC, modelC);
				Caravan caravan = cm.getCaravan(idCa);
				
				
				//creamos la reserva
				
				int id = 123;
				LocalDate ini = LocalDate.of(2023,9,26);
				LocalDate end = LocalDate.of(2023,9,28);
				String state = "ready";
				
				
				bm.saveBooking(id, ini, end, state, customer, caravan, cm);
				Booking b = bm.getBooking(id);
				
				
				//private BookingSerializer bs;
				BookingSerializer bs = new BookingSerializer();
				bs.Serialization(b);
				
				Booking b2 = bs.Deserialization();
				
				assertEquals(b.getId(), b2.getId());
				assertEquals(b.getIniDate(), b2.getIniDate());
				assertEquals( b.getEndDate(), b2.getEndDate());
				assertEquals(b.getState(), b2.getState());
				assertEquals(b.fromCustomerGetId(), b2.fromCustomerGetId());
				assertEquals(b.fromCaravanGetId(), b2.fromCaravanGetId());
		
	}*/
}