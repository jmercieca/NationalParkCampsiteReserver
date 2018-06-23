package reserver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("HI~ WORLD");
		DatabaseDAO db = new DatabaseDAO();
		//List<Park> parks = new ArrayList<Park>();
		//try ( db.getAllParks(parks) ){
		try ( List<Park> parks = db.getAllParks() ){
			ListIterator<Park> parkIt = parks.listIterator();
			Park park = new Park();
			while(parkIt.hasNext()) {
				park = parkIt.next();
				System.out.printf("--------\n%s\n", park.getInfo());
			}
		} catch (SQLException ex) {       
			Logger lgr = Logger.getLogger(App.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		
		
	}

	
}
