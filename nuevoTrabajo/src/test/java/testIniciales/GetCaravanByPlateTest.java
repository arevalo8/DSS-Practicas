package testIniciales;

import org.junit.Test;

import junit.framework.TestCase;
import trabajo.Caravan;
import trabajo.CaravanDao;
import trabajo.CaravanManager;

public class GetCaravanByPlateTest extends TestCase {

	public static void main(String args[]) {
		junit.textui.TestRunner.run(CaravanTest.class);
	}
	

	@Test
	public void testCaravan() {
		String plateC = "2079 GHL";
		String typeOfCaravanC = "Fifth Wheeler";
		int pricePerDayC = 45;
		int numberOfSeatsC = 5;
		int weightC = 4325;
		String brandC = "Fendt";
		String modelC = "perfilada";
		
		CaravanDao cd = new CaravanDao();
		CaravanManager cm = new CaravanManager(cd);
		
		Caravan caravan = new Caravan(plateC, typeOfCaravanC, pricePerDayC, numberOfSeatsC, weightC, brandC, modelC);
		cm.saveCaravan(caravan);
		Caravan c = cm.getCaravan(plateC);
		
		assertEquals(caravan.getPlate(), c.getPlate());
		assertEquals(caravan.getTypeOfVehicle(), c.getTypeOfVehicle());
		assertEquals(caravan.getPricePerDay(), c.getPricePerDay());
		assertEquals(caravan.getNumberOfSeats(), c.getNumberOfSeats());
		assertEquals(caravan.getWeight(), c.getWeight());
		assertEquals(caravan.getBrand(), c.getBrand());
		assertEquals(caravan.getModel(), c.getModel());
	}
}