package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OptionButtonsController implements Initializable{
	
	//Attributes
	private Main main;
	
	@FXML
	private Label timeNowLabel,dateNowLabel;
	
	@FXML
	private Button homeButton,clockButton,chronometerButton;
	

	
	/*
	Para Java, "volátil" le dice al compilador que el valor de una variable n
	unca debe almacenarse en caché, ya que su valor puede cambiar fuera del alcance 
	del programa en sí.
	*/
	
	private volatile boolean stop = false;
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		homeButton.setStyle("-fx-border-color : #AEDD00; -fx-background-color : TRANSPARENT;");

	
		timeNow();
		
		
	}
	
	@FXML
	void showHome(ActionEvent e) {
		stop = true;
		setSelectedButtonStyle(1); 
		main.showInitialView();
	}
	
	@FXML
	void showWatch(ActionEvent e) {
		stop = true;
		setSelectedButtonStyle(2); 
		main.showClock();
	}
	
	@FXML
	void showChronometer(ActionEvent e) {
		setSelectedButtonStyle(3);
		stop = true;
		main.showChronometer();
		 
	}
	
	private void timeNow() {
		
		new Thread(() ->{
			
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			SimpleDateFormat sdfd = new SimpleDateFormat("dd/MM/yy");
			
			final String dateNoW = sdfd.format(new Date());
			dateNowLabel.setText(dateNoW);
			
			while(!stop) {
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				
				final String timeNow = sdf.format(new Date());
				
				
				Platform.runLater(() ->{
					timeNowLabel.setText(timeNow);
				});
				
			
			
			}
			
			
			
		}).start();;
		
	}
	
	private void setSelectedButtonStyle(int option) {
		switch(option) {
		case 1:
			homeButton.setStyle("-fx-border-color : #AEDD00; -fx-background-color : TRANSPARENT;");
			
			//
			clockButton.setStyle("-fx-background-color : TRANSPARENT;");
			chronometerButton.setStyle("-fx-background-color : TRANSPARENT;");
			
			break;
		case 2:
			clockButton.setStyle("-fx-border-color : #AEDD00; -fx-background-color : TRANSPARENT;");
			
			//
			homeButton.setStyle("-fx-background-color : TRANSPARENT;");
			chronometerButton.setStyle("-fx-background-color : TRANSPARENT;");;
			
			break;
		case 3:
			chronometerButton.setStyle("-fx-border-color : #AEDD00; -fx-background-color : TRANSPARENT;");
			
			//
			homeButton.setStyle("-fx-background-color : TRANSPARENT;");
			clockButton.setStyle("-fx-background-color : TRANSPARENT;");
			
			break;
		default:
			break;
		}
	}
	
	

	public void setMain(Main main) {
		this.main = main;
	}
		
	

}
