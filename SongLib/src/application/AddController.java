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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {
	 
	@FXML
	private Button cancel;
	
	@FXML
	private Button confirm; 
	
	@FXML
	private TextField SongName;
	
	@FXML
	private TextField SongArtist;
	
	@FXML
	private TextField SongAlbum;
	
	@FXML
	private TextField SongYear;
	
	@FXML
	private Label error;
	
	private ObservableList<Song> listOfSongs;
	private int index;
	
	public void cancel(ActionEvent Event) throws IOException
	{ 
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("SongView2.fxml")); // dir of your .fxml file
		Parent SongView = loader.load();
		Scene SongViewscene = new Scene(SongView);
		Controller controller = loader.getController();
		Stage window = (Stage) ((Node)Event.getSource()).getScene().getWindow();
		controller.restart(window, listOfSongs, index);
		controller.filewrite();
		window.setScene(SongViewscene);
		window.show();
	}
	
	public boolean checkSong() throws IOException
	{
		String name;
		String artist;
		String album;
		String year;
		if(SongName.getText().trim().isEmpty())
		{
			error.setText("Please Enter a Song Name and Artist");
			return false;
		}
		else {
			name = SongName.getText();
		}
		
		if(SongArtist.getText().trim().isEmpty())
		{
			error.setText("Please Enter a Song Name and Artist");
			return false;
		}
		else {
			artist = SongArtist.getText();
		}
		
		if (SongAlbum.getText()==null)
		{
			album = " ";
		}
		else 
		{
			album = SongAlbum.getText();
		}
		
		if (SongYear.getText()==null)
		{
			year = " ";
		}
		else 
		{
			year = SongYear.getText();
		}
		
		Song add = new Song(name, artist, album, year);
		if(add.equals(add, listOfSongs))
		{
			error.setText("Error: A song by this artist already exists: Please enter a new song with a different name or artist.");
			return false;
		}
		add.insertSong(listOfSongs);
		this.index = listOfSongs.indexOf(add);
		return true;
		
	}
	
	//
	public void confirm(ActionEvent Event) throws IOException
	{
		if (checkSong() == true)
		{
			cancel(Event);
		}
		else
		{
			return;
		}
	}
	
	public void initSongList(ObservableList<Song> songs, Song s)
	{
		this.listOfSongs = songs;
		this.index = songs.indexOf(s);
	}
	 
	
	

}
