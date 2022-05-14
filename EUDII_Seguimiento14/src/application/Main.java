package application;
	
import java.io.IOException;

import controller.ChronometerViewController;
import controller.ClockViewController;
import controller.OptionButtonsController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	//Attributes
	private Stage currentStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			showInitialView();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showInitialView() {
	
		
		try {
			
			if(currentStage != null) {currentStage.close();}
			
			FXMLLoader loader_OB = new FXMLLoader(getClass().getResource("../ui/OptionButtons.fxml"));
			
			BorderPane root_OB =(BorderPane)loader_OB.load();
			
			
			
			OptionButtonsController controller_OB = loader_OB.getController();
			controller_OB.setMain(this);
			
			Scene scene = new Scene(root_OB);
			Stage stage = new Stage();
			
			stage.setScene(scene);
			
			
			currentStage = stage;
			
			
			
			stage.show();
			 
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showChronometer() {
		try {

			FXMLLoader loader_CV = new FXMLLoader(getClass().getResource("../ui/ChronometerView.fxml"));
			
			BorderPane root_OB =(BorderPane)loader_CV.load();
			
			
			
			// scene = new Scene(root_OB);
			Stage stage = currentStage;
			
			ChronometerViewController  controller_CV = loader_CV.getController();
			 
			controller_CV.setMain(this);
			 
			BorderPane newRoot = (BorderPane)stage.getScene().getRoot();
			
			newRoot.setRight(root_OB);
			
			
			stage.show();
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showClock() {
		
		if(currentStage != null) {currentStage.close();}
		

		try {
			
			FXMLLoader loader_CV = new FXMLLoader(getClass().getResource("../ui/ClockView.fxml"));
			
			BorderPane root_OB =(BorderPane)loader_CV.load();
			
			
			
			// scene = new Scene(root_OB);
			Stage stage = currentStage;
			
			ClockViewController controller_CV = loader_CV.getController();
			 
			controller_CV.setMain(this);
			 
			BorderPane newRoot = (BorderPane)stage.getScene().getRoot();
			
			newRoot.setRight(root_OB);
			
			
			stage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
