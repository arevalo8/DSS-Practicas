package es.uca.dss.UCAravana.cli;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import trabajo.Booking;
import trabajo.BookingManager;
import trabajo.Caravan;
import trabajo.CaravanManager;
import trabajo.CaravanManager.CaravanNotFoundException;
import trabajo.Customer;
import trabajo.CustomerManager;

@ShellComponent
public class ManagingCommands {
	
	private CaravanManager cm; // = new CaravanManager(new CaravanSerializerDao());
	private CustomerManager customerManager;  //= new CustomerManager(new CustomerSerializerDao());
	private BookingManager bm; // = new BookingManager(new BookingDao());
	
	public ManagingCommands(CaravanManager cm, CustomerManager customerManager, BookingManager bm) {
		this.cm = cm;
		this.customerManager = customerManager;
		this.bm = bm;
	}
	


//-----------------------COMANDOS DE CARAVANAS-----------------------------------------------------------



	@ShellMethod(key = "getAllCaravans", value = "El metodo getAllCaravans devuelve todas las caravanas almacenadas en la base de datos")
	public String getAllCaravans() {
		 List<Caravan> c = cm.getAllCaravans();
		 return c.toString();
	}
	
	@ShellMethod(key = "saveCaravan", value = "El metodo saveCaravan guarda en la base de datos una caravana con los parámetros que se le han pasado")
	public void saveCaravan(String plate, String typeOfVehicle, int pricePerDay, int numberOfSeats, int weight, String brand, String model) {
		Caravan c = new Caravan(plate, typeOfVehicle, pricePerDay, numberOfSeats, weight, brand, model);
		cm.saveCaravan(c);
		//return "La caravana se ha almacenado con exito";
	}
	
	@ShellMethod(key = "getCaravan", value = "El metodo getCaravan devuelve la caravana con matricula plate de la base de datos")
	public Caravan getCaravan(String plate) {
	    try {
	        return cm.getCaravan(plate);
	    } catch (CaravanNotFoundException e) {
	        System.err.println("No se encontro ninguna caravana con matricula " + plate);
	        return null; 
	    }
	}

	
	@ShellMethod(key = "deleteCaravan", value = "El metodo deleteCaravan borra la caravana con matricula plate de la base de datos")
	public String deleteCaravan(String plate) {
		try {
	        cm.deleteCaravan(plate);
	    } catch (CaravanNotFoundException e) {
	        System.err.println("No se encontro ninguna caravana con matricula " + plate);
	        return null; 
	    }
		return "La caravana se ha borrado con exito";
	}
	
	@ShellMethod(key = "editCaravanPlate", value = "El metodo editCaravanPlate edita la matricula de la caravana con matricula plate a newPlate")
	public String editCaravanPlate(String plate, String newPlate) {
		try {
			cm.editCaravanPlate(plate, newPlate);
			return "La matricula ha cambiado de " + plate + " a " + newPlate;
	    } catch (CaravanNotFoundException e) {
	        System.err.println("No se encontro ninguna caravana con matricula " + plate);
	        return null; 
	    }
	}
	
	@ShellMethod(key = "editCaravanTypeOfVehicle", value = "El metodo editCaravanTypeOfVehicle modifica el tipo de vehiculo de la caravana con matricula plate")
	public String editCaravanTypeofVehicle(String plate, String typeOfVehicle) {
		try {
			cm.editCaravanTypeofVehicle(plate, typeOfVehicle);
			return "El tipo de la caravana se ha modificado con exito";
	    } catch (CaravanNotFoundException e) {
	        System.err.println("No se encontro ninguna caravana con matricula " + plate);
	        return null; 
	    }
	}
	
	@ShellMethod(key = "editCaravanPricePerDay", value = "El metodo editCaravanPricePerDay modifica el precio diario de alquilar la caravana con matricula plate")
	public String editCaravanPricePerDay(String plate, int pricePerDay) {
		
		try {
			cm.editCaravanPricePerDay(plate, pricePerDay);
			return "El precio de la caravana se ha modificado con exito";
	    } catch (CaravanNotFoundException e) {
	        System.err.println("No se encontro ninguna caravana con matricula " + plate);
	        return null; 
	    }
	}
	
