package testIniciales;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import junit.framework.TestCase;
import trabajo.Caravan;


public class CaravanTest extends TestCase {

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
		
		Caravan c = new Caravan(plateC, typeOfCaravanC, pricePerDayC, numberOfSeatsC, weightC, brandC, modelC);

		
		assertEquals(plateC, c.getPlate());
		assertEquals(typeOfCaravanC, c.getTypeOfVehicle());
		assertEquals(pricePerDayC, c.getPricePerDay());
		assertEquals(numberOfSeatsC, c.getNumberOfSeats());
		assertEquals(weightC, c.getWeight());
		assertEquals(brandC, c.getBrand());
		assertEquals(modelC, c.getModel());
		
	}
}
