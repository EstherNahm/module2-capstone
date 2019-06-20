package Reservations;

import java.util.List;

import Campgrounds.Campgrounds;

public interface ReservationsDAO {
	public List<Reservations> searchByReservation(String reservationId);
	public void save(String reservationName);
	public void delete(String reservationName);
	public void update(String reservationName);
	public Reservations isAvailable(Boolean isAvailable);
	
}
