package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.User;
import javafx.scene.control.PasswordField;

import java.sql.Connection;
import java.sql.Statement;

import dao.UserDAOImp;
import database.DatabaseConnection;

public class RegisterController {
	
	@FXML
	private TextField FirstnameTextField;
	@FXML
	private TextField LastnameTextField;
	@FXML
	private TextField UsernameTextField;
	@FXML
	private Label SignInLabel;
	@FXML
	private Label XLabel;
	@FXML
	private Button CloseButton;
	@FXML
	private Label InformationLabel;
	@FXML
	private Button SignUpButton;
	@FXML
	private PasswordField PasswordField;
	@FXML
	private PasswordField ConfirmPasswordField;
	
	Connection conn = null;
	
	
	public void SignUpButtonAction(ActionEvent event) {
		
		if(FirstnameTextField.getText().isBlank() == false && LastnameTextField.getText().isBlank() == false && UsernameTextField.getText().isBlank() == false
				&& PasswordField.getText().isBlank() == false && ConfirmPasswordField.getText().isBlank() == false
				) {
			
			if(PasswordField.getText().equals(ConfirmPasswordField.getText())) {
				
				User user = new User();	
				 
				user.setFirstname(FirstnameTextField.getText());
				user.setLastname(LastnameTextField.getText());
				user.setUsername(UsernameTextField.getText());
				user.setPassword(PasswordField.getText());
				
	
				UserDAOImp dao = new UserDAOImp();
				dao.register(user);
				
				FirstnameTextField.setText("");
				LastnameTextField.setText("");
				UsernameTextField.setText("");
				PasswordField.setText("");
				ConfirmPasswordField.setText("");
				
				
			}else {
				InformationLabel.setTextFill(Color.web("#ff0000"));
				InformationLabel.setText("Šifra se ne podudara!");
			}
			
		}else {
			InformationLabel.setTextFill(Color.web("#ff0000"));
			InformationLabel.setText("Popunite prazna polja!");
		}
		
		
	}
	
	
	public void OpenLogInForm(MouseEvent event) {
		try {
			Stage stage = (Stage) SignInLabel.getScene().getWindow();
			stage.close();
			Parent root = FXMLLoader.load(getClass().getResource("/views/LogIn.fxml"));
			Stage LogInScene = new Stage();
			LogInScene.initStyle(StageStyle.UNDECORATED);
			LogInScene.setScene(new Scene(root, 523,448));
			LogInScene.show();
			LogInScene.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void CloseButtonAction(ActionEvent event) {
		Stage stage = (Stage) CloseButton.getScene().getWindow();
		stage.close();
		System.out.println("Zatvoreno!");
	}
	
	@FXML
	public void XLabelClick(MouseEvent event) {
		Stage stage = (Stage) XLabel.getScene().getWindow();
		stage.close();
	}
	

}
