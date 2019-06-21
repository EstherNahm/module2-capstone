package Campgrounds;

import java.util.List;

public interface CampgroundsDAO {
	public List<Campgrounds> getAllCampgrounds();
	public List<Campgrounds> getCampgroundById(int campgroundId);
	public List<Campgrounds> searchByCampgrounds(String campId);
	public void save(String campName);
	public void delete(String campName);
	public void update(String campName);
	public Campgrounds isAvailable(Boolean isAvailable);
	
}
