package trabajo;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

@jakarta.persistence.Entity
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private @Id @Column(name="id", nullable = false, unique = true) UUID id;
	//private @Id @GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name="id", nullable = false, unique = true)int id;
	private @Column(name="name", nullable = false)String name;
	private @Column(name="first_surname", nullable = false)String firstSurname;
	private @Column(name="second_surname", nullable = true)String secondSurname;
	private @Column(name="dni", nullable = false)String dni;
	private @Column(name="phones", nullable = false)String phone;
	private @Column(name="email", nullable = false)String email;
	private @Column(name="driving_license_type", nullable = false)String drivingLicenseType;
	


	public Customer() {
		this.id = UUID.randomUUID();
		this.name = "Fernando";
		this.firstSurname = "Alonso";
		this.secondSurname = "Quijano";
		this.dni = "11111111X";
		this.phone = "11111111";
		this.email = "fernando@gmail.com";
		this.drivingLicenseType = "Z";
	}
	
	
	public Customer(String name, String firstSurname, String secondSurname, String dni, String phone,
			String email, String drivingLicenseType) {
		super();
		this.id = UUID.randomUUID();
		this.name = name;
		this.firstSurname = firstSurname;
		this.secondSurname = secondSurname;
		this.dni = dni;
		this.phone = phone;
		this.email = email;
		this.drivingLicenseType = drivingLicenseType;
	}
	
	
	//Para mantener la copia de elementos y poder recuperarlo del CustomerManager
	public Customer(UUID id, String name, String firstSurname, String secondSurname, String dni, String phone,
			String email, String drivingLicenseType) {
		super();
		this.id = id;
		this.name = name;
		this.firstSurname = firstSurname;
		this.secondSurname = secondSurname;
		this.dni = dni;
		this.phone = phone;
		this.email = email;
		this.drivingLicenseType = drivingLicenseType;
	}
	
	
	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFirstSurname() {
		return firstSurname;
	}

	public String getSecondSurname() {
		return secondSurname;
	}

	public String getDni() {
		return dni;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getDrivingLicenseType() {
		return drivingLicenseType;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDrivingLicenseType(String drivingLicenseType) {
		this.drivingLicenseType = drivingLicenseType;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, name, firstSurname, secondSurname, dni, phone, email, drivingLicenseType);
    }
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Customer customer = (Customer) o;
	    return Objects.equals(id, customer.id);
	}

	
	@Override
	public String toString() {
		return "Customer with id " + this.id +
				": name=" + this.name +
				", firstSurname=" + this.firstSurname +
				", secondSurname=" + this.secondSurname +
				", dni=" + this.dni +
				", phone=" + this.phone +
				", email=" + this.email +
				", drivingLicenseType=" + this.drivingLicenseType +
				"\n";
	}
	
}
