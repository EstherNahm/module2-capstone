package Site;

import java.util.List;

import Campgrounds.Campgrounds;

public interface SiteDAO {
	public List<Site> getAllSites(int siteNumber);
	public List<Site> searchBySites(int siteId);
	public void save(int siteNumber);
	public void delete(int siteNumber);
	public void update(int siteNumber);	
}
