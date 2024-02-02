package es.uca.dss.UCAravana.apirest;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trabajo.Caravan;

public interface CaravanRepository extends JpaRepository<Caravan, UUID> { 
	public Optional<Caravan> findByPlate(String plate);
}
