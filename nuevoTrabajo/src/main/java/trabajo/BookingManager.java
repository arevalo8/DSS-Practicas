package trabajo;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mailSender.EmailSender;


/**
 * @author Dar�o Salas Arellano
 *
 */
@Service
public class BookingManager {

	private Dao<Booking> daoBookings;

	
	private EmailSender emailSender; 


	//Los clientes que entreguen una caravana tendrán una penalización de 150€ por cada día retraso
	static final int PRECIO_RETRASO = 150;
	

	//Constructor
	@Autowired
	public BookingManager(Dao<Booking> daoBookings, EmailSender emailSender) {
		super();
		this.daoBookings = daoBookings; 	//Se le asigna a DaoBookings el tipo de sistema de persistencia espec�fico
		this.emailSender = emailSender; // Inyecta el EmailSender
	}

	public List<Booking> getAllBookings() {
		return daoBookings.getAll();
	}


	public void saveBooking(Booking b, CaravanManager cm) {
		//Hay que comprobar que la Booking no tiene un id que ya existe en la base de datos, o mejor ponerlo como precondici�n
		//el último parámetro de checkcaravanPicked debería ser Manager o Dao ? (Creo que Manager)
		boolean caravanFree = false;
		boolean validDate = checkDate(b.getIniDate(), b.getEndDate());
		//si la fecha es correcta comprobamos que la caravana este libre
		if(validDate) {
			caravanFree = checkCaravanPicked(b.getIniDate(), b.getEndDate(), b.getCaravan(), cm);
		}
		boolean politicaReserva = checkPolitic(b.getIniDate());
		//boolean 
		if(validDate && caravanFree && politicaReserva) {
			b.setState("Booked - Unpaid");
			daoBookings.save(b);				//Aqu� se recurre a los m�todos de la interfaz para guardar la Booking


			String msgBody = (b.getCustomer().getName() + ", su reserva de la caravana " + b.getCaravan().getModel() + " marca " + b.getCaravan().getBrand()
					+ " con matrícula " + b.getCaravan().getPlate() + " entre las fechas " + b.getIniDate() + " y " +
					b.getEndDate() + " ha sido procesada con éxito");


			emailSender.sendMail(b.getCustomer().getEmail(), "Booking done", msgBody);  //Enviamos email de confirmación de la reserva al cliente
		}
	}

	public void saveBooking(LocalDate iniDate, LocalDate endDate, Customer customer, Caravan caravan, CaravanManager cm) {
		//Hay que comprobar que la Booking no tiene un id que ya existe en la base de datos, o mejor ponerlo como precondici�n
		//el último parámetro de checkcaravanPicked debería ser Manager o Dao ? (Creo que Manager)
		boolean caravanFree = false;
		boolean validDate = checkDate(iniDate, endDate);
		//si la fecha es correcta comprobamos que la caravana este libre
		if(validDate) {
			caravanFree = checkCaravanPicked(iniDate, endDate, caravan, cm);
		}
		boolean politicaReserva = checkPolitic(iniDate);
		//boolean 
		if(validDate &&/* caravanFree &&*/ politicaReserva) {
			Booking b = new Booking(iniDate, endDate, customer, caravan);

			daoBookings.save(b);;				//Aqu� se recurre a los m�todos de la interfaz para guardar la Booking


				String msgBody = (b.getCustomer().getName() + ", su reserva de la caravana " + b.getCaravan().getModel() + " marca " + b.getCaravan().getBrand()
						+ " con matrícula " + b.getCaravan().getPlate() + " entre las fechas " + b.getIniDate() + " y " +
						b.getEndDate() + " ha sido procesada con éxito");


			emailSender.sendMail(b.getCustomer().getEmail(), "Book", msgBody);  //Enviamos email de confirmación de la reserva al cliente
		}
	}


	//Devuelve todas las caravanas disponibles en una fecha determinada
	public List<Caravan> availableByDate(LocalDate ini, LocalDate end, CaravanManager cm) {
		manageAvailability ma = new manageAvailability();
		List<Caravan> LC = ma.viewAvailability(ini, end, cm, this);

		return LC;
	}	


	/*public void saveCaravan(int id, String plate, String typeOfVehicle, int pricePerDay, int numberOfSeats, int weight, String brand, String model) {
		//Hay que comprobar que la Caravan no tiene un id que ya existe en la base de datos, o mejor ponerlo como precondici�n
		boolean isUnique = CaravanUnique(id);
		//comprobamos que sea única
		if(isUnique) {

			//creamos la caravana
			Caravan c = new Caravan(id, plate, typeOfVehicle, pricePerDay, numberOfSeats, weight, brand, model);
			//guardamos la caravana
			daoCaravans.save(c);				//Aqu� se recurre a los m�todos de la interfaz para guardar la Caravan
		}
	}*/

