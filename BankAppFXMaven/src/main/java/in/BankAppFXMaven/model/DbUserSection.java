package in.BankAppFXMaven.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUserSection {
	
	private SimpleDbConnection dbService;
	private static DbUserSection dbUserSection;
	
    private DbUserSection() {
    }
    
    public static DbUserSection getInstance() {
    	if(dbUserSection == null) {
    		dbUserSection = new DbUserSection();
    	}
    	return dbUserSection;
    }
    
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";

        try (Statement st = dbService.getConnection().createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                User newUser = new User();
				newUser.setId(rs.getInt("user_id"));
				newUser.setName(rs.getString("name"));
				newUser.setSurname(rs.getString("surname"));
				newUser.setEmail(rs.getString("email"));
				users.add(newUser);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbUserSection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }
    
    public User getUserInfo(String userID) {
    	String query = "SELECT "
    			+ "    User.email,"
    			+ "    User.name,"
    			+ "    User.surname,"
    			+ "    Bank_Account.account_number,"
    			+ "    Login.password"
    			+ "FROM "
    			+ "    user"
    			+ "JOIN "
    			+ "login ON user.user_id = login.user_id"
    			+ "JOIN "
    			+ "bank_Aaccount ON user.user_id = bank_account.user_id"
    			+ "WHERE "
    			+ "user.user_id = " + userID
    			+ ";";
    	User user = new User();
    	return user;
    }
}