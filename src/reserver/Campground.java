package reserver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;

import reserver.Park.Action;

public class Campground {
	
	private int m_campgroundId;
	private int m_parkId;
	private String m_name;
	private String m_openMonth;
	private String m_closeMonth;
	private double m_fee;
	
	public Campground() {
		this.m_campgroundId = 0;
	}
	public Campground(ResultSet rs) throws SQLException {
		try {
			this.m_campgroundId  = rs.getInt("campground_id");
			this.m_parkId 		 = rs.getInt("park_id");
			this.m_name 		 = rs.getString("name");
			this.m_openMonth     = rs.getString("open_from_mm");
			this.m_closeMonth    = rs.getString("open_to_mm");
			this.m_fee           = rs.getDouble("daily_fee");
		} finally {
			// ?
		}
	}
	
	public enum Action {
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
		actionList.add(Action.SEARCH);
		actionList.add(Action.BACK);
		return actionList;
	}
	
	public int getId() {
		return m_campgroundId;
	}
	
	public String getName() {
		return m_name;
	}
	
	public String getOpenMonth() {
		return m_openMonth;
	}
	
	public String getOpenMonthName() {
		return new DateFormatSymbols().getMonths()[Integer.valueOf(m_openMonth) - 1];
	}
	
	public String getCloseMonth() {
		return m_closeMonth;
	}
	
	public String getCloseMonthName() {
		return new DateFormatSymbols().getMonths()[Integer.valueOf(m_closeMonth) - 1];
	}
	
	public double getFee() {
		return m_fee;
	}
}
