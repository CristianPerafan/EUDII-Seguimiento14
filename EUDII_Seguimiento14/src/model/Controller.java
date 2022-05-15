package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {
	
	private ObservableList<Zone> aux;
	
	public Controller() {
		aux = FXCollections.observableArrayList();
		
	}
	
	
	public void addAZoneController(String zoneId) {
		
		Zone z = new Zone(zoneId);
		
		aux.add(z);
	
	}
	
	public ObservableList<Zone> returnZones(){
		
		for(int i = 0;i<aux.size();i++) {
			Zone temp = new Zone(aux.get(i).getTimeZone());
			
			aux.set(i, temp);
		}
		return aux;
	}
}
