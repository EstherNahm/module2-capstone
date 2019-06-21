package Reservations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.rowset.*;

import Campgrounds.Campgrounds;
import Campgrounds.CampgroundsDAO;

//import Campgrounds.JDBCCampgroundsDAO;

public class JDBCReservationsDAO implements ReservationsDAO, CampgroundsDAO {
	private JdbcTemplate jdbcTemplate;
	
//	public JDBCCampgroundsDAO(DataSource dataSource) {
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}
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

	//public List<Reservations> searchForReservation(String departure, String arrival, int campground) {
	public List<Reservations> searchForReservation(String departure, String arrival, int campground) {//, String arrival1, String departure2) {
		Map<Integer, Double> reservationSearch = new HashMap<Integer, Double>();
		String sqlSearchReservations = "SELECT site.id, (date(?) - date(?)) * campground.daily_fee AS totalfee"
				+ "FROM campground "
				+ "JOIN site ON campground.campground_id = site.campground_id "
				+ "JOIN reservation ON site.site_id = reservation.site_id "
				+ "WHERE campground.campground_id = ? "
				+ "AND (date(?) < reservation.from_date OR date(?) > reservation.to_date) "
				+ "GROUP BY campground.campground_id "
				+ "LIMIT 5;"; 
				
				SqlRowSet returned = jdbcTemplate.queryForRowSet(sqlSearchReservations, departure, arrival, campground, arrival, departure);
				while (returned.next()) {
					Reservations reservation = mapRowToReservations(returned);
					reservationSearch.add(reservation);
				}
				return reservationSearch;
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
	
	private Reservations mapRowToReservations1(SqlRowSet returned) {
		Reservations aReservation = new Reservations();
		
		
		
		
	}
	
	private Reservations mapRowToReservations(SqlRowSet returned) {
		Reservations aReservation = new Reservations();
		
		aReservation.setReservationId(returned.getInt("reservation_id"));
		aReservation.setSiteId(returned.getInt("site_id"));
		aReservation.setReservationName(returned.getString("name"));
		aReservation.setFromDate(returned.getString("from_date"));
		aReservation.setToDate(returned.getString("to_date"));
		aReservation.setCreateDate(returned.getDate("create_date"));
		
		return aReservation;
	}
	public List<Campgrounds> getAllCampgrounds(){
		return null;
	}
	public List<Campgrounds> getCampgroundById(int parkId){
		return null;
	}
	public List<Campgrounds> searchByCampgrounds(String campId){
		return null;
	}
	

}
