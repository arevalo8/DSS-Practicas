package trabajo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookingDao implements Dao<Booking> {

	private List<Booking> bookings = new ArrayList<Booking>();

	public BookingDao() {
		super();
	}
	
	@Override
	public Optional<Booking> get(UUID id) {
		// Devuelve un Optional<Booking> con la Booking cuyo id es id o vac�o si no
		// existe esa Booking
		Booking resultado = null;

		for (int i = 0; i < bookings.size(); i++) {
			if (bookings.get(i).getId().equals(id)) {
				resultado = bookings.get(i);
			}
		}
		return Optional.ofNullable(resultado);
	}
	
	@Override
	public List<Booking> getAll() {
		return bookings;
	}
	
	@Override
	public void save(Booking b) {
		bookings.add(b);
	}
	
	@Override
	public void update(Booking c, String parameterToModify, String[] params) {
		if(parameterToModify == "state" ||
				parameterToModify == "pendingPay") {
	    	if(parameterToModify == "state") {
	    		Optional<Booking> booking = this.get(c.getId()); 
	    		if(booking.isPresent()){
	    			(booking.get()).setState(params[0]);
	    		}//Si no est� presente no se hace nada
	    	}
	    	
	    	if(parameterToModify == "pendingPay") {
	    		Optional<Booking> booking = this.get(c.getId()); 
	    		if(booking.isPresent()){
	    			(booking.get()).setPendingPay(Integer.parseInt(params[0]));
	    		}//Si no est� presente no se hace nada
	    	}
		}else {
			//Código de modificar todos los datos de la caravana
    		Optional<Booking> booking = this.get(UUID.fromString(params[0])); 
    		if(booking.isPresent()){
    			this.delete(booking.get());
    			this.save(c);
    		}//Si no está presente no se hace nada
		}	
    	
	}
	
	@Override
	public void delete(Booking b) {
		bookings.remove(b);
	}

	//Lo añadimos por compatibilidad con el Dao
	@Override
	public Optional<Booking> get(String naturalIdentifier) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}