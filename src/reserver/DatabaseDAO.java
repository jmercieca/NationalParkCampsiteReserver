package reserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    public ArrayList<Campground> getCampgrounds(Park park) {
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
}
