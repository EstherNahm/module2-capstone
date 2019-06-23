package Campgrounds;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.rowset.*;

import Reservations.Reservations;


public class JDBCCampgroundsDAO implements CampgroundsDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCCampgroundsDAO(DataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public List<Campgrounds> getAllCampgrounds() {
		
		List<Campgrounds> allGrounds = new ArrayList<Campgrounds>();
		
		String sqlSearchGrounds = "SELECT * FROM campground";
		
		SqlRowSet returned = jdbcTemplate.queryForRowSet(sqlSearchGrounds);
		
			while(returned.next()) {
				Campgrounds aGround = mapRowToGrounds(returned);
				allGrounds.add(aGround);
			}
		return allGrounds;
	}
	
	public List<Campgrounds> getCampgroundById(int parkId) {//Method to select the campgrounds by Park ID
		
		List<Campgrounds> groundsId = new ArrayList<Campgrounds>(); //Declare and instantiate new List[]
		
		String sqlSearchId = "SELECT * FROM campground JOIN park ON campground.park_id = park.park_id WHERE park.park_id = ?";
		//SQL statement to Search by Park ID
		
		SqlRowSet returned = jdbcTemplate.queryForRowSet(sqlSearchId, parkId); //SQL search that will use parkId entered as variable for the where clause

			while(returned.next()) { //While loop for our SqlRowSet
				Campgrounds aGround = mapRowToGrounds(returned); //Campground object that accesses our mapRowToGrounds method, filling in the column information for each row
				groundsId.add(aGround);//Adding the campground rows into our List
			}
			return groundsId;//When no more campgrounds with that parkId exist, return the list of campgrounds
	}

	/* (non-Javadoc)
	 * @see Campgrounds.CampgroundsDAO#searchByCampgrounds(java.lang.String)
	 */
	@Override
	public List<Campgrounds> searchByCampgrounds(String campId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Campgrounds.CampgroundsDAO#save(java.lang.String)
	 */
	@Override
	public void save(String campName) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Campgrounds.CampgroundsDAO#delete(java.lang.String)
	 */
	@Override
	public void delete(String campName) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Campgrounds.CampgroundsDAO#update(java.lang.String)
	 */
	@Override
	public void update(String campName) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Campgrounds.CampgroundsDAO#isAvailable(java.lang.Boolean)
	 */
	@Override
	public Campgrounds isAvailable(Boolean isAvailable) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
	private Campgrounds mapRowToGrounds(SqlRowSet returned) { //Method that feeds our SqlRowSet information into a Campground object
		
		Campgrounds aCampground = new Campgrounds();//Declare an instantiate campground object
		
		//Use associated setters to set values based on the information passed from our SqlRowSet, each column in table has a corresponding setter
		aCampground.setCampground_id(returned.getInt("campground_id"));
		aCampground.setPark_id(returned.getInt("park_id"));
		aCampground.setName(returned.getString("name"));
		aCampground.setOpen_from_mm(returned.getString("open_from_mm"));
		aCampground.setOpen_to_mm(returned.getString("open_to_mm"));
		aCampground.setDaily_fee(returned.getString("daily_fee"));
		
		return aCampground;
	}
	@Override
	public List<Reservations> searchByReservationId(int reservationId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
