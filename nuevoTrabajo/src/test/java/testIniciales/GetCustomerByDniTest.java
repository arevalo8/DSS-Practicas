package testIniciales;

import org.junit.Test;

import junit.framework.TestCase;
import trabajo.Customer;
import trabajo.CustomerDao;
import trabajo.CustomerManager;

public class GetCustomerByDniTest extends TestCase {
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(CustomerTest.class);
	}
	@Test
	public void testGetCustomerByDni() {
		String name = "Paco";
		String firstSurname = "Pérez";
		String secondSurname = "García";
		String dni = "54879545j";
		String phone = "652847952";
		String email = "paco.perez@gmail.com";
		String drivingLicenseType = "B";
		
		CustomerDao cd = new CustomerDao();
		CustomerManager cm = new CustomerManager(cd);
		
		Customer customer = new Customer(name, firstSurname, secondSurname, dni, phone, email, drivingLicenseType);
		cm.saveCustomer(customer);
		Customer c = cm.getCustomer(dni);
		
		
		assertEquals(customer.getName(), c.getName());
		assertEquals(customer.getFirstSurname(), c.getFirstSurname());
		assertEquals(customer.getSecondSurname(), c.getSecondSurname());
		assertEquals(customer.getDni(), c.getDni());
		assertEquals(customer.getPhone(), c.getPhone());
		assertEquals(customer.getEmail(), c.getEmail());
		assertEquals(customer.getDrivingLicenseType(), c.getDrivingLicenseType());
	}
}