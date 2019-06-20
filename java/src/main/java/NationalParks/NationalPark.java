package NationalParks;

import java.sql.Date;

public class NationalPark {
	
	
	private int park_id;
	private String name;
	private String location;
	private Date establish_date;
	private int area;
	private int visitors;
	private String description;
	
	/**
	 * @return the park_id
	 */
	public int getPark_id() {
		return park_id;
	}
	/**
	 * @param park_id the park_id to set
	 */
	public void setPark_id(int park_id) {
		this.park_id = park_id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the establish_date
	 */
	public Date getEstablish_date() {
		return establish_date;
	}
	/**
	 * @param establish_date the establish_date to set
	 */
	public void setEstablish_date(Date establish_date) {
		this.establish_date = establish_date;
	}
	/**
	 * @return the area
	 */
	public int getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(int area) {
		this.area = area;
	}
	/**
	 * @return the visitors
	 */
	public int getVisitors() {
		return visitors;
	}
	/**
	 * @param visitors the visitors to set
	 */
	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return park_id + ". " + name + "\n";
	}
	
}
