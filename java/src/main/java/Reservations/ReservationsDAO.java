package Reservations;

import java.sql.Date;
import java.util.List;

import Campgrounds.Campgrounds;

public interface ReservationsDAO {
	public List<Reservations> searchByReservation(int reservationId);
	public void save(String reservationName);
	public void delete(String reservationName);
	public void update(String reservationName);
	public Reservations isAvailable(Boolean isAvailable);
	public List<Reservations> searchForReservation(String departure, String arrival, int campground);
	
}
