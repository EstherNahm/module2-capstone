package Reservations;

import java.util.List;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.rowset.*;

public class JDBCReservationsDAO implements ReservationsDAO{

	/* (non-Javadoc)
	 * @see Reservations.ReservationsDAO#searchByReservation(java.lang.String)
	 */
	@Override
	public List<Reservations> searchByReservation(String reservationId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Reservations.ReservationsDAO#save(java.lang.String)
	 */
	@Override
	public void save(String reservationName) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Reservations.ReservationsDAO#delete(java.lang.String)
	 */
	@Override
	public void delete(String reservationName) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Reservations.ReservationsDAO#update(java.lang.String)
	 */
	@Override
	public void update(String reservationName) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Reservations.ReservationsDAO#isAvailable(java.lang.Boolean)
	 */
	@Override
	public Reservations isAvailable(Boolean isAvailable) {
		// TODO Auto-generated method stub
		return null;
	}

}
