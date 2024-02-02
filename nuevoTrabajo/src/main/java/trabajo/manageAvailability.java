package trabajo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class manageAvailability {
	
	public manageAvailability() {
		super();
	}

	public boolean rangesOverlap(LocalDate startA, LocalDate endA, LocalDate startB, LocalDate endB) {

		
	    return !(startA.isAfter(endB) || startA.isEqual(endB)) && 
	    		!(startB.isAfter(endA) || startB.isEqual(endA));
	}


	
	public List<Caravan> viewAvailability(LocalDate ini, LocalDate end, CaravanManager cm, BookingManager bm) {
		

		List<Caravan> Lc = cm.getAllCaravans();
		List<Caravan> copiaLC = new ArrayList<Caravan>(Lc);
		
		List<Booking> Lb = bm.getAllBookings();
		//List<Booking> copiaLB = new ArrayList<Booking>(Lb);
											

		for(Booking b : Lb) {
			//si la caravana de la reserva está descartada, pasamos a la siguiente
			if(b.getCaravan() != null)  {
				if(contiene(copiaLC, b.getCaravan().getId())) {
					LocalDate startBook = b.getIniDate();
					LocalDate endBook = b.getEndDate();
					if(rangesOverlap(ini, end, startBook, endBook)) {
						//removeCaravan(copiaLC,  b.getCaravan().getId());
						//copiaLC.removeIf(c->c.getId() == b.getCaravan().getId());
						copiaLC.removeIf(c->c.getId().equals(b.getCaravan().getId()));
						
					}
				}
			}	
		}
	
	
		return copiaLC;
		//return Lc;
	
	}
	
	private boolean contiene(List<Caravan> caravans, UUID idC) {
		for(Caravan c : caravans) {
			if(c.getId() == idC) {
				return true;
			}
		}
		
		return false;
		
	}
	

	
}
