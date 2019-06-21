package Site;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.rowset.*;

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
	
	

}
