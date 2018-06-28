package reserver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
	
	public static void main(String[] args) {
		System.out.println("HI WORLD");
		init();
		while(bCanContinue) {
			run();
		}
		System.out.println("BAIIIII");
	}
	
	
	private static DatabaseDAO db;
	private static ArrayList<Park> parkList;
	private static Scanner reader;
	private static boolean bCanContinue;
	
	//public Menu() {
	public static void init() {
		App.reader = new Scanner(System.in);
		App.db = new DatabaseDAO();
		App.parkList = db.getAllParks();
		App.bCanContinue = true;
	}

	public static void run() {

		Park park = getParkSelection();
		if(park == null) {
			App.bCanContinue = false;
			return;
		}
		
		
		while(bCanContinue) {	
			// PARK-LEVEL ACTIONS
			Park.Action action = getParkAction(park);	
			switch(action) {
				case VIEW:
					action = getCampgroundAction(park);
					break;
				case SEARCH:
					searchReservations(park, park.getParkPrompts()); // Park-wide Search
					break;
				case BACK:
					run();
					break;
			}
			
			// CAMPGROUND-LEVEL ACTIONS
			switch(action) {
				case SEARCH:
					displayCampgrounds(park, null);
					searchReservations(park, park.getCampgroundPrompts()); // 
					break;
				case BACK:
					// Let loop restart
					break;
			}
		}
		
	}
	
	public static Park getParkSelection() {
		while(true) {
			HashMap<String, Park> opts = displayParks();
			String userInput = getUserInput();
			if(opts.containsKey(userInput)) {
				Park park = opts.get(userInput);
				if(park != null) {
					System.out.println(park.getInfo());
				}
				return park;
			} else {
				System.out.println("Invalid option selected. Try again silly buns!");
			}
		}
	}
	
	public static Park.Action getParkAction(Park park) {
		while(true) {
			HashMap<String, Park.Action> optsMap  = displayParkActions(park.getActions());
			String userInput = getUserInput();
			if(optsMap.containsKey(userInput)) {
				return optsMap.get(userInput);
			} else {
				System.out.println("Invalid option selected. Try again silly buns!");
			}
		}
	}
	
	public static Park.Action getCampgroundAction(Park park) {
		while(true) {
			//HashMap<String, Campground> campgroundsMap = displayCampgrounds(park);
			displayCampgrounds(park, null);
			HashMap<String, Park.Action> optsMap = displayParkActions(park.getCampgroundActions());
			String userInput = getUserInput();
			if(optsMap.containsKey(userInput)) {
				return optsMap.get(userInput);
			} else {
				System.out.println("Invalid option selected. Try again silly buns!");
			}
		}
	}
	
	public static HashMap<String, Park> displayParks() {
		System.out.println("\nSelect a park:\n");
		HashMap<String, Park> optsMap = new HashMap<String, Park>();
		for(Park park : parkList) {
			System.out.printf("  %d) %s\n", park.getId(), park.getName());
			optsMap.put(Integer.toString(park.getId()), park);
		}
		System.out.println("  Q) Quit");
		optsMap.put("Q", null);
		return optsMap;
	}
	
	public static HashMap<String, Park.Action> displayParkActions(ArrayList<Park.Action> list) {
		System.out.println("\nSelect an action:");
		HashMap<String, Park.Action> optsMap = new HashMap<String, Park.Action>();
		int actionId = 1;
		for(Park.Action action : list) {
			System.out.printf("  %d) %s\n", actionId, action.getDescription());
			optsMap.put(Integer.toString(actionId), action);
			actionId++;
		}
		return optsMap;
	}
	
	public static HashMap<String, Campground> displayCampgrounds(Park park, ArrayList<Campground> inputCampgrounds) {
		System.out.printf("%s campgrounds:\n\n", park.getName());
		
		ArrayList<Campground> campgrounds = inputCampgrounds == null ? db.getAllCampgrounds(park) : inputCampgrounds;
		HashMap<String, Campground> optsMap = new HashMap<String, Campground>();
		System.out.printf(" %4s %-40.40s %-10.10s %-10.10s %s\n"," ","Name","Open","Close","Daily Fee");
		for(Campground campground : campgrounds) {
			System.out.printf("#%-4d %-40.40s %-10.10s %-10.10s $%.2f\n",
					campground.getId(),
					campground.getName(),
					campground.getOpenMonthName(),
					campground.getCloseMonthName(),
					campground.getFee()
			);	
			optsMap.put(Integer.toString(campground.getId()), campground);
		}
		return optsMap;
	}
	
	public static void searchReservations(Park park, ArrayList<Park.SearchPrompt> prompts) {
		HashMap<String, String> userResponses = new HashMap<String, String>();
		for(Park.SearchPrompt prompt : prompts) {
			String answer = promptUser(prompt);
			if((prompt == Park.SearchPrompt.CAMPGROUND) && (answer == "0")){
				return;
			}
			userResponses.put(prompt.getField(), answer);
		}
		ArrayList<Site> results = db.getAvailableSites(park, userResponses);
		displaySearchResults(results);
		
	}
	
	public static void displaySearchResults(ArrayList<Site> results) {
		System.out.println("Site No.\tMax Occup.\tAccessible?\tRV Len\tUtility Cost");
		for(Site site : results) {
			System.out.printf("%s\n", site.getInfoLine());
		}
	}
	
	public static String promptUser(Park.SearchPrompt prompt) {
		System.out.printf(" >>> %s : ", prompt.getPrompt());
		String in = reader.nextLine();
		System.out.print("\n");
		return in;
	}
	
	public static String getUserInput() {
		System.out.print("\n\n>>> ");
		String in = reader.nextLine(); 
		System.out.print("\n\n");
		return in;
	}
	
	public void showParks() {
		ArrayList<Park> parks = db.getAllParks();
		for(Park park : parks) {
			System.out.printf("--------\n%s\n", park.getInfo());
		}
	}
	
	
}



/*
public class App {
	public static void main(String[] args) {
		System.out.println("HI WORLD");
		DatabaseDAO db = new DatabaseDAO();
	
		try {
			ArrayList<Park> parks = db.getAllParks();
			for(Park park : parks) {
				System.out.printf("--------\n%s\n", park.getInfo());
			}
		} catch (SQLException ex) {       
			Logger lgr = Logger.getLogger(App.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
*/