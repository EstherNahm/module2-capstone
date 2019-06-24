package Campgrounds;

public class Campgrounds {

	private int park_id;
	private int campground_id;
	private String name;
	private String daily_fee;
	private String open_to_mm;
	private String open_from_mm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

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
	 * @return the open_from_mm
	 */
	public String getOpen_from_mm() {
		return open_from_mm;
	}

	/**
	 * @param open_from_mm the open_from_mm to set
	 */
	public void setOpen_from_mm(String open_from_mm) {
		this.open_from_mm = open_from_mm;
	}

	/**
	 * @return the open_to_mm
	 */
	public String getOpen_to_mm() {
		return open_to_mm;
	}

	/**
	 * @param open_to_mm the open_to_mm to set
	 */
	public void setOpen_to_mm(String open_to_mm) {
		this.open_to_mm = open_to_mm;
	}

	/**
	 * @return the daily_fee
	 */
	public String getDaily_fee() {
		return daily_fee;
	}

	/**
	 * @param daily_fee the daily_fee to set
	 */
	public void setDaily_fee(String daily_fee) {
		this.daily_fee = daily_fee;
	}

	@Override
	public String toString() {
		return "Campgrounds [campground_id=" + campground_id + ", park_id=" + park_id + ", name=" + name
				+ ", open_from_mm=" + open_from_mm + ", open_to_mm=" + open_to_mm + ", daily_fee=" + daily_fee + "]";
	}
}
