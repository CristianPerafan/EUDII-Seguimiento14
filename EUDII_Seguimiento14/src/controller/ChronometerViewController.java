package controller;


import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Lap;


public class ChronometerViewController implements Initializable {
	
	//Attributes
	@SuppressWarnings("unused")
	private Main main;

	@FXML
	private Label minutesLabel,secondsLabel,thousandthsLabel;
	
	private int minutes,seconds,thousandths;
	
	private int identifier = 1;
	
	private volatile boolean stop = false;
	@SuppressWarnings("unused")
	private volatile boolean stopRefresh = false;
	
	@FXML
	private Button playButton;

	@FXML
	private Button flagButton;
	
	@FXML
	private Button restoreButton;
	
	@FXML
	private TableView<Lap> lapsTable;
	 
	@FXML
	private TableColumn<Lap,Integer> idColumn;
	 
	@FXML
	private TableColumn<Lap,String> lapTime;
	 
	@FXML
	private TableColumn<Lap,String> overallTimeLap;
	
	private ObservableList<Lap> aux;
	 
	 
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		toInitializeObservableList();

		//
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		lapTime.setCellValueFactory(new PropertyValueFactory<>("lapTime"));
		overallTimeLap.setCellValueFactory(new PropertyValueFactory<>("overallTime"));
		//
		
		
		restoreButton.setVisible(false);
		
		minutes = 0;
		seconds = 0;
		thousandths = 0;
		
	}
	
	public void toInitializeObservableList() {
		aux = FXCollections.observableArrayList();
	}
	
	
	private void addAMinute() {minutes++;}
	
	private void addASecond() {seconds++;}
	
	private void addAThousandth() {thousandths++;}
	
	private void resetMinuteS() {minutes = 0;}
	
	private void resetSeconds() {seconds = 0;}
	
	private void resethousandths() {thousandths = 0;}
	

	
	
	

	
	
	@FXML
	void toStart(ActionEvent e) {
		stop = false;
		stopRefresh = true;
		
		restoreButton.setVisible(false);
		
		toStartChronometer();
	
	}
	
	private void threadForTable() {
		new Thread(()->{
			
			for(int i = 0;i<1;i++) {
				Platform.runLater(() ->{
					lapsTable.setItems(aux);
						
				});
			}
		
		}).start();
	}
	

	
	private void toStartChronometer() {
		new Thread() {
			public void run() {
				for(;;) {
					if(!stop) {
						try {
							
							sleep(1);
							
							if(thousandths == 1000) {
								resethousandths();
								addASecond();
							}
							
							if(seconds == 60) {
								resethousandths();
								resetSeconds();
								addAMinute();
							}
							
							
							Platform.runLater(() ->{
								minutesLabel.setText(String.valueOf(minutes));
								secondsLabel.setText(String.valueOf(seconds));
								thousandthsLabel.setText(String.valueOf(thousandths));
							});
							
							
							
							
							addAThousandth();
							
						
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
					else {
						break;
					}
				}
			}
		}.start();
	}
	
	
	
	
	@FXML
	void toStop(ActionEvent e) {
		playButton.setVisible(true);
		restoreButton.setVisible(true);
		stop = true;
	}
	
	@FXML
	void toRestore(ActionEvent e) {
		
		toInitializeObservableList();
		threadForTable();
		
		
		resetMinuteS();
		resetSeconds();
		resethousandths();
		
		minutesLabel.setText(String.valueOf(minutes));
		secondsLabel.setText(String.valueOf(seconds));
		thousandthsLabel.setText(String.valueOf(thousandths));
		
		identifier = 1;
		
		
	}
	
	@FXML
	void toFlag(ActionEvent e) {
		
		
		
		boolean condition = false;
		
		if(minutes>0) {
			condition = true;
		}
		else if(seconds>0) {
			condition = true;
		}
		else if(thousandths>0) {
			condition = true;
		}
		
		if(condition == true) {
		
			if(identifier == 1) {
				
				Lap l = new Lap(identifier,minutes,seconds,thousandths,minutes,
						seconds,thousandths);
				
				aux.add(l);
			}
			else {
				int lapTimeMLast = aux.get(aux.size()-1).getOverallTimeMinutes();
				int lapTimeSLast = aux.get(aux.size()-1).getOverallTimeSeconds();
				int lapTimeMSLast = aux.get(aux.size()-1).getOverallTimeMiliseconds();

			
				int timeMS = 0;
				int timeS = 0;
				int timeM = 0;
				
				if(lapTimeMSLast<thousandths) {
				
					timeMS = lapTimeMSLast-thousandths;

				
					timeMS = timeMS*(-1);;
					
					
				}
				else if(lapTimeMSLast>thousandths) {
					timeMS = 1000-lapTimeMSLast;
				
					timeMS = timeMS+thousandths;
			
				}
				
				
				
				if(minutes>lapTimeMLast) {
				
					if(minutes-lapTimeMLast == 1) {
				
						if(seconds>lapTimeSLast) {
			
							timeM = minutes-lapTimeMLast;
							timeS = seconds-lapTimeSLast;
						}
						else if(seconds<lapTimeSLast) {
						
							
							timeM = 0;
							timeS = 60-(lapTimeSLast-seconds);
							
						}
						else {
						
							timeM = 1;
							timeS = 0;
						}
					}
					else {
						if(seconds>lapTimeSLast) {
					
							timeM = minutes-lapTimeMLast;
							timeS = seconds-lapTimeSLast;
						}
						else {
						
							timeM = minutes-1;
							timeS = 60- (lapTimeSLast-seconds);
						}
					}
					
				
				}
				else{
					
					timeS = seconds-lapTimeSLast;
					
					
				}
				
				
				
				
				
				Lap l = new Lap(identifier,minutes,seconds,thousandths,timeM,
						timeS,timeMS);
				
				aux.add(l);
			}
			
			identifier++;
			threadForTable();
		}
		else {
			
		}
		
	}
	
	

	
	public void setMain(Main main) {
		this.main = main;
	}
}
