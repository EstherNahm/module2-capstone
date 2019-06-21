package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.Menu;
import Campgrounds.Campgrounds;
import Campgrounds.CampgroundsDAO;
import Campgrounds.JDBCCampgroundsDAO;
import NationalParks.JDBCNationalParkDAO;
import NationalParks.NationalPark;
import NationalParks.NationalParkDAO;
import Reservations.JDBCReservationsDAO;
import Reservations.Reservations;
import Reservations.ReservationsDAO;
import Site.JDBCSiteDAO;
import Site.SiteDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CampgroundCLI {

	// public String [] displayParkNames = parkDAO;
	private static final String MAIN_MENU_DISPLAY_PARKS = "Display list of parks";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_DISPLAY_PARKS, MAIN_MENU_OPTION_EXIT };

	private static final String PARK_INFO_MENU = "View Campgrounds for this park";
	private static final String RESERVATION_SEARCH = "Make a Reservation"; // will link to available reservation
	private static final String PARK_INFO_RETURN = "Exit";
	private static final String[] PARK_INFO_OPTIONS = { PARK_INFO_MENU, RESERVATION_SEARCH, PARK_INFO_RETURN };

	
	
	private Menu menu;
	private CampgroundsDAO campgroundsDAO;
	private NationalParkDAO nationalparkDAO;
	private ReservationsDAO reservationsDAO;
	private SiteDAO siteDAO;
	
	public static void main(String[] args) {
		CampgroundCLI application = new CampgroundCLI();
		application.run();
	}
	
	public CampgroundCLI() {
		this.menu = new Menu(System.in, System.out);
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		campgroundsDAO = new JDBCCampgroundsDAO(dataSource);
		nationalparkDAO = new JDBCNationalParkDAO(dataSource);	
		reservationsDAO = new JDBCReservationsDAO(dataSource);
		
		siteDAO = new JDBCSiteDAO(dataSource);
	}
	
	// public CampgroundCLI(DataSource datasource) {

	// create your DAOs here
	// }

	public void run() {
		boolean shouldProcess = true;         // Loop control variable	
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_DISPLAY_PARKS:
					List<NationalPark> parkList = nationalparkDAO.getAllParks();          
					String[] parkNames = new String[parkList.size() + 1];
					int parkNum = 0;
					for(NationalPark aPark : parkList) {
						parkNames[parkNum] = aPark.getName();
						parkNum++;
					}
					parkNames[parkNum] = "Quit";
					choice = (String)menu.getChoiceFromOptions(parkNames);
					
					List<NationalPark> parkInfo = nationalparkDAO.searchByPark(choice);
					for(NationalPark park: parkInfo) {
						System.out.println(park.getName() + " National Park");
						System.out.println("Location: " + park.getLocation());
						System.out.println("Established: " + park.getEstablish_date());
						System.out.println("Area: " + park.getArea());
						System.out.println("Annual Visitors: " + park.getVisitors());
						System.out.println();
						System.out.println(park.getDescription());
					}
					
						System.out.println();
						System.out.println("Select a command:");
						
						
						
						
						
						boolean shouldProcess1 = true;
						while((shouldProcess1)) { 
							String choice1 = (String)menu.getChoiceFromOptions(PARK_INFO_OPTIONS);
							switch(choice1) {
								case PARK_INFO_MENU:
							List<Campgrounds> groundID = campgroundsDAO.getCampgroundById(parkInfo.get(0).getPark_id());
									String[] campGround = new String [groundID.size() + 1];
									int groundNum1 = 0;
									for (Campgrounds ground: groundID) {
										campGround[groundNum1] = ground.getName() + "  " + ground.getOpen_from_mm() + "  " + ground.getOpen_to_mm() + "  " + ground.getDaily_fee();
										groundNum1++;
									}
							
							campGround[groundNum1] = "Return to previous screen to make a reservation";
							choice1 = (String)menu.getChoiceFromOptions(campGround);
							
							
							break;
							
							
							case RESERVATION_SEARCH:
								Scanner myKeyboard = new Scanner(System.in);
								System.out.println("Which campground?");
								String campground = myKeyboard.nextLine();
								int campground1 = Integer.parseInt(campground);
								System.out.println("What is the arrival date? Please enter in this format: YEAR-MM-DD");
								String arrival = myKeyboard.nextLine();
								String arrival1 = arrival;
								System.out.println("What is the depature date? Please enter in this format: YEAR-MM-DD");
								String departure = myKeyboard.nextLine();
								String departure2 = departure;
							List<Reservations> reservation = reservationsDAO.searchForReservation(departure, arrival, campground1);
								String[] reservations1 = new String [reservation.size()];
								int num = 0;
								for (Reservations res : reservation) {
									reservations1[num] = res.getReservationId() + " " + res.getSiteId() + " " + res.getFromDate() + " " + res.getToDate();
									num++;
								} 
								
								choice1 = (String)menu.getChoiceFromOptions(reservations1);
					

								break;
								//if(value.equals())
								
							
							case PARK_INFO_RETURN:
								endMethodProcessing();
								break;
							
							}
							
							
							
							
							
							
							
						}
					
			break;  	
				}
		
		
	}			
	return;
	}
	
	// Call another method (runCampgroundMenu) once the user has made a "choice"
					// 	- pass along the selected park

	// Exit switch statement
					
//				case MAIN_MENU_OPTION_EXIT:
//					endMethodProcessing();    // Invoke method to perform end of method processing
//					shouldProcess = false; 

//		break;                    // Exit switch statement
	
	 // End method and return to caller


	
	public static void endMethodProcessing() {
		System.out.println("Fuck off, Mate");
	}
}
