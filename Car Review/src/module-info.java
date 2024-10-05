module Projekat_Raspust {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
    opens controllers;
    opens models to javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
