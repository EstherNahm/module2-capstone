package Campgrounds;

import java.util.List;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.rowset.*;

public class JDBCCampgroundsDAO implements CampgroundsDAO {

	/* (non-Javadoc)
	 * @see Campgrounds.CampgroundsDAO#getAllCampgrounds(java.lang.String)
	 */
	@Override
	public List<Campgrounds> getAllCampgrounds(String campName) {
		// TODO Auto-generated method stub
		return null;
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

}
