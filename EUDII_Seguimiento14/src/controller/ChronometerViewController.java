package controller;


import java.net.URL;
import java.util.ArrayList;
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
	
	@SuppressWarnings("unused")
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
		
		threadForMinutes();
		threadForSeconds();
		threadForThousandths();

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
	
	private void threadForThousandths() {
		new Thread(()->{
			
				while(!stop) {
				
					try {
						Thread.sleep(1);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if(thousandths == 100) {
						resethousandths();
					}
					
					
					addAThousandth();
					
					
					Platform.runLater(() ->{
						thousandthsLabel.setText(String.valueOf(thousandths));
						
					});
					
				}
		}).start();
	}
	
	//Segundo -> 1000
	//Minuto -> 60000
	
	private void threadForSeconds() {
		new Thread(()->{
			
			while(!stop) {
				
				try {
					Thread.sleep(1000);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(seconds == 60) {
					resetSeconds();
				}
				
				addASecond();
				
				Platform.runLater(() ->{
					secondsLabel.setText(String.valueOf(seconds));
					
				});
				
				
				
			}
			
		}).start();
	}
	
	
	private void threadForMinutes() {
		new Thread(()->{
			
			while(!stop) {
				
				try {
					Thread.sleep(60_000);
					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				
				addAMinute();
				
				Platform.runLater(() ->{
					minutesLabel.setText(String.valueOf(minutes));
				});
			}
			
			
			
		}).start();;
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
				System.out.println("Entro first");
				Lap l = new Lap(identifier,minutes,seconds,thousandths,minutes,
						seconds,thousandths);
				
				aux.add(l);
			}
			else {
				int lapTimeMLast = aux.get(aux.size()-1).getOverallTimeMinutes();
				System.out.println(lapTimeMLast);
				int lapTimeSLast = aux.get(aux.size()-1).getOverallTimeSeconds();
				System.out.println(lapTimeSLast);
				int lapTimeMSLast = aux.get(aux.size()-1).getOverallTimeMiliseconds();
				System.out.println("ls-->ms: "+lapTimeMSLast);
				System.out.println("thousandths: "+thousandths);
				
				int timeMS = 0;
				if(lapTimeMSLast<thousandths) {
					System.out.println("Entro menor");
					timeMS = lapTimeMSLast-thousandths;
					System.out.println("Resta: "+timeMS);
					int aux = timeMS;
					timeMS = aux+(aux*2);
					
				}
				else if(lapTimeMSLast>thousandths) {
					timeMS = 100-thousandths;
					System.out.println("Entro mayor");
					timeMS = timeMS+lapTimeMSLast;
				}
				
				
				
				
				Lap l = new Lap(identifier,minutes,seconds,thousandths,minutes-lapTimeMLast,
						seconds-lapTimeSLast,timeMS);
				
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
