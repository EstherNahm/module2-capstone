package Campgrounds;

import java.util.List;
import Reservations.Reservations;

public interface CampgroundsDAO {
	
	public List<Campgrounds> searchByCampgrounds(String campId);

	public List<Campgrounds> getCampgroundById(int parkId);

	public List<Campgrounds> getAllCampgrounds();

	public void update(String campName);

	public void save(String campName);

	public void delete(String campName);

	public Campgrounds isAvailable(Boolean isAvailable);

	List<Reservations> searchByReservationId(int reservationId);

	public List<Campgrounds> getCampgroundFee(int campground, int site);
}
