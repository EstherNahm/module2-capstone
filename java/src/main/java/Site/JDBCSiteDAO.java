package Site;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.rowset.*;

import Reservations.Reservations;

public class JDBCSiteDAO implements SiteDAO{
	

private JdbcTemplate jdbcTemplate;
	
	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* (non-Javadoc)
	 * @see Site.SiteDAO#getAllSites(int)
	 */
	@Override
	public List<Site> getAllSites(int siteNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Site.SiteDAO#searchBySites(int)
	 */
	@Override
	public List<Site> searchBySites(int siteId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Site.SiteDAO#save(int)
	 */
	@Override
	public void save(int siteNumber) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Site.SiteDAO#delete(int)
	 */
	@Override
	public void delete(int siteNumber) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Site.SiteDAO#update(int)
	 */
	@Override
	public void update(int siteNumber) {
		// TODO Auto-generated method stub
		
	}
	
public List<Site> makeAReservation(int siteId, Date fromDate, Date toDate) { //Method to make a reservation
		
		List<Site> allReservations = new ArrayList<Site>();	//Declaring and instantiating new  Site array list
		
		// Sql search statement
		String sqlSearchReservations = "SELECT * FROM site WHERE site.site_id NOT IN (SELECT site_id from reservation where reservation.from_date between ? and ? or reservation.to_date between ? and ? or from_date < ? and to_date > ?) AND site.campground_id = ? limit 5";
		
		SqlRowSet returned = jdbcTemplate.queryForRowSet(sqlSearchReservations, fromDate, toDate, fromDate, toDate, fromDate, toDate, siteId); //SqlRowSet object that inserts our passed in information into our SQL search
		
		while(returned.next()) {//Loop through as long as their is another item matching the search criteria
			Site reservation = mapRowToSite(returned); //Putting info from our SqlRowSet into our mapRow method into a site object containing a complete site
			allReservations.add(reservation); //Adding that site to our list
		}
		return allReservations; //Returning the list of sites

	}
	
private Site mapRowToSite(SqlRowSet returned) {

		Site aSite = new Site();
		
		aSite.setSite_id(returned.getInt("site_id"));
		aSite.setCampground_id(returned.getInt("campground_id"));
		aSite.setSite_number(returned.getInt("site_number"));
		aSite.setMax_occupancy(returned.getInt("max_occupancy"));
		aSite.setAccessible(returned.getBoolean("accessible"));
		aSite.setMax_rv_length(returned.getInt("max_rv_length"));
		aSite.setUtilities(returned.getBoolean("utilities"));
		
		return aSite;
}
}