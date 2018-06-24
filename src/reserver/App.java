package reserver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
	public static void main(String[] args) {
		System.out.println("HI WORLD");
		Menu menu = new Menu();
		menu.start();
		while(menu.canContinue()) {
			menu.start();
		}
		System.out.println("BAIIIII");
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