package com.example.Serialization;

import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;
import trabajo.Caravan;

public class CaravanSerializerTest extends TestCase {
	
	/*public static void main(String args[]) {
		junit.textui.TestRunner.run(CustomerSerializerTest.class);
	}*/

	/*@Test
	public void testCaravanSerializer() throws IOException, ClassNotFoundException {

		// creamos  el cliente
		
		int idC = 12;
		String plateC = "2079 GHL";
		String typeOfCaravanC = "Fifth Wheeler";
		int pricePerDayC = 45;
		int numberOfSeatsC = 5;
		int weightC = 4325;
		String brandC = "Fendt";
		String modelC = "perfilada";
		
		Caravan c = new Caravan(plateC, typeOfCaravanC, pricePerDayC, numberOfSeatsC, weightC, brandC, modelC);
		
		
				
				
		CaravanSerializer cs = new CaravanSerializer();
		cs.Serialization(c);
				
		Caravan c2 = cs.Deserialization();
				
		System.out.println(c.toString());
		System.out.println(c2.toString());
		
		assertEquals(c.getId(), c2.getId());
		assertEquals(c.getPlate(), c2.getPlate());
		assertEquals(c.getTypeOfVehicle(), c2.getTypeOfVehicle());
		assertEquals(c.getPricePerDay(), c2.getPricePerDay());
		assertEquals(c.getNumberOfSeats(), c2.getNumberOfSeats());
		assertEquals(c.getWeight(), c2.getWeight());
		assertEquals(c.getBrand(), c2.getBrand());
		assertEquals(c.getModel(), c2.getModel());
		
	}*/
}


