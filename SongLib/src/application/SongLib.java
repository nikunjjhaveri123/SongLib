//Nikunj Jhaveri and Louie Zhou
package application;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.Controller;

public class SongLib extends Application {

	private Stage mainStage;
	
	public void start(Stage stage) {
		mainStage = stage;
		mainStage.setTitle("Song library");  // title of window
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("SongView2.fxml")); // dir of your .fxml file
			AnchorPane pane = (AnchorPane)loader.load();
			
			Controller controller = loader.getController();
			controller.setMainStage(mainStage);
			controller.start(mainStage);
			Scene scene = new Scene(pane);
			
			mainStage.setScene(scene);
			mainStage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main(). it shouldn't even be called 
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}