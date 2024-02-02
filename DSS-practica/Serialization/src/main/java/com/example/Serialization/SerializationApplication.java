package com.example.Serialization;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;

import trabajo.Caravan;
import trabajo.CaravanManager;
import trabajo.Customer;
import trabajo.CustomerManager;

//@SpringBootApplication
public class SerializationApplication {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SpringApplication.run(SerializationApplication.class, args);
	
	
			 

		int id = 7;
		String name = "Paco";
		String firstSurname = "Pérez";
		String secondSurname = "García";
		String dni = "54879545j";
		String phone = "652847952";
		String email = "paco.perez@gmail.com";
		String drivingLicenseType = "B";
		
		Customer customer = new Customer(name, firstSurname, secondSurname, dni, phone, email, drivingLicenseType);
		
		/*CustomerSerializer cs = new CustomerSerializer();
		cs.Serialization(c);
		Customer c2 = cs.Deserialization();*/
		//System.out.println(c2.toString());
		/*
		Caravan caravan = new Caravan(2, "2952 FLD", "Perfilada", 28, 6, 7200, "Mercedes", "Grande");
		CaravanSerializer carS = new CaravanSerializer();
		carS.caravanSerialization(caravan);
		Caravan car2 = carS.caravanDeserialization();
		System.out.println(car2.toString());*/
		Caravan caravan = new Caravan("2045 LGF", "Perfilada", 28, 6, 7200, "Mercedes", "Grande");
		
		CaravanSerializerDao cd = new CaravanSerializerDao();
		CaravanManager cm = new CaravanManager(cd);
		
		//CaravanSerializerDao cd2 = new CaravanSerializerDao();
		//CaravanManager cm2 = new CaravanManager(cd2);
		
		//cm.saveCaravan(caravan);
		
		CustomerSerializerDao csd = new CustomerSerializerDao();
		CustomerManager csm = new CustomerManager(csd);
		
		//csd.SerializationObject(customer);
		
		csm.saveCustomer(customer);
		Customer c2 = csm.getCustomer(customer.getDni());
		
		System.out.println("Hola " + c2.toString());
		
		//Caravan c= cm.getCaravan(caravan.getId());
		//System.out.println("hola" + c.toString());
		//System.out.println("TAMANO:  " + caravans.size());
		/*for(Caravan c : caravans) {
			System.out.println("Hola");
			System.out.println(c.toString());
		}*/
		
		
		//Booking b = new Booking(LocalDate.of(2024, 03, 15), LocalDate.of(2024, 03, 19), customer, caravan);
		//Booking b2 = new Booking(LocalDate.of(2024, 04, 10), LocalDate.of(2024, 04, 19), customer, caravan);
		
		/*List<Booking> bookings = new ArrayList<Booking>();
		bookings.add(b);
		bookings.add(b2);
		BookingSerializer bs = new BookingSerializer();
		bs.Serialization(bookings);
		List<Booking> books = bs.DeserializationList();*/
		
		
		
				
	}

}
