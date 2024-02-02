package es.uca.dss.UCAravana.apirest;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trabajo.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
	Optional<Customer> findByDni(String dni);
}