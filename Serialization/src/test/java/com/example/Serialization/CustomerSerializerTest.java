package com.example.Serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

import trabajo.Customer;
import trabajo.CustomerManager;

/*

public class CustomerSerializerTest {
	
	private CustomerSerializerDao cs;
	private Customer c;
	private Customer otroC;
	private Customer otroC2;
	private Customer otroC3;
	//private CustomerManager cm;
	private List<Customer> pruebaC;
	
	@Before
	public void setUp() throws Exception {
		cs = new CustomerSerializerDao();
		
		String phone = "652847952";
		ArrayList<String> ph = new ArrayList<String>();
		ph.add(phone);
		c = new Customer("Paco", "Pérez", "García", "54879545j", ph, "paco.perez@gmail.com", "B");
		
		String phone2 = "693524879";
		String phone3 = "698745285";
		ArrayList<String> ph2 = new ArrayList<String>();
		ph2.add(phone2);
		ph2.add(phone3);
		otroC = new Customer("Enrique", "Fernandez", "Rivas", "78451697g", ph2, "enrique.fernandez@gmail.com", "B96");
		otroC2 = new Customer( "Pedro", "Fernandez", "Rivas", "78451697g", ph2, "enrique.fernandez@gmail.com", "B96");
		otroC3 = new Customer("Juan", "Hernandez", "Rivas", "78451697g", ph2, "enrique.fernandez@gmail.com", "B96");
	
		
		pruebaC = new ArrayList<Customer>();
		pruebaC.add(c);
		pruebaC.add(otroC);
		pruebaC.add(otroC2);
		
	}*/

	/*@Test
	public void testCustomerSerializer()  {

		cs.SerializationObject(c);
		
		Customer c2 = cs.DeserializationObject();
		
		
		assertEquals(c.getId(), c2.getId());
		assertEquals(c.getName(), c2.getName());
		assertEquals(c.getFirstSurname(), c2.getFirstSurname());
		assertEquals(c.getSecondSurname(), c2.getSecondSurname());
		assertEquals(c.getDni(), c2.getDni());
		assertTrue(Objects.equals(c.getPhones(), c2.getPhones()));
		assertEquals(c.getEmail(), c2.getEmail());
		assertEquals(c.getDrivingLicenseType(), c2.getDrivingLicenseType());				

	}*/
	
	
	
	
	/*@Test 
	public void testListCustomerSerializer() {
		

		cs.SerializationObject(c);
		cs.SerializationObject(otroC);
		cs.SerializationObject(otroC2);
		cs.SerializationObject(otroC3);
		try {
			cs.SerializationList(pruebaC);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Customer> customers = cs.DeserializationList();
		
		for(Customer c : customers) {
			System.out.println("Hola " + c.toString());
		}
		
		//System.out.println(customers.toString());
		//assertTrue(pruebaC.get(0).equals(customers.get(2)));
		//assertTrue(pruebaC.get(1).equals(customers.get(1)));
		//assertTrue(pruebaC.get(2).equals(customers.get(0)));
	}*/
	
/*	@Test
	public void customerManagerTest() {
		CustomerManager cm = new CustomerManager(cs);
		
		cm.saveCustomer(c);
		Customer c2 = cm.getCustomer(c.getDni());
		
		assertEquals(c.getId(), c2.getId());
		assertEquals(c.getName(), c2.getName());
		assertEquals(c.getFirstSurname(), c2.getFirstSurname());
		assertEquals(c.getSecondSurname(), c2.getSecondSurname());
		assertEquals(c.getDni(), c2.getDni());
		assertTrue(Objects.equals(c.getPhones(), c2.getPhones()));
		assertEquals(c.getEmail(), c2.getEmail());
		assertEquals(c.getDrivingLicenseType(), c2.getDrivingLicenseType());
	}
	*/
	
	
	
	
	


