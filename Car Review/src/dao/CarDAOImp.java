package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Cars;

public class CarDAOImp implements CarDAO{
	
	
	Connection conn = null;

	@Override
	public void update(Cars car) {
		conn = DatabaseConnection.ConnectDB();
		String query = "UPDATE cars SET brand = ?, model = ?, type = ?, color = ? WHERE id = ?";
		
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			
			preparedStmt.setString(1, car.getBrand());
			preparedStmt.setString(2, car.getModel());
			preparedStmt.setString(3, car.getType());
			preparedStmt.setString(4, car.getColor());
			preparedStmt.setInt(5, car.getId());
			
			preparedStmt.executeUpdate();
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}	
		
	}

	@Override
	public void save(Cars car) {
		conn = DatabaseConnection.ConnectDB();
		String query = "INSERT INTO cars (brand, model, type, color) VALUES (?,?,?,?);";
		
		
		try {	
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			preparedStmt.setString(1, car.getBrand());
			preparedStmt.setString(2, car.getModel());
			preparedStmt.setString(3, car.getType());
			preparedStmt.setString(4, car.getColor());
			
			preparedStmt.execute();
			
		
	
		}catch(Exception ex) {
		ex.printStackTrace();
		}
		
	}
	
	
	public ObservableList<Cars> getCars(){
		ObservableList<Cars> listOfBooks = FXCollections.observableArrayList();
		conn = DatabaseConnection.ConnectDB();	
		String query = "SELECT * FROM cars";
		Statement statement;
		ResultSet queryResult;
		
		try {
			statement = conn.createStatement();
			queryResult = statement.executeQuery(query);
			Cars cars;
			while(queryResult.next()) {
				cars = new Cars(queryResult.getInt("id"), queryResult.getString("brand"),
						          queryResult.getString("model"),queryResult.getString("type"),
						          queryResult.getString("color"));
					
				listOfBooks.add(cars);
			}	
		}catch(Exception ex) {
		ex.printStackTrace();
		}
		
		return listOfBooks;
	}
	
	
	@Override
	public void delete(Cars car) {
		conn = DatabaseConnection.ConnectDB();
		
		String query = "DELETE FROM cars where id = ?";
		
		try {	
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			preparedStmt.setInt(1, car.getId());
			
			preparedStmt.execute();
				
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
