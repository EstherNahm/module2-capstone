package Site;

public class Site {
	
	private int site_id;
	private int site_number;
	private int campground_id;
	private int max_occupancy;
	private int max_rv_length;
	private Boolean accessible;
	private Boolean utilities;
	/**
	 * @return the site_id
	 */
	public int getSite_id() {
		return site_id;
	}
	/**
	 * @param site_id the site_id to set
	 */
	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}
	/**
	 * @return the campground_id
	 */
	public int getCampground_id() {
		return campground_id;
	}
	/**
	 * @param campground_id the campground_id to set
	 */
	public void setCampground_id(int campground_id) {
		this.campground_id = campground_id;
	}
	/**
	 * @return the site_number
	 */
	public int getSite_number() {
		return site_number;
	}
	/**
	 * @param site_number the site_number to set
	 */
	public void setSite_number(int site_number) {
		this.site_number = site_number;
	}
	/**
	 * @return the max_occupancy
	 */
	public int getMax_occupancy() {
		return max_occupancy;
	}
	/**
	 * @param max_occupancy the max_occupancy to set
	 */
	public void setMax_occupancy(int max_occupancy) {
		this.max_occupancy = max_occupancy;
	}
	/**
	 * @return the accessible
	 */
	public Boolean getAccessible() {
		return accessible;
	}
	/**
	 * @param accessible the accessible to set
	 */
	public void setAccessible(Boolean accessible) {
		this.accessible = accessible;
	}
	/**
	 * @return the max_rv_length
	 */
	public int getMax_rv_length() {
		return max_rv_length;
	}
	/**
	 * @param max_rv_length the max_rv_length to set
	 */
	public void setMax_rv_length(int max_rv_length) {
		this.max_rv_length = max_rv_length;
	}
	/**
	 * @return the utilities
	 */
	public Boolean getUtilities() {
		return utilities;
	}
	/**
	 * @param utilities the utilities to set
	 */
	public void setUtilities(Boolean utilities) {
		this.utilities = utilities;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Site: " + site_id + "  campgroundID: " + campground_id + "  Site#: " + site_number
				+ "  max_occupancy: " + max_occupancy + "  Handicap accessible: " + accessible + "  max length: " + max_rv_length
				+ "  utilities: " + utilities;
	}
	
	

}