	public void checkIn(UUID id) {
		Booking b = this.getBooking(id);
		if(b.getPendingPay() == 0) {
			this.editBookingState(id, "CheckIn Performed");
		} else {
			System.out.println("No se puede realizar el Check-In\n"
					+ "Cantidad pendiente de pago: " + b.getPendingPay() );
		}

	}
	

	//permite pagar una determinada cantidad del alquiler de la caravana
	public void payAmount(UUID id, int amount) {
		Booking b = this.getBooking(id);

		int pendingPay = b.getPendingPay();
		int stillPending = pendingPay - amount;

		//Si intentamos pagar más de lo que queda 
		if(amount > pendingPay) {
			System.out.println("Error. Está intentando abonar una cantidad mayor a la pendiente de pago."
					+ "Pendiente de pago: " + pendingPay);
		} else {

			//En caso de estar completamente pagada actualizamos el estado de la reserva
			if (stillPending == 0) {
				System.out.println("El precio del alquiler de la caravana ha sido abonado al completo");
				//b.setState("Booked - Paid");
				this.editBookingState(id, "Booked - Paid");
			} else {  //En caso contrario solo actualizamos la cantidad pendiente de pagar
				System.out.println("Se ha abonado un total de " + amount + "\n"
						+ "Queda por pagar un total de " + stillPending );
			}

			//b.setPendingPay(stillPending);
			this.editBookingPendingPay(id, stillPending);

		}
	}


	//realizar checkout con fecha de hoy
	public void CheckOut(UUID id) {
		Booking b = this.getBooking(id);

		int delayDays = (int) DAYS.between(b.getEndDate(), LocalDate.now());
		int priceDelay = (int) (delayDays * PRECIO_RETRASO);


		if(priceDelay > 0) {
			System.out.println("Ha habido un retraso de " + delayDays + " en la devolución de la caravana\n"
					+ "Este conlleva un coste de " + priceDelay + "\n"
					+ "Debe abonarlo ahora mismo.");
			//b.setDelayPrice(priceDelay);

		} 
		
		this.editBookingState(id, "CheckOut performed");

		System.out.println("Check-Out realizado con éxito");	
	}

	//realizar el checkout para una echa distinta a la actual
	public void CheckOut(UUID id, LocalDate check) {
		Booking b = this.getBooking(id);

		int delayDays = (int) DAYS.between(b.getEndDate(), check);
		int priceDelay = (int) (delayDays * PRECIO_RETRASO);


		if(priceDelay > 0) {
			System.out.println("Ha habido un retraso de " + delayDays + " en la devolución de la caravana\n"
					+ "Este conlleva un coste de " + priceDelay);
			//b.setDelayPrice(priceDelay);
		} 
		
		this.editBookingState(id, "CheckOut performed");
		
		System.out.println("Check-Out realizado con éxito");	
	}


	//cancelar reserva
	public void cancel(UUID id) {
		Booking b = this.getBooking(id);

		//if(b.getId() == -1) {
		//Tirar excepción (debería hacerlo la getRawBooking())
		//}

		long days = DAYS.between(LocalDate.now(), b.getIniDate());

		//comprobamos que la cancelación se ha reaalizado con un mínimo de 2 días de antelación
		if(days >= 2) {

			//this.deleteBooking(b.getId());
			editBookingState(b.getId(), "Cancelled");
			System.out.println("La reserva ha sido cancelada con éxito");


			String msgBody = (b.getCustomer().getName() + " su reserva de la caravana " + b.getCaravan().getModel() + " marca " + b.getCaravan().getBrand()
					+ " con matrícula " + b.getCaravan().getPlate() + " entre las fechas " + b.getIniDate() + " y " +
					b.getEndDate() + " ha sido cancelada con éxito");


			emailSender.sendMail(b.getCustomer().getEmail(), "Cancel", msgBody);  //Enviamos email de confirmación de la reserva al cliente

		} else {
			//System.out.println("Error. La cancelacion de la reserva debe realizarse con un minimo de "
			//		+ "2 dias de antelacion");
			throw new FailPolitics("Error. La cancelacion de la reserva debe realizarse con un minimo de 2 dias de antelacion");
		}
	}
	
	public int pendingPay(UUID id) {
		Booking b = this.getBooking(id);
		int total = b.getPendingPay();
		if(total > 0) {
			System.out.println("Queda por pagar un total de " + total + " €");
		} else {
			System.out.println("La reserva ha sido paga al completo");
		}
		return total;
	}

	public void editBookingState(UUID id, String state) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = String.valueOf(state);

