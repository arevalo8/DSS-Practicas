package trabajo;

import static java.time.temporal.ChronoUnit.DAYS;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@jakarta.persistence.Entity
public class Booking implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private @Id @Column(name="id", nullable = false, unique = true) UUID id;
	//private @Id @GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name="id", nullable = false, unique = true)int id;
	private @Column(name="ini_date", nullable = false)LocalDate iniDate;
	private @Column(name="end_date", nullable = false)LocalDate endDate;
	private @Column(name="state", nullable = false)String state;
	private @ManyToOne @JoinColumn(name="id_customer", nullable = true)Customer customer;
	private @ManyToOne @JoinColumn(name="id_caravan", nullable = true)Caravan caravan;
	private @Column(name="pending_pay", nullable = false)int pendingPay;
	//private @Column(name="delay_price", nullable = false)int delayPrice;
	
	public Booking() {
		this.iniDate = LocalDate.parse("2023-06-22");
		this.endDate = LocalDate.parse("2023-06-22");
		this.state = "";
		this.customer = null;
		this.caravan = null;
		this.pendingPay = 0;
		//this.delayPrice = 0;
	}
	
	public Booking(LocalDate iniDate, LocalDate endDate, Customer customer, Caravan caravan) {
		super();
		this.id = UUID.randomUUID();
		this.iniDate = iniDate;
		this.endDate = endDate;
		this.state = "Booked - Unpaid";
		this.customer = customer;
		this.caravan = caravan;
		//this.delayPrice = 0;
		
		
		//calculamos el precio total de la reserva
		int totalPrice = (int) DAYS.between(iniDate, endDate) * caravan.getPricePerDay();
		
		this.pendingPay = totalPrice;
		 
	}
	
	//Para poder copiar reservas y mantener compatibilidad con BookingManager
	public Booking(UUID id, LocalDate iniDate, LocalDate endDate, String state, Customer customer, Caravan caravan, int pendingPay) {
		super();
		this.id = id;
		this.iniDate = iniDate;
		this.endDate = endDate;
		this.state = state;
		this.customer = customer;
		this.caravan = caravan;
		//this.delayPrice = 0;
		this.pendingPay = pendingPay;
	}	
	
	public UUID getId() {
		return id;
	}

	/*public void setId(int id) {
		this.id = id;
	}*/

	public LocalDate getIniDate() {
		return iniDate;
	}

	public void setIniDate(String iniDate) {
		this.iniDate = LocalDate.parse(iniDate);
	}

	public void setIniDate(LocalDate iniDate) {
		this.iniDate = iniDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = LocalDate.parse(endDate);
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Caravan getCaravan() {
		return caravan;
	}

	public void setCaravan(Caravan caravan) {
		this.caravan = caravan;
	}
	
	public UUID fromCaravanGetId() {
		return this.caravan.getId();
	}
	
	public UUID fromCustomerGetId() {
		return this.customer.getId();
	}
	
	public int getPendingPay() {
		return pendingPay;
	}
	
	public void setPendingPay(int pendingPay) {
		this.pendingPay = pendingPay;
	}
	
	/*public int getDelayPrice() {
		return delayPrice;
	}
	
	public void setDelayPrice(int delayPrice) {
		this.delayPrice = delayPrice;
	}*/
	
	@Override
	  public int hashCode() {
	    return Objects.hash(id);
	  }
	


	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Booking booking = (Booking) o;

	    return Objects.equals(id, booking.id);
	}

	
	
	
	@Override
	public String toString() {
		return "Booking with id " + this.id +
				": iniDate=" + this.iniDate +
				", endDate=" + this.endDate +
				", state=" + this.state +
				", idCustomer=" + fromCustomerGetId() +
				", idCaravan=" + this.caravan.getId() +
				"\n";
	}
	

}
