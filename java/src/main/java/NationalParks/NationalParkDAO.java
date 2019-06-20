package NationalParks;

import java.util.List;

public interface NationalParkDAO {
	
	public List<NationalPark> getAllParks(String parkName);
	public List<NationalPark> searchByPark(int parkId);
	public void save(String parkName);
	public void delete(String parkName);
	public void update(String parkName);

	
	
	 
	
}
