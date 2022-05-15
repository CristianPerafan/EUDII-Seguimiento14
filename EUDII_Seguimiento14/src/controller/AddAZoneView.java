package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class AddAZoneView implements Initializable {
	
	//Attribute
	private Main main;
	
	@FXML
	private ComboBox<String> zoneId;
	
	private String[] zoneMap = {"Australia/Darwin","Australia/Sydney","America/Argentina/Buenos_Aires",
			"Africa/Cairo","America/Anchorage","America/Sao_Paulo","Asia/Dhaka","Africa/Harare",
			"America/St_Johns"," America/Chicago","Asia/Shanghai","Asia/Shanghai","Europe/Paris",
			"America/Indiana/Indianapolis","Asia/Kolkata","Asia/Tokyo","Pacific/Apia",
			" Asia/Yerevan","Pacific/Auckland","Asia/Karachi","America/Phoenix",
			"America/Puerto_Rico","America/Los_Angeles","Pacific/Guadalcanal"};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		zoneId.getItems().addAll(zoneMap);
	}
	
	@FXML
	void saveAZone(ActionEvent e) {
		main.addAZoneMain(zoneId.getValue());
		main.closeCurrent();
		
		
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	
	
	

}
