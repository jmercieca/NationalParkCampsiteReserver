package reserver;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Reservation {
	
	private static int m_reservationId;
	private static int m_siteId;
	private static String m_name;
	private static String m_fromDate;
	private static String m_toDate;
	private static String m_createDate;
	
	public Reservation() {
		m_reservationId = 0;
		m_siteId = 0;
		m_name = "";
		m_fromDate = "N/A";
		m_toDate = "N/A";
		m_createDate = "N/A";
	}
	
	public Reservation( int reservationId,
						int siteId,
						String name,
						String fromDate,
						String toDate,
						String createDate ) 
	{
		m_reservationId = reservationId;
		m_siteId = siteId;
		m_name = name;
		m_fromDate = fromDate;
		m_toDate = toDate;
		m_createDate = createDate;
	}
	
	public void create(int reservationId, String fromDate, String toDate) {
		m_reservationId = reservationId;
		m_fromDate = fromDate;
		m_toDate = toDate;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		m_createDate = dateFormat.format(date);
	}
	

	public int getId() {
		return m_reservationId;
	}
	
	public int getSiteId() {
		return m_siteId;
	}
}
