package es.uca.dss.UCAravana.apirest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import trabajo.Booking;
import trabajo.BookingManager;
import trabajo.Caravan;
import trabajo.CaravanManager;
import trabajo.Customer;



@RestController
public class BookingController {

	
	private BookingManager cm;
	//private EmailSender emailSender;
	
	@Autowired
	BookingController(/*BookingRepository repository, EmailSender emailSender,*/ BookingManager bm) {
			//Se crea el manager de Bookings con la base de datos JPA-style como sistema de persistencia
		    //this.cm  = new BookingManager(daoBooking, emailSender);
		this.cm = bm;
	}
	
	@GetMapping("/bookings")
	public CollectionModel<EntityModel<Booking>> getAllBookings() {
	    List<EntityModel<Booking>> bookings = cm.getAllBookings().stream()
	        .map(booking -> {
	            EntityModel<Booking> model = EntityModel.of(booking,
	                linkTo(methodOn(BookingController.class).getBooking(booking.getId())).withSelfRel(),
	                linkTo(methodOn(BookingController.class).getAllBookings()).withRel("bookings"));
	            return model;
	        })
	        .collect(Collectors.toList());

	    return CollectionModel.of(bookings, linkTo(methodOn(BookingController.class).getAllBookings()).withSelfRel());
	}
	
	@GetMapping("/bookings/{id}")
	public EntityModel<Booking> getBooking(@PathVariable UUID id) {
		Booking b = cm.getBooking(id);
		if(b.getId() != null) {
			return EntityModel.of(b, //
				      linkTo(methodOn(BookingController.class).getBooking(id)).withSelfRel(),
				      linkTo(methodOn(BookingController.class).getAllBookings()).withRel("bookings"));
		}else {
			throw new BookingNotFoundException(id);
		}
	}
	
	@GetMapping("/bookings/{id}/pendingPay") 
	public String pendingPay(@PathVariable UUID id) {
		return "Queda por pagar un total de " + cm.pendingPay(id);
	}	
	
	@GetMapping("/caravans/availableByDate")
    public List<Caravan> availableByDate(@RequestParam String ini, @RequestParam String end) {
        LocalDate iniDate = LocalDate.parse(ini);
        LocalDate endDate = LocalDate.parse(end);
        return cm.availableByDate(iniDate, endDate, CaravanController.getCaravanManager());
    }
	
	@GetMapping("/bookings/totalBookings")
	public int totalBookings() {
		return cm.totalBookings();
	}
	
	@GetMapping("/bookings/averageDaysPerBooking")
	public double averageDaysPerBooking() {
		return cm.averageDaysPerBooking();
	}
	
	@GetMapping("/bookings/totalIncomes")
	public int totalIncomes() {
		return cm.totalIncomes(CaravanController.getCaravanManager());
	}
	
	@PutMapping("/bookings/{id}")
	public Booking replaceBooking(@RequestBody Booking newBooking, @PathVariable UUID id) {
		Booking old = cm.getBooking(id);
		if(!newBooking.getIniDate().equals(LocalDate.now())) {
			old.setIniDate(newBooking.getIniDate());
		}
		if(!newBooking.getEndDate().equals(LocalDate.now())) {
			old.setIniDate(newBooking.getIniDate());
		}
		
		
		cm.updateBooking(newBooking, id);
		return newBooking;
	}
	
	@PutMapping("/bookings/pay")
    public String payAmount(@RequestParam UUID id, @RequestParam int amount) {
        cm.payAmount(id, amount);
        if(cm.getBooking(id).getPendingPay() == 0) {
        	return "Se ha abonado al completo la reserva";
        } else {
        	return "Pago realizado con exito. Falta por pagar: " + cm.getBooking(id).getPendingPay() ;
        }
    }
	
	@PutMapping("/bookings/{id}/checkIn")
    public Booking checkInBooking(@PathVariable UUID id) {
        cm.checkIn(id);
        return cm.getBooking(id);
    }
	
	@PutMapping("/bookings/{id}/checkOut")
    public Booking checkOutBooking(@PathVariable UUID id) {
        cm.CheckOut(id);
        return cm.getBooking(id);
    }
	
	@PutMapping("bookings/{id}/cancel")
    public Booking cancelBooking(@PathVariable UUID id) {
        cm.cancel(id);
        return cm.getBooking(id);
    }
	
	@PostMapping("/bookings")
	public Booking saveBooking(@RequestParam LocalDate iniDate, @RequestParam LocalDate endDate, 
			@RequestParam String dniCustomer, String plateCaravan) {
		 
		CustomerRepository customerRepository = CustomerController.getCustomerRepository();
		CaravanRepository caravanRepository = CaravanController.getCaravanRepository();


	   //convertimos en Caravan si es distinto de null
		Optional<Caravan> caravanOptional = caravanRepository.findByPlate(plateCaravan);
		Caravan caravan = caravanOptional.orElseThrow(() ->
		        new CaravanNotFoundException("La caravana con la placa " + plateCaravan + " no se encontró"));

		

		//convertimos en Customer si es distinto de null
		Optional<Customer> customerOptional = customerRepository.findByDni(dniCustomer);
		Customer customer = customerOptional.orElseThrow(() ->
		        new CustomerNotFoundException("El cliente con dni " + dniCustomer + " no se encontró"));
		
		Booking newBooking = new Booking(iniDate, endDate, customer, caravan);
		
		CaravanManager caravanManager = CaravanController.getCaravanManager();
		
		cm.saveBooking( newBooking, caravanManager);
		
		return newBooking;
	}
	
	@DeleteMapping("/bookings/{id}")
	public void deleteBooking(@PathVariable UUID id) {
		cm.deleteBooking(id);
	}
	
	
}
