package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.Menu;

import Campgrounds.JDBCCampgroundsDAO;
import NationalParks.JDBCNationalParkDAO;
import NationalParks.NationalPark;

import java.util.List;
import java.util.Scanner;

public class CampgroundCLI {

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		JDBCNationalParkDAO parkDAO = new JDBCNationalParkDAO(dataSource);
		JDBCCampgroundsDAO groundsDAO = new JDBCCampgroundsDAO(dataSource);

		Menu appMenu = new Menu(System.in, System.out);

		CampgroundCLI application = new CampgroundCLI(appMenu);
		application.run(parkDAO);
	}

	// public String [] displayParkNames = parkDAO;
	private static final String MAIN_MENU_DISPLAY_PARKS = "Display list of parks";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_DISPLAY_PARKS, MAIN_MENU_OPTION_EXIT };

	private static final String PARK_INFO_MENU = "View Campgrounds";
	private static final String PARK_INFO_SEARCH = "Search for Reservation"; // will link to available reservation
	private static final String PARK_INFO_RETURN = "Return to Previous";
	private static final String[] PARK_INFO_OPTIONS = { PARK_INFO_MENU, PARK_INFO_SEARCH, PARK_INFO_RETURN };

	private Menu groundsMenu;
	private Menu parkMenu;

	public CampgroundCLI(Menu menu) {
		this.parkMenu = menu;
		this.groundsMenu = menu;
	}

	// public CampgroundCLI(DataSource datasource) {

	// create your DAOs here
	// }

	public void run(JDBCNationalParkDAO parkDAO) {
		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)parkMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_DISPLAY_PARKS:
					List<NationalPark> parkList = parkDAO.getAllParks();           // invoke method to display items in Vending Machine
					String[] parkNames = new String[parkList.size() + 1];
					int parkNum = 0;
					for(NationalPark aPark : parkList) {
						parkNames[parkNum] = aPark.getName();
						parkNum++;
					}
					parkNames[parkNum] = "Quit";
					choice = (String)parkMenu.getChoiceFromOptions(parkNames);
					
					List<NationalPark> parkInfo = parkDAO.searchByPark(choice);
					for(NationalPark park: parkInfo) {
						System.out.println(park.getName());
						System.out.println(park.getLocation());
						System.out.println(park.getEstablish_date());
						System.out.println(park.getArea());
						System.out.println(park.getVisitors());
						System.out.println(park.getDescription());
						
					}
					
					// Call another method (runCampgroundMenu) once the user has made a "choice"
					// 	- pass along the selected park
					// 


					
					break;                    // Exit switch statement
					
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false; 

					break;                    // Exit switch statement
			}	
		}return; // End method and return to caller

	}

	public static void endMethodProcessing() {
		System.out.println("Fuck off, Mate");
	}
}
