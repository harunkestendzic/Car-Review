package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.PasswordEncryptionDecryption;
import database.DatabaseConnection;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import models.User;

public class UserDAOImp implements UserDAO{
	
	Connection conn = null;

	@Override
	public void register(User user) {
		conn = DatabaseConnection.ConnectDB();
		String query = "INSERT INTO user_account (firstname, lastname, username, password) VALUES (?,?,?,?);";
		
		
		try {	
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			String password_hashed = PasswordEncryptionDecryption.generateStrongPasswordHash(user.getPassword());
			
			preparedStmt.setString(1, user.getFirstname());
			preparedStmt.setString(2, user.getLastname());
			preparedStmt.setString(3, user.getUsername());
			preparedStmt.setString(4, password_hashed);
			
			preparedStmt.execute();
			
		
	
		}catch(Exception ex) {
		ex.printStackTrace();
		}
		
	}

	@Override
	public boolean login(String username, String password) {
		
		conn = DatabaseConnection.ConnectDB();
		
		String sql = "SELECT * FROM user_account WHERE username = '" + username+ "'";
		
		
		try {
			
			Statement statement = conn.createStatement();
			ResultSet queryResult = statement.executeQuery(sql);
				
				if(queryResult.next()) {
					
					String storedPassword = queryResult.getString("password");
					
					if(PasswordEncryptionDecryption.validatePassword(password, storedPassword)) {
						
						return true;
						
					}else {
				
						return false;
						
					}
					
				}
				
				
		}catch(Exception ex) {
			System.out.println("Exception in Login Controller "+ex);
		}
		return false;
	}

}
