package reserver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Site {
	
	private int m_siteId;
	private int m_campgroundId;
	private int m_siteNumber;
	private int m_maxOccupancy;
	private int m_isAccessible;
	private int m_maxRvLength;
	private int m_utilities;
	
	public Site( ResultSet rs ) throws SQLException {
		try {
			this.m_siteId 		 = rs.getInt("site_id");
			this.m_campgroundId  = rs.getInt("campground_id");
			this.m_siteNumber	 = rs.getInt("site_number");
			this.m_maxOccupancy  = rs.getInt("max_occupancy");
			this.m_isAccessible  = rs.getInt("is_accessible");
			this.m_maxRvLength   = rs.getInt("max_rv_length");
			this.m_utilities     = rs.getInt("utilities");
			
		} finally {
			// ?
		}
	}
	
	public String getInfoLine() {
		return String.format("%d\t%d\t%s\t%s\t$%d", 
				this.m_siteNumber,
				this.m_maxOccupancy,
				(this.m_isAccessible > 0 ? "Yes" : "No"),
				(this.m_maxRvLength > 0 ? Integer.toString(this.m_maxRvLength) : "N/A"),
				this.m_utilities
		);
				
	}
}
