package es.uca.dss.UCAravana.apirest;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trabajo.Booking;

public interface BookingRepository extends JpaRepository<Booking, UUID> { }
