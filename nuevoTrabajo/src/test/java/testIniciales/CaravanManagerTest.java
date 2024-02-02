package testIniciales;

import java.util.UUID;

import org.junit.Test;

import junit.framework.TestCase;
import trabajo.Caravan;
import trabajo.CaravanDao;
import trabajo.CaravanManager;


public class CaravanManagerTest extends TestCase {

	public static void main(String args[]) {
		junit.textui.TestRunner.run(CaravanManagerTest.class);
	}
	

	@Test
	public void testManagerCaravan() {
		//int idC = 12;
		String plateC = "2079 GHL";
		String typeOfCaravanC = "Fifth Wheeler";
		int pricePerDayC = 45;
		int numberOfSeatsC = 5;
		int weightC = 2500;
		String brandC = "Fendt";
		String modelC = "perfilada";
		
		
		CaravanDao cd = new CaravanDao();
		CaravanManager cm = new CaravanManager(cd);
		
		
		
		Caravan caravan = new Caravan(plateC, typeOfCaravanC, pricePerDayC, numberOfSeatsC, weightC, brandC, modelC);
		UUID idC = caravan.getId();
		cm.saveCaravan(caravan);
		Caravan c = cm.getCaravan(plateC);
		
		//Comprobamos que se haya guardado correctamente
		assertEquals(idC, c.getId());
		assertEquals(plateC, c.getPlate());
		assertEquals(typeOfCaravanC, c.getTypeOfVehicle());
		assertEquals(pricePerDayC, c.getPricePerDay());
		assertEquals(numberOfSeatsC, c.getNumberOfSeats());
		assertEquals(weightC, c.getWeight());
		assertEquals(brandC, c.getBrand());
		assertEquals(modelC, c.getModel());
		
		System.out.println(cm.getAllCaravans().toString());
		
		//Actualizamos la caravana
		
		
		String modelC2 = "integral";
		cm.editCaravanModel(plateC, modelC2);
		
		String brandC2 = "buccaneer";
		cm.editCaravanBrand(plateC, brandC2);
		
		int weightC2 = 2300;
		cm.editCaravanWeight(plateC, weightC2);
		
		int pricePerDayC2 = 50;
		cm.editCaravanPricePerDay(plateC, pricePerDayC2);
		
		String typeOfCaravanC2 = "Camper Trailer";
		cm.editCaravanTypeofVehicle(plateC, typeOfCaravanC2);
		
		String plateC2 = "9856 THS";
		cm.editCaravanPlate(plateC, plateC2);
		
		Caravan c2 = cm.getCaravan(idC);
		System.out.println(cm.getCaravan(plateC2));
		
		//Comprobamos que se haya guardado correctamente
		assertEquals(idC, c2.getId());
		assertEquals(plateC2, c2.getPlate());
		assertEquals(typeOfCaravanC2, c2.getTypeOfVehicle());
		assertEquals(pricePerDayC2, c2.getPricePerDay());
		assertEquals(numberOfSeatsC, c2.getNumberOfSeats());
		assertEquals(weightC2, c2.getWeight());
		assertEquals(brandC2, c2.getBrand());
		assertEquals(modelC2, c2.getModel());
		
		System.out.println(cm.getAllCaravans().toString());
		
		//Volvemos a actualizar a los valores iniciales
		cm.updateCaravan(c, idC);
	
	}
}