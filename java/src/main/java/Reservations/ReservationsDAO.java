package Reservations;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import Campgrounds.Campgrounds;

public interface ReservationsDAO {
	
	public List<Reservations> searchForReservation(String departure, String arrival, int campground);
	public List<Reservations> searchByReservationId(int reservationId);
	public void update(String reservationName);
	public void delete(String reservationName);
	public void save(String reservationName);
	public Reservations isAvailable(Boolean isAvailable);
	public List<Reservations> makeAReservation(int siteId, Date fromDate, Date toDate);
	//public List<Reservations> makeReservation(int siteId, String name, Date fromDate, Date toDate);
	//public void makeReservation(int siteId, String name, Date fromDate, Date toDate);
	public int makeReservation(int siteId, String name, Date fromDate, Date toDate);
	
}
