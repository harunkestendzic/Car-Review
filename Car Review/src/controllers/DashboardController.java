package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Cars;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import dao.CarDAOImp;
import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class DashboardController implements Initializable{
	@FXML
	private TextField brandInput;
	@FXML
	private TextField modelInput;
	@FXML
	private TextField typeInput;
	@FXML
	private TextField colorInput;
	@FXML
	private Button insertButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button deleteButton;
	@FXML
	private TableView<Cars> CarsTable;
	@FXML
	private TableColumn<Cars,String> brandColumn;
	@FXML
	private TableColumn<Cars,String> modelColumn;
	@FXML
	private TableColumn<Cars,String> typeColumn;
	@FXML
	private TableColumn<Cars,String> colorColumn;
	
	Connection conn = null;

	@FXML
	public void insertButtonAction(ActionEvent event) {
		Cars car = new Cars();
		
		String brand = brandInput.getText();
		String model = modelInput.getText();
		String type = typeInput.getText();
		String color = colorInput.getText();
		
		car.setBrand(brand); 
		car.setModel(model);
		car.setType(type);
		car.setColor(color);
		
		CarDAOImp dao = new CarDAOImp();
		dao.save(car);
		
		showCars();
	}
	
	
	@FXML
	public void deleteButtonAction(ActionEvent event) {
		
		Cars selectedCar = CarsTable.getSelectionModel().getSelectedItem();
		
		CarDAOImp dao = new CarDAOImp();
		dao.delete(selectedCar);
		
		showCars();
	}
	
	
	
	@FXML
	public void updateButtonAction(ActionEvent event) {
	    // Get the selected car from the table
	    Cars selectedCar = CarsTable.getSelectionModel().getSelectedItem();

	    // Check if a car is selected
	    if (selectedCar != null) {
	        // Update the selected car with input values
	        selectedCar.setBrand(brandInput.getText());
	        selectedCar.setModel(modelInput.getText());
	        selectedCar.setType(typeInput.getText());
	        selectedCar.setColor(colorInput.getText()); 

	   
	        CarDAOImp dao = new CarDAOImp();
	        dao.update(selectedCar);

	    
	        showCars();
	    } else {
	      
	        System.out.println("Nijedno auto nije izabrano.");
	    }
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showCars();
		
		CarsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	Cars selectedCar = CarsTable.getSelectionModel().getSelectedItem();
		    	
		    	
		    	
		    	brandInput.setText(selectedCar.getBrand());
		    	modelInput.setText(selectedCar.getModel());
		    	typeInput.setText(selectedCar.getType());
		    	colorInput.setText(selectedCar.getColor());
		    	
		    

		    }
		});
	}	
	
	
	
	public void showCars() {
		CarDAOImp dao = new CarDAOImp();
		ObservableList<Cars> cars = dao.getCars();;
		
		brandColumn.setCellValueFactory(new PropertyValueFactory<Cars, String>("brand"));
		modelColumn.setCellValueFactory(new PropertyValueFactory<Cars, String>("model"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Cars, String>("type"));
		colorColumn.setCellValueFactory(new PropertyValueFactory<Cars, String>("color"));

		
		CarsTable.setItems(cars);

	}
	


	
}












