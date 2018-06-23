package reserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestDB {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/ctrl?useSSL=false";
        String user = "parksvc";
        String password = "clowndix666";
        
        String query = "SELECT id, description FROM ctrl.test";
        String sqlInsert = "INSERT IGNORE INTO ctrl.test VALUES (?,?)";
        
        try (Connection con = DriverManager.getConnection(url, user, password)){
            
        	PreparedStatement pst = con.prepareStatement(sqlInsert);
        		pst.setInt(1, 6);
        		pst.setString(2, "FAYGOAL2");
        		pst.executeUpdate();
        	
    		Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
        	
            while (rs.next()) {
                
                System.out.println(rs.getString("id") + " " + rs.getString("description"));
            }
			
        } catch (SQLException ex) {
            
            Logger lgr = Logger.getLogger(TestDB.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } 
    }
}