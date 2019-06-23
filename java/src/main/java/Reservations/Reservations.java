package Reservations;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Reservations {
	private int reservationId;
	private int siteId;
	private String reservationName;
	private Date fromDate;
	private Date toDate;
	private Date createDate;
	
	/**
	 * @return the reservationId
	 */
	public int getReservationId() {
		return reservationId;
	}
	/**
	 * @param reservationId the reservationId to set
	 */
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	/**
	 * @return the siteId
	 */
	public int getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	/**
	 * @return the reservationName
	 */
	public String getReservationName() {
		return reservationName;
	}
	/**
	 * @param reservationName the reservationName to set
	 */
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}
	/**
	 * @param date the fromDate to set
	 */
	public void setFromDate(Date date) {
		this.fromDate = date;
	}
	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Reservations [reservationId=" + reservationId + ", siteId=" + siteId + ", reservationName="
				+ reservationName + ", fromDate=" + fromDate + ", toDate=" + toDate + ", createDate=" + createDate
				+ "]";
	}
	

}
