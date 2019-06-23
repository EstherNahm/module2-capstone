package Reservations;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.rowset.*;

import Site.Site;


public class JDBCReservationsDAO implements ReservationsDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCReservationsDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	public List<Reservations> searchByReservationId(int reservationId) {
		
		List<Reservations> allReservations = new ArrayList<Reservations>();
		
		String sqlSearchReservations = "SELECT * FROM reservation WHERE reservation_id = ?";
		
		SqlRowSet returned = jdbcTemplate.queryForRowSet(sqlSearchReservations, reservationId);
		
			while(returned.next()) {
				Reservations reservation = mapRowToReservations(returned);
				allReservations.add(reservation);
			}
			return allReservations;
	}



public int makeReservation(int siteId, String name, Date fromDate, Date toDate) {
		

		String sqlMakeReservations = "INSERT INTO reservation (site_id, name, from_date, to_date) " + 
				"values (?, ?, ?, ?)";
		
		jdbcTemplate.update(sqlMakeReservations, siteId, name, fromDate, toDate);
		
		
		int confirmed = 0;
		SqlRowSet results = jdbcTemplate.queryForRowSet("select max(reservation_id) " + 
				"from reservation "); 
		while(results.next()) {
			confirmed = results.getInt(1);
		}
		
	return confirmed;
		
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


	@Override
	public List<Reservations> searchForReservation(String departure, String arrival, int campground) {
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


	@Override
	public Reservations isAvailable(Boolean isAvailable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Reservations> makeAReservation(int siteId, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public double costOfReservation(int campground) {
		// TODO Auto-generated method stub
		return 0;
	}



	

}
