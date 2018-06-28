package reserver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;



public class DatabaseDAO {
	
    private static final String URL      = "jdbc:mysql://localhost:3306/ctrl?autoReconnect=true&useSSL=false";
    private static final String USER     = "parksvc";
    private static final String PASSWORD = "clowndix666";
    
    private static Connection m_dbConn;
    private static Logger m_log;
    
    public DatabaseDAO() {
    	DatabaseDAO.m_log = Logger.getLogger(DatabaseDAO.class.getName());
    	initConn();
    }
    

	private static void initConn() {	
    	try {
    		if(DatabaseDAO.m_dbConn != null && DatabaseDAO.m_dbConn.isValid(10)) 
    			return;
    		DatabaseDAO.m_dbConn = DriverManager.getConnection(URL, USER, PASSWORD);
    		DatabaseDAO.m_log.log(Level.INFO, "CONNECTED TO DB");
    	} catch (SQLException ex) {            
    		DatabaseDAO.m_log.log(Level.SEVERE, ex.getMessage(), ex);
        } 	
    }
    
    public ArrayList<Park> getAllParks () {
  		initConn();
    	ArrayList<Park> parkList = new ArrayList<Park>(3);
    	String sql = "SELECT * FROM park";
    	try( 

    		Statement st = m_dbConn.createStatement(); ){
    		ResultSet rs = st.executeQuery(sql);
    		while(rs.next()) {
    			parkList.add( new Park(rs) );
    			DatabaseDAO.m_log.log( Level.INFO, String.format("#%d [%02d] %s", rs.getRow(), rs.getInt("park_id"), rs.getString("name")) );
    		}
    			
    	} catch (SQLException ex) {            
    		DatabaseDAO.m_log.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
        	
        }
    	
    	return(parkList);
    }
    
    public ArrayList<Campground> getAllCampgrounds(Park park) {
    	initConn();
    	ArrayList<Campground> campgrounds = new ArrayList<Campground>();
    	String sql = "SELECT * FROM campground WHERE park_id = ?";
    	try {
    		PreparedStatement pst = m_dbConn.prepareStatement(sql);
    		pst.setInt(1, park.getId());
    		ResultSet rs = pst.executeQuery();
    		while(rs.next()) {
    			campgrounds.add( new Campground(rs) );
    		}
    		
    	} catch (SQLException ex) {
    		DatabaseDAO.m_log.log(Level.SEVERE, ex.getMessage(), ex);
    	}
    	return campgrounds;
    }
    
    public ArrayList<Site> getAvailableSites(Park park, HashMap<String, String> fieldMap) {
    	initConn();
    	ArrayList<Site> results = new ArrayList<Site>();
    	SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Date toDate;
    	Date fromDate;
		try {
			fromDate = parser.parse(fieldMap.get("from_date").toString());
			toDate = parser.parse(fieldMap.get("to_date").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return results;
			
		}
		String fromDateStr = formatter.format(fromDate);
    	String toDateStr = formatter.format(toDate);
    	
    	String optWhere = "";
    	if(fieldMap.containsKey("campground_id")) {
    		optWhere = String.format( "AND c.campground_id = %d", Integer.parseInt(fieldMap.get("campground_id")) );
    	}
    	
    	
    	String sql = String.format(
    		 "SELECT DISTINCT s.* "
    		+ " FROM campground c "
    		+ " JOIN site s ON c.campground_id = s.campground_id "
    		+ " LEFT JOIN reservation r "
    		+ "       ON r.site_id = s.site_id"
    		+ "      AND (    r.from_date BETWEEN DATE('%s') AND DATE('%s')    "
    		+ "              OR r.to_date BETWEEN DATE('%s') AND DATE('%s')   )"
    		+ "WHERE c.park_id = %d"
    		+ "  AND c.open_from_mm <= MONTH('%s') AND c.open_to_mm >= MONTH('%s')"
    		+ "  AND r.site_id IS NULL"
    		+ "  %s", 
    		fromDateStr, toDateStr,
    		fromDateStr, toDateStr,
    		park.getId(),
    		fromDateStr, toDateStr,
    		optWhere
    	);
    	
    	try {
			Statement st = m_dbConn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				results.add( new Site(rs) );
			}
		} catch (SQLException ex) {
    		DatabaseDAO.m_log.log(Level.SEVERE, ex.getMessage(), ex);
    	}
    			
    	return results;
    }
}
