package com.techelevator;

import java.sql.Date;
import java.text.SimpleDateFormat;
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

	private static final String MAIN_MENU_DISPLAY_PARKS = "Explore Parks";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_DISPLAY_PARKS, MAIN_MENU_OPTION_EXIT };

	private static final String PARK_INFO_MENU = "View Campgrounds For This Park";
	private static final String RESERVATION_SEARCH = "Check Your Existing Reservation";
	private static final String SITE_SEARCH = "Check Available Sites";
	private static final String MAKE_RESERVATION = "Make A Reservation";
	private static final String PARK_INFO_RETURN = "Exit";
	private static final String[] PARK_INFO_OPTIONS = { PARK_INFO_MENU, RESERVATION_SEARCH, SITE_SEARCH,
			MAKE_RESERVATION, PARK_INFO_RETURN };

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

		BasicDataSource dataSource = new BasicDataSource(); // Connecting to our database
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		campgroundsDAO = new JDBCCampgroundsDAO(dataSource); // Object to access our Campgrounds data
		nationalparkDAO = new JDBCNationalParkDAO(dataSource); // Object to access our NationalPark data
		reservationsDAO = new JDBCReservationsDAO(dataSource); // Object to access Reservations data
		siteDAO = new JDBCSiteDAO(dataSource); // Object to access Site data

	}

	public void run() {
		boolean shouldProcess = true; // Loop control variable
		while (shouldProcess) { // Loop until user indicates they want to exit

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS); // Display menu and get choice

			switch (choice) { // Process based on user menu choice

			case MAIN_MENU_DISPLAY_PARKS:
				List<NationalPark> parkList = nationalparkDAO.getAllParks(); // accessing data source + method to get
																				// all the parks
				String[] parkNames = new String[parkList.size() + 1]; // String array for all the park info, +1 for
																		// "Quit" option
				int parkNum = 0; // counter for the for loop
				for (NationalPark aPark : parkList) {// for loop to get all the park names from the List
					parkNames[parkNum] = aPark.getName(); // putting all of the names in the parkNames string
					parkNum++; // iterate to the next item in the list
				}
				parkNames[parkNum] = "Quit"; // reason we added + 1 in our array
				choice = (String) menu.getChoiceFromOptions(parkNames); // updating the value of choice

				List<NationalPark> parkInfo = nationalparkDAO.searchByPark(choice); // user's choice to bring up all of
																					// the park info
				for (NationalPark park : parkInfo) { // iterate through the parkInfo list
					System.out.println(park.getName() + " National Park"); // prints name of park
					System.out.println("Location: " + park.getLocation()); // prints location of park
					System.out.println("Established: " + park.getEstablish_date()); // prints established date of park
					System.out.println("Area: " + park.getArea()); // prints area of park
					System.out.println("Annual Visitors: " + park.getVisitors()); // prints annual visitors of the park
					System.out.println();// prints a blank line
					System.out.println(park.getDescription());// prints description
				}

				System.out.println();
				System.out.println("Select a command:");

				boolean shouldProcess1 = true; // Loop control variable
				while ((shouldProcess1)) { // Loop until user wants to exit
					String choice1 = (String) menu.getChoiceFromOptions(PARK_INFO_OPTIONS); // Variable to hold user
																							// selection

					switch (choice1) { // Switch statement based on user selection
					case PARK_INFO_MENU:

						List<Campgrounds> groundID = campgroundsDAO.getCampgroundById(parkInfo.get(0).getPark_id());
						String[] campGround = new String[groundID.size() + 1]; // instantiate new String[] to hold the
																				// info from the list

						int groundNum1 = 0;// Loop variable
						for (Campgrounds ground : groundID) {// for loop
							
							
							campGround[groundNum1] =  ground.getCampground_id() + " | " + ground.getName() + " | "
									+ ground.getOpen_from_mm() + " | " + ground.getOpen_to_mm() + " |  $"
									+ ground.getDaily_fee() + "0";
							// Putting the campgroundId, name, open from date, to date and daily free from
							// campgrounds with selected campground ID
							groundNum1++;// Update loop variable
						}
						System.out.println();
						System.out.println("--------------------CAMPGROUND INFORMATION--------------------");
						System.out.println();
						System.out.println(" (CAMP ID, NAME, OPEN FROM(MONTH), OPEN UNTIL(MONTH), FEE(PER DAY)");
						campGround[groundNum1] = "Return To Make A Reservation";
						choice1 = (String) menu.getChoiceFromOptions(campGround); // Update value of user's choice

						break;

					case RESERVATION_SEARCH:

						Scanner myKeyboard = new Scanner(System.in); // Declare and instantiate a scanner to read user
																		// input
						System.out.println("What is your existing reservation ID?");
						String reservation1 = myKeyboard.nextLine();
						int reservationID = Integer.parseInt(reservation1); // Parsing the string entered into an int

						List<Reservations> reservation = reservationsDAO.searchByReservationId(reservationID);
						String[] reservations1 = new String[reservation.size() + 1]; // declaring and instantiating
																						// string to hold reservation
																						// info from list

						int num = 0; // loop variable
						for (Reservations res : reservation) {
							reservations1[num] = res.getReservationId() + " " + res.getReservationName() + " "
									+ res.getFromDate() + " " + res.getToDate();
							num++;
						}

						reservations1[num] = "Return To Make A Reservation";
						choice1 = (String) menu.getChoiceFromOptions(reservations1);// updating value of user choice
																					// variable
						break;

					case SITE_SEARCH:
						
						
						
						Scanner myKeyboard1 = new Scanner(System.in); // new scanner object to read user input

						System.out.println("What is the camp ID you'd like to check?");
						String id = myKeyboard1.nextLine(); // Variable to hold user input
						int siteId = Integer.parseInt(id); // Parsing user's input from string to int

						System.out.println("What is your arrival date? (YYYY-MM-DD)");
						String ugh = myKeyboard1.nextLine();
						String date10 = ugh.substring(5, 7);
						int dateArr = Integer.parseInt(date10);
						
						
						System.out.println("What is your departure date? (YYYY-MM-DD)");
						String ugh1 = myKeyboard1.nextLine();
						String date11 = ugh1.substring(5, 7);
						int dateArr1 = Integer.parseInt(date11);
						
							
						
						if ((siteId == 2) && ((dateArr < 5) || (dateArr1 > 9))) {
								System.out.println("There aren't any available sites for these dates. Please try another date!");
						} if ((siteId == 3) && ((dateArr < 5) || (dateArr1 > 10))) {
							System.out.println("TThere aren't any available sites for these dates. Please try another date!");
						} if ((siteId == 7) && ((dateArr < 5) || (dateArr1 > 11))) {
							System.out.println("TThere aren't any available sites for these dates. Please try another date!");
						}
						
						
						
						
							Date arrivalDate = Date.valueOf(ugh);
							Date departDate = Date.valueOf(ugh1);

						List<Site> reservation2 = siteDAO.sitesAvailable(siteId, arrivalDate, departDate);
						System.out.println();
						
						System.out.println("------------------------------TOP AVAILABLE SITES------------------------------");
						System.out.println();
						System.out.println("CAMP ID |  SITE # |MAX OCCUPANCY|     HANDICAP    | MAX RV LENGTH |   UTILITIES");
						System.out.println("--------------------------------------------------------------------------------");
						for (int i = 0; i < reservation2.size(); i++) {
							System.out.println(reservation2.get(i).toString());
						}
						
						

					case MAKE_RESERVATION:
						System.out.println();
						Scanner myKeyboard2 = new Scanner(System.in);
						System.out.println("Would you like to make a reservation? (Y) or (N)");
						String yesNo = myKeyboard2.nextLine(); // Variable to hold user selection

						if (yesNo.equalsIgnoreCase("N")) {
							endMethodProcessing();
						} else if (yesNo.equalsIgnoreCase("Y")) {
							System.out.println("Enter the camp ID you'd like to reserve.");
							String campId = myKeyboard2.nextLine();
							int campId1 = Integer.parseInt(campId);
							System.out.println("Enter the site ID you'd like to reserve.");
							String id1 = myKeyboard2.nextLine();
							int siteId1 = Integer.parseInt(id1);// Parsing user selection from String to int
							System.out.println("What name would you like on the reservation?");
							String name = myKeyboard2.nextLine();
							System.out.println("What is your arrival date? (YYYY-MM-DD)");
							String date1 = myKeyboard2.nextLine();
							
//							String date10 = date1.substring(5, 7);
//							
//							
//							int dateArr = Integer.parseInt(date10);
//							
//							
//							if (dateArr < 5) {
//								System.out.println("this worked!");
//							}
							
							Date arrivalDate1 = Date.valueOf(date1); // user's from_date
							System.out.println("What is your departure date? (YYYY-MM-DD)");
							Date departDate1 = Date.valueOf(myKeyboard2.nextLine()); // user's to_date
							

							long difference = departDate1.getTime() - arrivalDate1.getTime();
							int diffDays = (int) (difference / (24 * 60 * 60 * 1000)); // converting difference through
																						// milliseconds
							
						
							
							System.out.println("The length of your desired stay comes to " + diffDays + " days.");
							System.out.println();

							int confirmed = reservationsDAO.makeReservation(siteId1, name, arrivalDate1, departDate1);

							List<Campgrounds> fee = campgroundsDAO.getCampgroundFee(campId1, siteId1);
							String[] fee1 = new String[fee.size()];

							int nums2 = 0;
							System.out.println("----------------------------------------");
							for (Campgrounds price : fee) {
								System.out.println("$" + (fee1[nums2] = price.getDaily_fee() + "0" + " is the daily fee."));
								String Y = price.getDaily_fee().toString();
								double yes = Double.parseDouble(Y);
								double total = (yes * diffDays);
								
								System.out.println("$" + total + "0" + " is the total fee.");
								System.out.println();
								System.out.println("Pack your bags - you're going camping! Your reservation ID is " + confirmed + ".");
								displayPrettyPicture();
								

								
							}
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

	public static void endMethodProcessing() {
		System.out.println("It's been real. Goodbye!");
	}
	

	
	private void displayPrettyPicture() {
System.out.println();
System.out.println("       ______");    
System.out.println("      /     /\\"); 
System.out.println("     /     /  \\");
System.out.println("    /     /----\\_");
System.out.println("    \"     \"          ).  ");
System.out.println("   _ ___          o (:') o  "); 
System.out.println("  (@))_))        o ~/~~\\~ o  "); 
System.out.println("                  o  o  o	");
System.out.println();
}
}