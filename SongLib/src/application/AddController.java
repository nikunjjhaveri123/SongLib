package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddController {
	 
	@FXML
	private Button cancel;
	
	@FXML
	private Button Confirm;
	
	public void cancel(ActionEvent Event) throws IOException
	{
		Parent SongView = FXMLLoader.load(getClass().getResource("SongView2.fxml"));
		Scene SongViewscene = new Scene(SongView);
		Stage window = (Stage) ((Node)Event.getSource()).getScene().getWindow();
		window.setScene(SongViewscene);
		window.show();
		
		
	}
	

}
