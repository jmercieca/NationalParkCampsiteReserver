package reserver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Park {

	private int m_parkId;
	private String m_name;
	private String m_location;
	private String m_establishDate;
	private long m_area;
	private long m_visitorCount;
	private String m_description;
	private String m_infoStr;
	//private List<Campground> m_campgrounds;
	
	
	public Park() {
		m_parkId = 0;
	}
	
	public Park( ResultSet rs ) throws SQLException {
		try {
			this.m_parkId 		 = rs.getInt("park_id");
			this.m_name 		 = rs.getString("name");
			this.m_location 	 = rs.getString("location");
			this.m_establishDate = rs.getString("establish_date");
			this.m_area          = rs.getLong("area");
			this.m_visitorCount  = rs.getLong("visitors");
			this.m_description   = rs.getString("description");
		} finally {
			// ?
		}
	}
	
	public Park( int parkId, 
				 String name, 
				 String location, 
				 String date, 
				 long area, 
				 long visitors,
				 String description ) 
	{
		this.m_parkId = parkId;
		this.m_name = name;
		this.m_location = location;
		this.m_establishDate = date;
		this.m_area = area;
		this.m_visitorCount = visitors;
		this.m_description = description;
	}
	

	public String getInfo() {
		return String.format("%s [%d]\n", this.m_name, m_parkId)
		     + String.format("Location        : %s\n", m_location)
		     + String.format("Established     : %s\n", m_establishDate)
		     + String.format("Area            : %,d sq km\n", m_area)
		     + String.format("Annual Visitors : %d\n", m_visitorCount)
		     + String.format("\n%s\n", m_description);	
	}
	
	public String getName() {
		return m_name;
	}
	
	public int getId() {
		return m_parkId;
	}
	
	public enum Action {
		VIEW("View Campgrounds"),
		SEARCH("Search for Reservation"),
		BACK("Return to Previous Screen");
		
		private final String description;
		Action(String desc) {
			this.description = desc;
		}
		public String getDescription() {
			return this.description;
		}
	}
	
	public ArrayList<Action> getActions() {
		ArrayList<Action> actionList = new ArrayList<Action>();
		actionList.add(Action.VIEW);
		actionList.add(Action.SEARCH);
		actionList.add(Action.BACK);
		return actionList;
	}
	
}
