package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.Initializable;

public class ClockViewController implements Initializable {
	
	//Attributes
	@SuppressWarnings("unused")
	private Main main;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setMain(Main main) {
		this.main = main;
	}

}