	@ShellMethod(key = "editCaravanWeight", value = "El metodo editCaravanWeight modifica el peso de la caravana con matricula plate")
	public String editCaravanWeight(String plate, int weight) {
		try {
			cm.editCaravanWeight(plate, weight);
			return "El peso de la caravana se ha modificado con exito";
	    } catch (CaravanNotFoundException e) {
	        System.err.println("No se encontro ninguna caravana con matricula " + plate);
	        return null; 
	    }
	}
	
	@ShellMethod(key = "editCaravanBrand", value = "El metodo editCaravanBrand modifica la marca de la caravana con matricula plate")
	public String editCaravanWeight(String plate, String brand) {
		try {
			cm.editCaravanBrand(plate, brand);
			return "La marca de la caravana se ha modificado con exito";
	    } catch (CaravanNotFoundException e) {
	        System.err.println("No se encontro ninguna caravana con matricula " + plate);
	        return null; 
	    }
	}
	
	//public void editCaravanModel(String plate, String model)
	@ShellMethod(key = "editCaravanModel", value = "El metodo editCaravanModel modifica el modelo de la caravana con matricula plate")
	public String editCaravanModel(String plate, String model) {
		try {
			cm.editCaravanBrand(plate, model);
			return "La marca de la caravana se ha modificado con exito";
	    } catch (CaravanNotFoundException e) {
	        System.err.println("No se encontro ninguna caravana con matricula " + plate);
	        return null; 
	    }
	}







//-----------------------COMANDOS DE CLIENTES-----------------------------------------------------------


	@ShellMethod(key = "getAllCustomers", value = "El metodo getAllCustomers devuelve todos los clientes almacenadas en la base de datos")
	public String getAllCustomers() {
		return customerManager.getAllCustomers().toString();
	}
	
	@ShellMethod(key = "saveCustomer", value = "El metodo saveCustomer guarda en la base de datos un cliente con los parámetros que se le han pasado")
	public void saveCustomer(String name, String firstSurname, String secondSurname, String dni, String phone,
			String email, String drivingLicenseType) {
		Customer c = new Customer(name, firstSurname, secondSurname, dni, phone, email, drivingLicenseType);
		customerManager.saveCustomer(c);
		//return "El Cliente se ha almacenado con exito";
	}
	
	@ShellMethod(key = "getCustomer", value = "El metodo getCustomer devuelve el cliente con dni de la base de datos")
	public Customer getCustomer(String dni) {
		return customerManager.getCustomer(dni);
	}
	
	@ShellMethod(key = "deleteCustomer", value = "El metodo deleteCustomer borra el cliente con identificador id de la base de datos")
	public String deleteCustomer(String dni) {
		customerManager.deleteCustomer(dni);
		return "El Cliente se ha borrado con exito";
	}
	
	@ShellMethod(key = "editCustomerName", value = "El metodo editCustomerName modifica el nombre del cliente con ese dni")
	public String editCustomerName(String dni, String name) {
		customerManager.editCustomerName(dni, name);
		return "El nombre del cliente se ha modificado con exito";
	}
	
	@ShellMethod(key = "editCustomerFirstSurname", value = "El metodo editCustomerFirstSurname modifica el primer apellido del cliente con ese dni")
	public String editCustomerFirstSurname(String dni, String firstSurname) {
		customerManager.editCustomerFirstSurname(dni, firstSurname);
		return "El primer apellido del cliente se ha modificado con exito";
	}
	
	@ShellMethod(key = "editCustomerSecondSurname", value = "El metodo editCustomerSecondSurname modifica el primer apellido del cliente con ese dni")
	public String editCustomerSecondSurname(String dni, String secondSurname) {
		customerManager.editCustomerSecondSurname(dni, secondSurname);
		return "El segundo apellido del cliente se ha modificado con exito";
	}
	
	@ShellMethod(key = "editCustomerdni", value = "El metodo editCustomerdni modifica el dni del cliente")
	public String editCustomerdni(String dni, String newDni) {
		customerManager.editCustomerdni(dni, newDni);
		return "El dni del cliente se ha modificado con exito";
	}
	
	@ShellMethod(key = "editCustomerPhoneNumber", value = "El metodo editCustomerPhoneNumber modifica el numero de telefono del cliente con ese dni")
	public String editCustomerPhoneNumber(String dni, String phoneNumber) {
		customerManager.editCustomerPhoneNumber(dni, phoneNumber);
		return "El numero de telefono se ha modificado con exito";
	}
	
