package campground;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestDB {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/ctrl?useSSL=false";
        String user = "root";
        String password = "rothBard4!";
        
        String query = "SELECT id, description FROM ctrl.test";

        try (Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                
                System.out.println(rs.getString("id") + " " + rs.getString("description"));
            }

        } catch (SQLException ex) {
            
            Logger lgr = Logger.getLogger(TestDB.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } 
    }
}