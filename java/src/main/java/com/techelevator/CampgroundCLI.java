package com.techelevator;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

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
import Site.Site;
import Site.SiteDAO;

public class CampgroundCLI {

	// public String [] displayParkNames = parkDAO;
	private static final String MAIN_MENU_DISPLAY_PARKS = "Display list of parks";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_DISPLAY_PARKS, MAIN_MENU_OPTION_EXIT };

	private static final String PARK_INFO_MENU = "View Campgrounds for this park";
	private static final String RESERVATION_SEARCH = "Check Your Existing Reservation"; // will link to available reservation
	private static final String SITE_SEARCH = "Check Available Sites";
	private static final String MAKE_RESERVATION = "Make A Reservation";
	private static final String PARK_INFO_RETURN = "Exit";
	private static final String[] PARK_INFO_OPTIONS = { PARK_INFO_MENU, RESERVATION_SEARCH, SITE_SEARCH,MAKE_RESERVATION, PARK_INFO_RETURN };

	
	
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
		
		//setSiteDAO(new JDBCSiteDAO(dataSource));
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
								campGround[groundNum1] = ground.getCampground_id() + "   " + ground.getName() + "   " + ground.getOpen_from_mm() + "   " + ground.getOpen_to_mm() + "   $" + ground.getDaily_fee();
								groundNum1++;
							}
							System.out.println();		
							System.out.println("------------CAMPGROUND INFORMATION--------------");
							System.out.println(" (ID, NAME, OPEN FROM(MONTH), OPEN UNTIL(MONTH), FEE(DAY)");
							campGround[groundNum1] = "Return to previous screen to make a reservation";
							choice1 = (String)menu.getChoiceFromOptions(campGround);
							
							
							break;
							
					
				case RESERVATION_SEARCH:
								
					Scanner myKeyboard = new Scanner(System.in);
					System.out.println("What is your reservation ID?");
					String reservation1 = myKeyboard.nextLine();
					int reservationID = Integer.parseInt(reservation1); 
					
					List<Reservations> reservation = reservationsDAO.searchByReservationId(reservationID);
					String[] reservations1 = new String [reservation.size() + 1];
							
							int num = 0;
							for (Reservations res : reservation) {
								reservations1[num] = res.getReservationId() + " " + res.getReservationName() + " " + res.getFromDate() + " " + res.getToDate();
								num++;
							}
							
							reservations1[num] = "Return to previous screen to make a reservation";
							choice1 = (String)menu.getChoiceFromOptions(reservations1);
				break;
					
				
				case SITE_SEARCH:
					
					Scanner myKeyboard1 = new Scanner(System.in);
					System.out.println("What is the ID of your desired campground?");
					String id = myKeyboard1.nextLine();
					int siteId = Integer.parseInt(id);
					
					System.out.println("What is your arrival date? (YYYY-MM-DD)");
					Date arrivalDate = Date.valueOf(myKeyboard1.nextLine());
					System.out.println("What is your departure date? (YYYY-MM-DD)");
					Date departDate = Date.valueOf(myKeyboard1.nextLine());
					
					
					List<Site> reservation2 = siteDAO.makeAReservation(siteId, arrivalDate, departDate);
					Site[] reservations3 = new Site [reservation2.size()];
					
					System.out.println("Here are the top 5 available sites for reservation: ");

					for (int i = 0; i < reservation2.size(); i++) {
						System.out.println(reservation2.get(i).toString());
					}
						
			
				case MAKE_RESERVATION:
					System.out.println();
					Scanner myKeyboard2 = new Scanner(System.in);
					System.out.println("Would you like to make a reservation? (Y) or (N)");
					String yesNo = myKeyboard2.nextLine();
					
					
					System.out.println("Enter the site ID you'd like to reserve.");
					String id1 = myKeyboard2.nextLine();
					int siteId1 = Integer.parseInt(id1);
					System.out.println("What name would you like on the reservation?");
					String name = myKeyboard2.nextLine();
					System.out.println("What is your arrival date? (YYYY-MM-DD)");
					Date arrivalDate1 = Date.valueOf(myKeyboard2.nextLine());
					System.out.println("What is your departure date? (YYYY-MM-DD)");
					Date departDate1 = Date.valueOf(myKeyboard2.nextLine());
					
					
					long difference = departDate1.getTime()-arrivalDate1.getTime();
					int diffDays = (int)(difference/(24 * 60 * 60 * 1000));
					System.out.println("The difference between those 2 days is: " + diffDays);
					
			
					
					
					if (yesNo.equals("N")) {
						endMethodProcessing();
					} else {
						
						
						int confirmed = reservationsDAO.makeReservation(siteId1, name, arrivalDate1, departDate1);
						
//						//String[] reserve1 = new String [reserve.size()];
//					
//						int number = 0;
//						for (Reservations res : reserve) {
//							reserve1[number] = res.getReservationName();
//						
						//}
						System.out.println("The reservation has been made and the id is " + confirmed);
					}
					
					
					
									
									
					break;	
					
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
		System.out.println("Goodbye!");
	}

	public SiteDAO getSiteDAO() {
		return siteDAO;
	}

	public void setSiteDAO(SiteDAO siteDAO) {
		this.siteDAO = siteDAO;
	}
}
