package Site;

import java.sql.Date;
import java.util.List;

import Campgrounds.Campgrounds;

public interface SiteDAO {
	public List<Site> getAllSites(int siteNumber);
	public List<Site> searchBySites(int siteId);
	public void update(int siteNumber);	
	public void save(int siteNumber);
	public void delete(int siteNumber);
	public List<Site> makeAReservation(int siteId, Date fromDate, Date toDate);
}
