package controllers;

import javafx.scene.Parent;
import java.sql.*;
import dao.UserDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




public class LogInController {
	
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private Label RegisterLabel;
	@FXML
	private Label XLabel;
	@FXML
	private Button CancelButton;
	@FXML
	private Label loginMessageLabel;
	
	Connection conn = null;
	
	
	
	@FXML
	public void OpenRegisterForm(MouseEvent event) {
		try {
			Stage stage = (Stage) RegisterLabel.getScene().getWindow();
			stage.close();
			Parent root = FXMLLoader.load(getClass().getResource("/views/Register.fxml"));
			Stage registerScene = new Stage();
			registerScene.initStyle(StageStyle.UNDECORATED);
			registerScene.setScene(new Scene(root, 426, 605));
			registerScene.show();
			registerScene.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	
	
	@FXML
	private void SignIn(ActionEvent event) throws Exception{
		
		
		
		if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false) {
			
			UserDAOImp dao = new UserDAOImp();
			
			if(dao.login(usernameTextField.getText(), passwordTextField.getText())){
				
				Stage stage = (Stage) XLabel.getScene().getWindow();
				stage.close();
				Parent root = FXMLLoader.load(getClass().getResource("/views/Dashboard.fxml"));
				Stage dashboardStage = new Stage();
				dashboardStage.setScene(new Scene(root,817,602));
				dashboardStage.show();
				
			}else {
				
				loginMessageLabel.setTextFill(Color.web("#ff0000"));
				loginMessageLabel.setText("Netačno korisničko ime ili šifra!");
				
			}
			
		
		}
		else {
			loginMessageLabel.setTextFill(Color.web("#ff0000"));
			loginMessageLabel.setText("Unesite korisničko ime i šifru.");
		}
	
	}
	
	@FXML
	public void XLabelClick(MouseEvent event) {
		Stage stage = (Stage) XLabel.getScene().getWindow();
		stage.close();
	}
		
	@FXML
	public void cancelButtonAction(ActionEvent event) {
		Stage stage = (Stage) CancelButton.getScene().getWindow();
		stage.close();

	}
	
}
