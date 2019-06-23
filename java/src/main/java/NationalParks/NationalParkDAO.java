package NationalParks;

import java.util.List;

public interface NationalParkDAO {
	
	public List<NationalPark> searchByPark(String name);
	public List<NationalPark> getAllParks();
	public void update(String parkName);
	public void save(String parkName);
	public void delete(String parkName);
	

	
	
	 
	
}
