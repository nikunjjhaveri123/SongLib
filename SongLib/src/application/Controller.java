package application;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class Controller {
	
	Stage mainStage;
	
	public void setMainStage(Stage stage) {
		mainStage = stage;
	}
	
}

class Song {
	String name;
	String artist;
	String album;
	Integer year; 
	public Song(String name, String artist, String album, Integer year)
	{
		this.name = name;
		this.artist = artist; 
		this.album = album;
		this.year = year;
	}
	public Song(String name, String artist) {
		this(name, artist, "", null);
	}
	public Song(String name, String artist, String album) {
		this(name, artist, album, null);
	}
	public Song(String name, String artist, Integer year) {
		this(name, artist, "", year);
	}
	public boolean equals(Song one)
	{
		return false;
	}

}

