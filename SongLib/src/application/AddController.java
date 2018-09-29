package application;

import java.io.IOException;

import javafx.collections.ObservableList;
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
	
	ObservableList<Song> listOfSongs;
	
	public void cancel(ActionEvent Event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("SongView2.fxml")); // dir of your .fxml file
		Parent SongView = loader.load();
		Scene SongViewscene = new Scene(SongView);
		Controller controller = loader.getController();
		Stage window = (Stage) ((Node)Event.getSource()).getScene().getWindow();
		controller.restart(window, listOfSongs);
		window.setScene(SongViewscene);
		window.show();
		
	}
	
	public void initSongList(ObservableList<Song> songs)
	{
		listOfSongs = songs;
	}
	

}
