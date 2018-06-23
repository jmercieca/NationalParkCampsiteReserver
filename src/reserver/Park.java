package reserver;

public class Park {
	
	private static int m_parkId;
	private static String m_name;
	private static String m_location;
	private static String m_establishDate;
	private static long m_area;
	private static long m_visitorCount;
	private static String m_description;
	private static String m_infoStr;
	
	//private List<Campground> m_campgrounds;
	public Park() {
		m_parkId = 0;
	}
	
	public Park( int parkId, 
				 String name, 
				 String location, 
				 String date, 
				 long area, 
				 long visitors,
				 String description ) 
	{
		m_parkId = parkId;
		m_name = name;
		m_location = location;
		m_establishDate = date;
		m_area = area;
		m_visitorCount = visitors;
		m_description = description;
		m_infoStr = String.format("%s [%d]\n", m_name, m_parkId)
		      + String.format("Location        : %s\n", m_location)
		      + String.format("Established     : %s\n", m_establishDate)
		      + String.format("Area            : %,d sq km\n", m_area)
		      + String.format("Annual Visitors : %d\n", m_visitorCount)
		      + String.format("\n%s\n", m_description);
			
	}
	
	public String getInfo() {
		return m_infoStr;
	}
}
