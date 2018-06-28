package reserver;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class Menu {
	/*********
	private DatabaseDAO m_db;
	private ArrayList<Park> m_parkList;
	//private Scanner in;
	private Scanner m_reader;
	private boolean m_bContinue;
	
	public Menu() {
		this.m_reader = new Scanner(System.in);
		this.m_db = new DatabaseDAO();
		this.m_parkList = m_db.getAllParks();
		this.m_bContinue = true;
	}
	
	public boolean canContinue() {
		return m_bContinue;
	}
	
	public String getUserInput() {
		System.out.print("\n\n>>> ");
		String in = m_reader.nextLine(); 
		System.out.print("\n\n");
		return in;
		// return in.nextLine();
	}

	public void start() {

		Park park = getParkSelection();
		if(park == null) {
			this.m_bContinue = false;
			return;
		}
		
		Park.Action action = getParkAction(park);
		switch(action) {
			case VIEW:
				viewCampgrounds(park);
				break;
			case SEARCH:
				searchReservations(park);
				break;
			case BACK:
				start();
				break;
		}
	}
	
	//public Park getParkSelection(HashMap<String, Park> opts) {
	public Park getParkSelection() {
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
	
	public HashMap<String, Park> displayParks() {
		System.out.println("\nSelect a park:\n");
		HashMap<String, Park> optsMap = new HashMap<String, Park>();
		int choiceId = 1;
		for(Park park : m_parkList) {
			System.out.printf("  %d) %s\n", choiceId, park.getName());
			optsMap.put(Integer.toString(choiceId), park);
			choiceId++;
		}
		System.out.println("  Q) Quit");
		optsMap.put("Q", null);
		return optsMap;
	}
	
	public Park.Action getParkAction(Park park) {
		while(true) {
			HashMap<String, Park.Action> optsMap  = displayParkActions(park);
			String userInput = getUserInput();
			if(optsMap.containsKey(userInput)) {
				return optsMap.get(userInput);
			} else {
				System.out.println("Invalid option selected. Try again silly buns!");
			}
		}
	}
	public HashMap<String, Park.Action> displayParkActions(Park park) {
		System.out.println("Select an action:");
		ArrayList<Park.Action> list = park.getActions();
		HashMap<String, Park.Action> optsMap = new HashMap<String, Park.Action>();
		int actionId = 1;
		for(Park.Action action : list) {
			System.out.printf("  %d) %s\n", actionId, action.getDescription());
			optsMap.put(Integer.toString(actionId), action);
			actionId++;
		}
		return optsMap;
	}
	
	
	public void viewCampgrounds(Park park) {
		System.out.println("*** viewCampgrounds ***");
		this.m_bContinue = false;
	}
	public void searchReservations(Park park) {
		System.out.println("*** searchReservations() ***");
		this.m_bContinue = false;
	}
	
	public void showParks() {
		ArrayList<Park> parks = m_db.getAllParks();
		for(Park park : parks) {
			System.out.printf("--------\n%s\n", park.getInfo());
		}
	}
	
	public void showCampsites(Park park) {
		
	}
	***************************/
	
	/**
	public void displayOptions(ArrayList optsList, String userInstructions) {
		System.out.println(userInstructions);
		for(Object opt : optsList) {
			switch opt.getClass() {
			}
			System.out.printf("  %d) %s", opt.getId(), opt.getName());
		}
	}
	**/
}
