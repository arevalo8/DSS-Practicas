package testIniciales;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class MyTestRunner {
	public static void main(String[] args) {
			Result result = JUnitCore.runClasses(CaravanTest.class, BookingTest.class, CustomerTest.class, CheckInTest.class,
					manageAvailabilityTest.class, CancelTest.class, SaveBookingTest.class, CheckInTest.class, CheckInTest.class,
					CheckOutTest.class, CaravanManagerTest.class, CustomerManagerTest.class,
					GetCustomerByDniTest.class, GetCaravanByPlateTest.class, BookingManagerTest.class);
			for (Failure failure : result.getFailures()) {
				System.out.println(failure.toString());
			}
			
			System.out.println(result.wasSuccessful());
	}
}
