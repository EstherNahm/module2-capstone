package NationalParks;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.rowset.*;


public class JDBCNationalParkDAO implements NationalParkDAO{

	private JdbcTemplate jdbcTemplate;
	
	public JDBCNationalParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	/* (non-Javadoc)
	 * @see NationalParks.NationalParkDAO#getAllParks(java.lang.String)
	 */
	@Override
	public List<NationalPark> getAllParks() { 
		List<NationalPark> allParks = new ArrayList<NationalPark>();
		
		String sqlSearchParks = "SELECT * FROM park";
		
		SqlRowSet returned = jdbcTemplate.queryForRowSet(sqlSearchParks);
		
		while(returned.next()) {
			NationalPark aPark = mapRowToParks(returned);
			allParks.add(aPark);
		}
		return allParks;
	}

	/* (non-Javadoc)
	 * @see NationalParks.NationalParkDAO#searchByPark(java.lang.String)
	 */
	@Override
	public List<NationalPark> searchByPark(String name) {
		List<NationalPark> allParks = new ArrayList<NationalPark>();
		String sqlSearchParks = "SELECT * FROM park WHERE name = ?";
		
		SqlRowSet returned = jdbcTemplate.queryForRowSet(sqlSearchParks, name);
		
		while(returned.next()) {
			NationalPark aPark = mapRowToParks(returned);
			allParks.add(aPark);
		}
		return allParks;
	}
	
//	public void showParkByName(String choice) {
//		List<NationalPark> parkList = getAllParks();           // invoke method to display items in Vending Machine
//		String[] parkNames = new String[parkList.size() + 1];
//		int parkNum = 0;
//		for(NationalPark aPark : parkList) {
//			parkNames[parkNum] = aPark.getName();
//			parkNum++;
//		}
//		parkNames[parkNum] = "Quit";
//		choice = (String)parkMenu.getChoiceFromOptions(parkNames);
//		System.out.println("Choice = " + choice);
//		break;    
//	}

	/* (non-Javadoc)
	 * @see NationalParks.NationalParkDAO#save(java.lang.String)
	 */
	@Override
	public void save(String parkName) {
		
		
	}

	/* (non-Javadoc)
	 * @see NationalParks.NationalParkDAO#delete(java.lang.String)
	 */
	@Override
	public void delete(String parkName) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see NationalParks.NationalParkDAO#update(java.lang.String)
	 */
	@Override
	public void update(String parkName) {
		// TODO Auto-generated method stub
		
	}
	
	private NationalPark mapRowToParks(SqlRowSet returned) {
		
		NationalPark aPark = new NationalPark();
		
		aPark.setPark_id(returned.getInt("park_id"));
		aPark.setName(returned.getString("name"));
		aPark.setLocation(returned.getString("location"));
		aPark.setEstablish_date(returned.getDate("establish_date"));
		aPark.setArea(returned.getInt("area"));
		aPark.setVisitors(returned.getInt("visitors"));
		aPark.setDescription(returned.getString("description"));
		
		return aPark;
	}
	
}