	@ShellMethod(key = "editCustomerEmail", value = "El metodo editCustomerEmail modifica el email al cliente con ese dni")
	public String editCustomerEmail(String dni, String email) {
		customerManager.editCustomerEmail(dni, email);
		return "El email se ha modficado con exito";
	}
	
	@ShellMethod(key = "editCustomerDrivingLicenseType", value = "El metodo editCustomerDrivingLicenseType modifica el permiso de cunducir al cliente con ese dni")
	public String editCustomerDrivingLicenseType(String dni, String drivingLicenseType) {
		customerManager.editCustomerDrivingLicenseType(dni, drivingLicenseType);
		return "El permiso de conducir se ha modficado con exito";
	}
	
	
	
	
	
	
	
//-----------------------COMANDOS DE RESERVAS-----------------------------------------------------------
	
	
	@ShellMethod(key = "getAllBookings", value = "El metodo getAllBookings devuelve todas las reservas almacenadas en la base de datos")
	public String getAllBookings() {
		return bm.getAllBookings().toString();
	}
	
	@ShellMethod(key = "saveBooking", value = "El metodo saveCaravan guarda en la base de datos una reserva con los parámetros que se le han pasado")
	public String saveBooking(String iniDate, String endDate, String dniCustomer, String plateCaravan) {
		LocalDate ini = LocalDate.parse(iniDate);
		LocalDate end = LocalDate.parse(endDate);
		Customer customer = customerManager.getCustomer(dniCustomer);
		Caravan caravan = cm.getCaravan(plateCaravan);
		Booking b = new Booking(ini, end, customer, caravan); //FALTA PASAR DE STRING A LOCALDATE
		bm.saveBooking(b, cm);
		return "La reserva se ha almacenado con exito";
	}	
	
	@ShellMethod(key = "getBooking", value = "El metodo getBooking devuelve la reserva con identificador id de la base de datos")
	public Booking getBooking(UUID id) {
		return bm.getBooking(id);
	}
	
	@ShellMethod(key = "cancel", value = "El metodo deleteBooking borra la reserva con identificador id de la base de datos")
	public void cancel(UUID id) {
		bm.cancel(id);
		//return "La reserva se ha borrado con exito";
	}
	
	@ShellMethod(key = "CheckIn", value = "El metodo CheckIn realiza el check in cuando se va a recoger una caravana alquilada")
	public void CheckIn(UUID idBooking) {
		//Booking b = bm.getBooking(idBooking);
		bm.checkIn(idBooking);
		//return "Se ha realizado el Check-In con exito";
	}
	

	
	@ShellMethod(key = "CheckOut", value = "El metodo CheckOut realiza el check out cuando se devuelve una caravana alquilada")
	public void CheckOut(UUID idBooking) {
		//Booking b = bm.getBooking(idBooking);
		bm.CheckOut(idBooking);
		//return "Se ha realizado el Check-Out con exito";
	}
	
	@ShellMethod(key = "availableByDate", value = "El metodo CheckOut realiza el check out cuando se devuelve una caravana alquilada")
	public void availableByDate(String ini, String end) {
		LocalDate iniDate = LocalDate.parse(ini);
		LocalDate endDate = LocalDate.parse(end);
		List<Caravan> caravans = bm.availableByDate(iniDate, endDate, cm);
		for(Caravan c : caravans) {
			System.out.println(c.toString());
		}
	}
	
	@ShellMethod(key = "payAmount", value = "El metodo payAmount realiza el pago de una cantidad amount de la reserva con identificador id")
	public void payAmount(UUID id, int amount) {
		bm.payAmount(id, amount);
	}

	@ShellMethod(key = "pendingPay", value = "El metodo pendingPay muestra la cantidad pendiente de pago de la reserva con identificador id")
	public void payAmount(UUID id) {
		bm.pendingPay(id);
	}

	@ShellMethod(key = "totalBookings", value = "El metodo totalBookings muestra el numero total de reservas realizadas")
	public int totalBookings() {
		return bm.totalBookings();
	}
	 
	@ShellMethod(key = "averageDaysPerBooking", value = "El metodo averageDaysPerBooking muestra el numero medio de dias por reserva")
	public double averageDaysPerBooking() {
		return bm.averageDaysPerBooking();
	}
	
	@ShellMethod(key = "totalIncomes", value = "El metodo totalIncomes muestra los ingresos totales por las reservas")
	public int totalIncomes() {
		return bm.totalIncomes(cm);
	}
}	
