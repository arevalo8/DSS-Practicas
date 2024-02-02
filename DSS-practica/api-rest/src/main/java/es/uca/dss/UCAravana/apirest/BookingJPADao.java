package es.uca.dss.UCAravana.apirest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import trabajo.Booking;
import trabajo.Dao;

public class BookingJPADao implements Dao<Booking> {

	BookingRepository repository;


	BookingJPADao(BookingRepository repository){
		//Se le asigna una instancia de una clase que implementa BookingRepository (de las que crea Spring)
		this.repository = repository;
	}

	@Override
	public List<Booking> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Booking> get(UUID id) {
		return repository.findById(id);
	}

	@Override
	public void save(Booking b) {
		repository.save(b);
	}

	/*@Override
	public void update(Booking t, String parameterToModify, String[] params) {
		//Sustituye los datos de la reserva con id params[0] por los de la reserva t, si no existe, guarda la reserva en la BD
		Optional<Booking> booking = this.get(t.getId()); 
		Optional<Booking> booking = repository.findById(UUID.fromString(params[0]));
			      if(booking.isPresent()){
			        booking.get().setIniDate(t.getIniDate());
			        booking.get().setEndDate(t.getEndDate());
			        booking.get().setState(t.getState());
			        booking.get().setCustomer(t.getCustomer());
			        booking.get().setCaravan(t.getCaravan());
			       // booking.get().setDelayPrice(t.getDelayPrice());
			        booking.get().setPendingPay(t.getPendingPay());

			        repository.save(booking.get());
			      }else {
				    repository.save(t);
			      };


	}*/

	@Override
	public void update(Booking t, String parameterToModify, String[] params) {
		//Sustituye los datos de la reserva con id params[0] por los de la reserva t, si no existe, guarda la reserva en la BD
		//Optional<Booking> booking = this.get(t.getId()); 

		if(parameterToModify.equals("state") ||
				parameterToModify.equals("pendingPay")) {
			if(parameterToModify.equals("state")) {
				Optional<Booking> booking = this.get(t.getId()); 
				if(booking.isPresent()){
					(booking.get()).setState(params[0]);
					repository.save(booking.get());
				}//Si no est� presente no se hace nada
			}

			if(parameterToModify.equals("pendingPay")) {
				Optional<Booking> booking = this.get(t.getId()); 
				if(booking.isPresent()){
					(booking.get()).setPendingPay(Integer.parseInt(params[0]));
					repository.save(booking.get());
				}//Si no est� presente no se hace nada
			}
		}else {
			Optional<Booking> booking = repository.findById(UUID.fromString(params[0]));
			if(booking.isPresent()){
				booking.get().setIniDate(params[0]);
				booking.get().setEndDate(params[0]);
				booking.get().setState(params[0]);
				booking.get().setCustomer(t.getCustomer());
				booking.get().setCaravan(t.getCaravan());
				// booking.get().setDelayPrice(t.getDelayPrice());
				booking.get().setPendingPay(t.getPendingPay());

				repository.save(booking.get());
			}else {
				repository.save(t);
			}
		}
	}		


	@Override
	public void delete(Booking t) {
		repository.deleteById(t.getId());

	}

	//Lo añadimos por compatibilidad con el dao
	@Override
	public Optional<Booking> get(String naturalIdentifier) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}