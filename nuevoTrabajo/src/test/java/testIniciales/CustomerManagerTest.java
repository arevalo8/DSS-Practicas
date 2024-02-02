package testIniciales;

import java.util.UUID;

import org.junit.Test;

import junit.framework.TestCase;
import trabajo.Customer;
import trabajo.CustomerDao;
import trabajo.CustomerManager;

public class CustomerManagerTest extends TestCase {
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(CustomerManagerTest.class);
	}
	
	@Test
	public void testCustomerManager() {
		CustomerDao cd = new CustomerDao();
		CustomerManager cm = new CustomerManager(cd);
		
		String name = "Paco";
		String firstSurname = "Pérez";
		String secondSurname = "García";
		String dni = "54879545j";
		String phone = "652847952";
		String email = "paco.perez@gmail.com";
		String drivingLicenseType = "B";
		
		Customer customer = new Customer(name, firstSurname, secondSurname, dni, phone, email, drivingLicenseType);
		UUID id = customer.getId();
		cm.saveCustomer(customer);
		Customer c = cm.getCustomer(id);
		
		
		assertEquals(id, c.getId());
		assertEquals(name, c.getName());
		assertEquals(firstSurname, c.getFirstSurname());
		assertEquals(secondSurname, c.getSecondSurname());
		assertEquals(dni, c.getDni());
		assertEquals(phone, c.getPhone());
		assertEquals(email, c.getEmail());
		assertEquals(drivingLicenseType, c.getDrivingLicenseType());
	}
}