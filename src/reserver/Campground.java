package reserver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Campground {
	
	private static int m_campgroundId;
	private static int m_parkId;
	private static String m_name;
	private static String m_startMonth;
	private static String m_endMonth;
	private static double m_fee;
	
	public Campground() {
		this.m_campgroundId = 0;
	}
	public Campground(ResultSet rs) throws SQLException {
		try {
			this.m_campgroundId  = rs.getInt("campground_id");
			this.m_parkId 		 = rs.getInt("park_id");
			this.m_name 		 = rs.getString("name");
			this.m_startMonth    = rs.getString("open_from_mm");
			this.m_endMonth      = rs.getString("open_to_mm");
			this.m_fee           = rs.getDouble("daily_fee");
		} finally {
			// ?
		}
	}
	
	public String getName() {
		return m_name;
	}
	
	public int getId() {
		return m_campgroundId;
	}
}
