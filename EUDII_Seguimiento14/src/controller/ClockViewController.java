package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Lap;
import model.Zone;

public class ClockViewController implements Initializable {
	
	//Attributes
	private Main main;
	
	@FXML
	private Label timeNowLabel;
	
	@FXML
	private TableView<Zone> zonesTable;
	
	@FXML
	private TableColumn<Zone,String> zoneName;
	
	@FXML
	private TableColumn<Zone,LocalTime> localTimeZone;
	
	
	
	private ObservableList<Zone> aux;
	
	private volatile boolean stop = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		aux = FXCollections.observableArrayList();
		
		//
		zoneName.setCellValueFactory(new PropertyValueFactory<>("timeZone"));;
		localTimeZone.setCellValueFactory(new PropertyValueFactory<>("time"));
		//
		
		
		timeNow();
		timeZones();
	}
	
	private void timeZones() {
		new Thread(() -> {
			
			while(!stop) {
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			
				aux = main.returnZonesMain();
				
				Platform.runLater(() ->{
					zonesTable.setItems(aux);
						
				});
			}
			
		}).start();
	}
	
	private void timeNow() {
		
		new Thread(() ->{
			
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			SimpleDateFormat sdfd = new SimpleDateFormat("dd/MM/yy");
			
			final String dateNoW = sdfd.format(new Date());
			
			
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
	
	@FXML
	void addACountry(ActionEvent e) {
		main.showAddAZone();
	}
	
	public void setMain(Main main) {
		this.main = main;
	}

}
