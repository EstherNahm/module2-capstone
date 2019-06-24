package com.techelevator;

import java.sql.Date;
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
		
		BasicDataSource dataSource = new BasicDataSource(); //Connecting to our database
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		campgroundsDAO = new JDBCCampgroundsDAO(dataSource); //Object to access our Campgrounds data
		nationalparkDAO = new JDBCNationalParkDAO(dataSource);	// Object to access our NationalPark data
		reservationsDAO = new JDBCReservationsDAO(dataSource); // Object to access Reservations data
		siteDAO = new JDBCSiteDAO(dataSource); // Object to access Site data
		
		
	}
	
	

	public void run() {
		boolean shouldProcess = true;         // Loop control variable	
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_DISPLAY_PARKS:
					List<NationalPark> parkList = nationalparkDAO.getAllParks();   //Accessing the datasource and using our method to get all the parks       
					String[] parkNames = new String[parkList.size() + 1]; //String array that holds all the park info, +1 for "Quit" option
					int parkNum = 0; //counter for the for loop
					for(NationalPark aPark : parkList) {//for loop to get all the park names from the List
						parkNames[parkNum] = aPark.getName(); //putting all of the names in the parkNames string
						parkNum++; //iterate to the next item in the list
					}
					parkNames[parkNum] = "Quit"; // The reason we added + 1 in our array
					choice = (String)menu.getChoiceFromOptions(parkNames); //updating the value of choice
					
					List<NationalPark> parkInfo = nationalparkDAO.searchByPark(choice); // Uses choice from user to bring up all of the park info
					for(NationalPark park: parkInfo) { //itterate through the parkInfo list
						System.out.println(park.getName() + " National Park"); // prints name of park
						System.out.println("Location: " + park.getLocation()); // prints location of park
						System.out.println("Established: " + park.getEstablish_date()); // prints established date of park
						System.out.println("Area: " + park.getArea()); // prints area of park
						System.out.println("Annual Visitors: " + park.getVisitors()); //prints annual visitors of the park
						System.out.println();//prints a blank line
						System.out.println(park.getDescription());//prints description
					}
					
						System.out.println();
						System.out.println("Select a command:");
						
						
						boolean shouldProcess1 = true; //Loop control variable
						while((shouldProcess1)) { // Loop until user wants to exit
							String choice1 = (String)menu.getChoiceFromOptions(PARK_INFO_OPTIONS); //Variable to hold user selection
							
				switch(choice1) { //Switch statement based on user selection
					case PARK_INFO_MENU:
			
				List<Campgrounds> groundID = campgroundsDAO.getCampgroundById(parkInfo.get(0).getPark_id());//List containing all the campgrounds info and using our method to select campgrounds based on the ID
						String[] campGround = new String [groundID.size() + 1]; //instantiate new String[] to hold the info from the list
						int groundNum1 = 0;//Loop variable
						for (Campgrounds ground: groundID) {//for loop
								campGround[groundNum1] = ground.getCampground_id() + "   " + ground.getName() + "   " + ground.getOpen_from_mm() + "   " + ground.getOpen_to_mm() + "   $" + ground.getDaily_fee();
								// Putting the campgroundId, name, open from date, to date and daily free from campgrounds with selected campground ID
								groundNum1++;//Update loop variable
							}
							System.out.println();		
							System.out.println("------------CAMPGROUND INFORMATION--------------");
							System.out.println(" (ID, NAME, OPEN FROM(MONTH), OPEN UNTIL(MONTH), FEE(DAY)");
							campGround[groundNum1] = "Return to previous screen to make a reservation";
							choice1 = (String)menu.getChoiceFromOptions(campGround); //Update value of users choice
							
							
							break;
							
					
				case RESERVATION_SEARCH:
								
					Scanner myKeyboard = new Scanner(System.in); //Declare and instantiate a scanner so we can read user input
					System.out.println("What is your reservation ID?");
					String reservation1 = myKeyboard.nextLine(); //Variable to hold what the user inputs
					int reservationID = Integer.parseInt(reservation1);  //Parsing the string entered into an int
					
					List<Reservations> reservation = reservationsDAO.searchByReservationId(reservationID);//List containing all reservations based on reservation Id using our method
					String[] reservations1 = new String [reservation.size() + 1]; //Declaring and Instantiating string to hold reservation info from list
							
							int num = 0; //loop variable
							for (Reservations res : reservation) {//for loop
								reservations1[num] = res.getReservationId() + " " + res.getReservationName() + " " + res.getFromDate() + " " + res.getToDate();
								//Adds the information from the List to our String[] containing reservation ID, Name, From Date, To date
								num++;//Update loop variable
							}
							
							reservations1[num] = "Return to previous screen to make a reservation";//Added +1 when we instantiated our Array size to add this option
							choice1 = (String)menu.getChoiceFromOptions(reservations1);//updating value of user choice variable
				break;
					
				
				case SITE_SEARCH:
					
					Scanner myKeyboard1 = new Scanner(System.in); //Declare and instantiate new scanner object to read user input
					System.out.println("What is the ID of your desired campground?");
					String id = myKeyboard1.nextLine(); //Variable to hold user input
					int siteId = Integer.parseInt(id); //Parsing users input from string to int
					
					System.out.println("What is your arrival date? (YYYY-MM-DD)");
					Date arrivalDate = Date.valueOf(myKeyboard1.nextLine()); //Variable to hold the date entered by user for from date
					System.out.println("What is your departure date? (YYYY-MM-DD)");
					Date departDate = Date.valueOf(myKeyboard1.nextLine()); //Variable to hold the data entered by user for to date
					
					
					List<Site> reservation2 = siteDAO.makeAReservation(siteId, arrivalDate, departDate); 
				Site[] reservations3 = new Site [reservation2.size()];
					
					System.out.println("Here are the top 5 available sites for reservation: ");

					for (int i = 0; i < reservation2.size(); i++) {
						System.out.println(reservation2.get(i).toString());
					}
					
					
				
//					
//					long difference1 = departDate.getTime()-arrivalDate.getTime(); //Declaring and instantiating an object that subtracts dates entered
//					int diffDays1 = (int)(difference1/(24 * 60 * 60 * 1000));//Casting the long to an int doing the math to make it the difference in days
//					System.out.println("The duration of your stay will cost " + (diffDays1  ));
//					
					
					
					
					
					
						
			
				case MAKE_RESERVATION:
					System.out.println();
					Scanner myKeyboard2 = new Scanner(System.in); //Declare and instantiate Scanner object to read user data
					System.out.println("Would you like to make a reservation? (Y) or (N)");
					String yesNo = myKeyboard2.nextLine(); //Variable to hold user selection
					
					

					if (yesNo.equalsIgnoreCase("N")) {
						endMethodProcessing();
					} else if (yesNo.equalsIgnoreCase("Y")) { 
						System.out.println("Enter the camp ID you'd like to reserve.");
						String campId = myKeyboard2.nextLine();
						int campId1 = Integer.parseInt(campId);
						System.out.println("Enter the site ID you'd like to reserve.");
						String id1 = myKeyboard2.nextLine(); //Variable to hold user selection
						int siteId1 = Integer.parseInt(id1);//Parsing user selection from String to int
						System.out.println("What name would you like on the reservation?");
						String name = myKeyboard2.nextLine();//Variable to hold the user input for name of reservation
						System.out.println("What is your arrival date? (YYYY-MM-DD)");
						Date arrivalDate1 = Date.valueOf(myKeyboard2.nextLine());//Date variable to hold user input as from date
						System.out.println("What is your departure date? (YYYY-MM-DD)");
						Date departDate1 = Date.valueOf(myKeyboard2.nextLine());//Date variable to hold user input as to date
						
						
						long difference = departDate1.getTime()-arrivalDate1.getTime(); //Declaring and instantiating an object that subtracts dates entered
						int diffDays = (int)(difference/(24 * 60 * 60 * 1000));//Casting the long to an int doing the math to make it the difference in days
						System.out.println("The length of your desired stay comes to: " + diffDays + " days");
						
						//int confirmed = reservationsDAO.makeReservation(siteId1, name, arrivalDate1, departDate1);
						
//						List<Site> reservation2 = siteDAO.makeAReservation(siteId, arrivalDate, departDate); 
//						Site[] reservations3 = new Site [reservation2.size()];
//							
//							System.out.println("Here are the top 5 available sites for reservation: ");
//
//							for (int i = 0; i < reservation2.size(); i++) {
//								System.out.println(reservation2.get(i).toString());
//							}
							
					
						List <Campgrounds> fee = campgroundsDAO.getCampgroundFee(campId1, siteId1);
						String [] fee1 = new String [fee.size()];
						
						int nums2 = 0;
//						System.out.println("The daily fee for this campsite is: ");
						for(Campgrounds price: fee) {
							System.out.println("$"+(fee1[nums2] = price.getDaily_fee() + " is the Daily Fee"));
							String Y = price.getDaily_fee().toString();
							double yes = Double.parseDouble(Y);
							double total = (yes * diffDays);
							System.out.println("$" + total + " is the Total Fee");
//							int fee3 = Integer.parseInt(s)(fee1);
//							System.out.println(fee1[nums2].valueOf(fee1[nums2]) + "Total Fee");
//							int totalFee = 
							
							
						
						}
						
						
//						System.out.println("To continue your booking, please enter in the above daily fee: ");
//						String cost = myKeyboard2.nextLine(); //Variable to hold user selection
//						int cost1 = Integer.parseInt(cost);
//						System.out.println("Your total reservation cost comes to : $" + (cost1 * diffDays));
						

						
////						
						
//						List<Reservations> reservation = reservationsDAO.searchByReservationId(reservationID);//List containing all reservations based on reservation Id using our method
//						String[] reservations1 = new String [reservation.size() + 1]; //Declaring and Instantiating string to hold reservation info from list
//								
//								int num = 0; //loop variable
//								for (Reservations res : reservation) {//for loop
//									reservations1[num] = res.getReservationId() + " " + res.getReservationName() + " " + res.getFromDate() + " " + res.getToDate();
//									//Adds the information from the List to our String[] containing reservation ID, Name, From Date, To date
//									num++;//Update loop variable
//								}
//					
					
					
						//System.out.println("The cost of your stay is: " + (campgrounds.getDaily_fee().diffDays));
					
					
					//System.out.println("The cost of your stay will be: "  ); 
		
					
					
					 //Variable that holds users input for Site Id, name, arrival and departure dates
						
//						//String[] reserve1 = new String [reserve.size()];
//					
//						int number = 0;
//						for (Reservations res : reserve) {
//							reserve1[number] = res.getReservationName();
//						
						//}
						//System.out.println("The reservation has been made and the id is " + confirmed); //Print line the confirms reservation
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