		daoBookings.update(this.getRawBooking(id), "state",  parametros);
	}

	public void editBookingPendingPay(UUID id, int pendingPay) {
		//Se crea el String[] de datos a cambiar
		String[] parametros = new String[1];
		parametros[0] = String.valueOf(pendingPay);

		daoBookings.update(this.getRawBooking(id), "pendingPay",  parametros);
	}

	public void updateBooking(Booking b, UUID id) {
		//Se crea el String[] con el id de la reserva que se va a modificar
		String[] parametros = new String[1];
		parametros[0] = String.valueOf(id);

		daoBookings.update(b, "old",  parametros);
	}

	public Booking getBooking(UUID id) {

		//Obtenemos la Booking con el m�todo privado getBooking()
		Booking bookingToView = this.getRawBooking(id);

		//Copiamos la Booking obtenida en una nueva, para no devolver la real
		Booking bookingCopy = new Booking(bookingToView.getId(),
				bookingToView.getIniDate(),
				bookingToView.getEndDate(),
				bookingToView.getState(),
				bookingToView.getCustomer(),
				bookingToView.getCaravan(),
				bookingToView.getPendingPay()
				);
		return bookingCopy;

	}

	public void deleteBooking(UUID id) {
		daoBookings.delete(this.getRawBooking(id));
	}
	
	public int totalBookings() {
		List<Booking> bookings = getAllBookings();
		return bookings.size();
	}
	
	public double averageDaysPerBooking() {
		List<Booking> bookings = getAllBookings();
		double total = 0;
		for(Booking b : bookings) {
			 //long diasDiferencia= ChronoUnit.DAYS.between(b.getIniDate(), b.getEndDate());
			 double diasDiferencia = (int) DAYS.between(b.getIniDate(), b.getEndDate());
			 total = total + diasDiferencia;
		}
		double average = total / totalBookings();
		
		return average;
	}
	
	public int totalIncomes(CaravanManager cm) {
		List<Booking> bookings = getAllBookings();
		int total = 0;
		for(Booking b : bookings) {
			 int diasDiferencia = (int) ChronoUnit.DAYS.between(b.getIniDate(), b.getEndDate());
			 //coste total de alquilar la caravana - lo que falta por pagar = ingresos
			 int paid = diasDiferencia * cm.getCaravan(b.fromCaravanGetId()).getPricePerDay() - b.getPendingPay();
			 total = total + paid;
		}
		
		return total;
	}

	private Booking getRawBooking(UUID id) {

		Optional<Booking> resultedBooking= daoBookings.get(id);

		if(resultedBooking.isEmpty()) {
			
			//Caravan caravan = new Caravan(null, "", "", 0, 0, 0, "", "");
			
			//return new Booking(null, LocalDate.of(1900,01,01), LocalDate.of(1900, 01,01), "", null, caravan, 0);
			throw new BookingNotFoundException("No se encontró ninguna reserva con id: " + id);
		}

		return resultedBooking.get();
	}

	//comprueba que las fechas de la reserva sean correctas
	private boolean checkDate(LocalDate iniDate, LocalDate endDate) {

		boolean correctDate = true;

		if(iniDate.isAfter(endDate) || iniDate.isBefore(LocalDate.now())) {
			correctDate = false;
			//System.out.println("La fecha seleccionada es incorrecta");
			throw new InvalidDate("Error. Las fechas introducidas son incorrectas.");
		} 


		return correctDate;
	}

	//comprueba que la caravana seleccionada no esté reservada para las fechas elegidas
	private boolean checkCaravanPicked(LocalDate iniDate, LocalDate endDate, Caravan caravan, CaravanManager cm) {

		manageAvailability ma = new manageAvailability();

		//obtenemos las caravanas disponibles para nuestras fechas
		List<Caravan> checkCaravan = ma.viewAvailability(iniDate, endDate, cm, this);

		//comprobamos si la caravana que queremos está disponible en esa fecha
		boolean caravanIsFree = false;

		for(Caravan c : checkCaravan) {
			if(c.equals(caravan)) {
				caravanIsFree = true;
			}
		}

		if(!caravanIsFree) {
			System.out.println("La caravana que desea ya está reservada para esa fecha");
		}

		return  caravanIsFree;
	}

	//Comprueba que se cumpla la política de reservas:
	//Las caravanas se deben de reservar con un mínimo de 2 días de antelación
	private boolean checkPolitic(LocalDate ini) {
		long dias = DAYS.between(LocalDate.now(), ini);
		if(dias <= 2) {
			//System.out.println("Lo siento, la reserva debe realizarse con un mínimo de dos días de antelación");
			throw new FailPolitics("Error. Las reservas deben realizarse con un minimo de dos dias de antelacion");
			//return false;
		} else {
			return true;
		}
	}
	
	public class BookingNotFoundException extends RuntimeException {
		public BookingNotFoundException(String message) {
			super(message);
		}
	}	
	
	public class InvalidDate extends RuntimeException {
		public InvalidDate(String message) {
			super(message);
		}
	}	
	
	public class FailPolitics extends RuntimeException {
		public FailPolitics(String message) {
			super(message);
		}
	}	
	
	



}




