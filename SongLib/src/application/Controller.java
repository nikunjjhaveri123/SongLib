package application;

import java.util.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Controller {
	
	Stage mainStage;
	
	ListView<Song> listview;
	
	ObservableList<Song> listOfSongs = FXCollections.observableArrayList();
	
	final static String fileName = "application/songlist.txt";
	
	public void setMainStage(Stage stage) {
		mainStage = stage;
	}
	
	//Reads a file and returns and ObservableList of Songs that can be used in the listview
	public ObservableList<Song> fileRead(String fileName)
	{
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
	
			String line;
			while(br.readLine()!=null)
			{
				line = br.readLine();
				String name = "";
				String artist = "";
				String year = "";
				String album = "";
				StringTokenizer str = new StringTokenizer(line, "/");
				name = str.nextToken();
				artist = str.nextToken();
				album = str.nextToken();
				year = str.nextToken();
				listOfSongs.add(new Song(name, artist, album, year));
			}
			fr.close();
			br.close();
	
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return listOfSongs;
	}
	
	public void filewrite() throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true));
		for (Song s : listOfSongs)
		{
			writer.write(s.name + "/" + s.artist + "/" + s.album + "/" + s.year + "/");
			writer.newLine();
		}
		writer.close();
		
	}
}

class Song {
	String name;
	String artist;
	String album;
	String year; 
	public Song(String name, String artist, String album, String year)
	{
		this.name = name;
		this.artist = artist; 
		this.album = album;
		this.year = year;
	}
	public Song(String name, String artist) {
		this(name, artist, "", "");
	}
	/*
	public Song(String name, String artist, String album) {
		this(name, artist, album, "");
	}
	public Song(String name, String artist, String year) {
		this(name, artist, "", year);
		
	}
	*/
	// need to add something to check for when using edit feature because year and album might be edited but name and artist could remain the same.
	public boolean equals(Song one, ObservableList<Song> listOfSongs)
	{
		for(Song s: listOfSongs)
		{
			if(one.name.equals(s.name))
			{
				if (one.artist.equals(s.artist))
				{
					return false;
				}
			}
		}
		return true;
	}
	public String toString() {
		return name + " by " + artist;
	}

}

