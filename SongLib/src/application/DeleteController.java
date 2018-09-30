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

public class DeleteController {
	
	@FXML
	private Button cancel;
	
	@FXML
	private Button delete; 
	
	ObservableList<Song> listOfSongs;
	int index;
	Song song;
	
	public void cancel(ActionEvent Event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("SongView2.fxml")); // dir of your .fxml file
		Parent SongView = loader.load();
		Scene SongViewscene = new Scene(SongView);
		Controller controller = loader.getController();
		Stage window = (Stage) ((Node)Event.getSource()).getScene().getWindow();
		if(index >= listOfSongs.size())
		{
			index = listOfSongs.size()-1;
		}
		controller.filewrite(listOfSongs);
		controller.restart(window, listOfSongs, index);
		window.setScene(SongViewscene);
		window.show();
		 
	}
	public void delete(ActionEvent Event) throws IOException{
		listOfSongs.remove(song);
		cancel(Event);
	}
	public void initSongList(ObservableList<Song> songs, Song song)
	{
		listOfSongs = songs;
		this.song = song;
		this.index = songs.indexOf(song);
	}

}
