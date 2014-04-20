package Antik;


import Antik.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class View extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			AnchorPane page = (AnchorPane) FXMLLoader.load(View.class
					.getResource("GUI.fxml"));
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.setTitle("VIEW");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Controller.initCombos();
	}
	
	
	 public static void main(String[] args) {
		 	TitulManager.pripojDB();
			
		  launch(args);
//		  	TitulManager.odpojDB();
		 }

	
}
