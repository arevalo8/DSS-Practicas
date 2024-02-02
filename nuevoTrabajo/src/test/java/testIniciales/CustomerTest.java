package testIniciales;

import org.junit.Test;

import junit.framework.TestCase;
import trabajo.Customer;

public class CustomerTest extends TestCase {
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(CustomerTest.class);
	}
	@Test
	public void testCustomer() {
		String name = "Paco";
		String firstSurname = "Pérez";
		String secondSurname = "García";
		String dni = "54879545j";
		String phone = "652847952";
		String email = "paco.perez@gmail.com";
		String drivingLicenseType = "B";
		
		Customer c = new Customer(name, firstSurname, secondSurname, dni, phone, email, drivingLicenseType);
		
		
		assertEquals(name, c.getName());
		assertEquals(firstSurname, c.getFirstSurname());
		assertEquals(secondSurname, c.getSecondSurname());
		assertEquals(dni, c.getDni());
		assertEquals(phone, c.getPhone());
		assertEquals(email, c.getEmail());
		assertEquals(drivingLicenseType, c.getDrivingLicenseType());
	}
}
