package Reservations;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.rowset.*;

public class JDBCReservationsDAO implements ReservationsDAO{
	private JdbcTemplate jdbcTemplate;
	
	public JDBCReservationsDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* (non-Javadoc)
	 * @see Reservations.ReservationsDAO#searchByReservation(java.lang.String)
	 */
	@Override
	public List<Reservations> searchByReservation(int reservationId) {
		List<Reservations> allReservations = new ArrayList<Reservations>();
		
		String sqlSearchReservations = "SELECT * FROM reservation WHERE reservation_id = ?";
		
		SqlRowSet returned = jdbcTemplate.queryForRowSet(sqlSearchReservations, reservationId);
		
		while(returned.next()) {
			Reservations reservation = mapRowToReservations(returned);
			allReservations.add(reservation);
		}
		return allReservations;
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
	
	private Reservations mapRowToReservations(SqlRowSet returned) {
		Reservations aReservation = new Reservations();
		
		aReservation.setReservationId(returned.getInt("reservation_id"));
		aReservation.setSiteId(returned.getInt("site_id"));
		aReservation.setReservationName(returned.getString("name"));
		aReservation.setFromDate(returned.getDate("from_date"));
		aReservation.setToDate(returned.getDate("to_date"));
		aReservation.setCreateDate(returned.getDate("create_date"));
		
		return aReservation;
	}

}
