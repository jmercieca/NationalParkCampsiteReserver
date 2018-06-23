package reserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;



public class DatabaseDAO {
	
    private static final String URL      = "jdbc:mysql://localhost:3306/ctrl?useSSL=false";
    private static final String USER     = "parksvc";
    private static final String PASSWORD = "clowndix666";
    
    private Connection m_dbConn;
    private Logger m_log = Logger.getLogger(DatabaseDAO.class.getName());
    
    
    public DatabaseDAO() {
    	initConn();
    }
    
    private void initConn() {
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
    		this.m_dbConn = conn;
    		m_log.log(Level.INFO, "CONNECTED TO DB");
    	} catch (SQLException ex) {            
            m_log.log(Level.SEVERE, ex.getMessage(), ex);
        } 	
    }
    
    public Connection getConn() {
    	return m_dbConn;
    }
    
    public List<Park> getAllParks () throws SQLException {
    //public void getAllParks (List<Park> parkList) throws SQLException {
    	if(m_dbConn.isClosed()) {
    		initConn();
    	}
    	List<Park> parkList = new ArrayList<Park>();
    	//parkList = new ArrayList<Park>();
    	String sql = "SELECT * FROM parks";
    	try(PreparedStatement pst = m_dbConn.prepareStatement(sql);
    		ResultSet rs = pst.executeQuery()) {
    		while(rs.next()) {
    			Park park = new Park(
    					rs.getInt("park_id"),
    					rs.getString("name"),
    					rs.getString("location"),
    					rs.getString("establish_date"),
    					rs.getLong("area"),
    					rs.getLong("visitors"),
    					rs.getString("description")
    			);
    			parkList.add(park);
    		}
    			
    	} catch (SQLException ex) {            
            m_log.log(Level.SEVERE, ex.getMessage(), ex);
        } 
    	
    	return parkList;
    	
    }
}
