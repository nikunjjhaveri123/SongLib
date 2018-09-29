package application;

import java.util.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Controller {
	
	Stage mainStage;
	
	@FXML
	ListView<Song> listview;
	
	ObservableList<Song> listOfSongs = FXCollections.observableArrayList();
	
	@FXML
	private Button Edit;
	@FXML
	private Button Add;
	@FXML
	private Button Delete;
	
	@FXML
	private Label details;
	
	final static String fileName = "src\\application\\SongList.txt";
	
	public void setMainStage(Stage stage) {
		mainStage = stage;
	}
	
	// starts the program by reading the file and setting up the listview to show the list of Songs
	public void start(Stage mainStage) {
		listOfSongs = fileRead(fileName);
		for(Song s: listOfSongs)
		{
			System.out.println(s);
		}
		listview.setItems(listOfSongs); 
		listview.getSelectionModel().select(0);
		details.setText(listview.getSelectionModel().getSelectedItem().toString());
		
		listview.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> details.setText(listview.getSelectionModel().getSelectedItem().toString()));
		
	}
	
	public void restart(Stage mainStage, ObservableList<Song> songs)
	{
		listOfSongs = songs;
		listview.setItems(listOfSongs); 
		listview.getSelectionModel().select(0);
		details.setText(listview.getSelectionModel().getSelectedItem().toString());
		
		listview.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> details.setText(listview.getSelectionModel().getSelectedItem().toString()));
		
		
	}
	
	//Reads a file and returns and ObservableList of Songs that can be used in the listview
	public ObservableList<Song> fileRead(String fileName)
	{
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
	
			String line;
			String delim = "/";
			while((line = br.readLine())!= null)
			{
				System.out.println(line);
				String name = "";
				String artist = "";
				String year = "";
				String album = "";
				StringTokenizer str = new StringTokenizer(line, delim, false);
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
	
	//This method removes the selected song from the observablelist listOfSongs
	public void deleteSong(ActionEvent Event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DeleteView.fxml")); // dir of your .fxml file
		Parent DeleteView = loader.load();
		Scene DeleteViewscene = new Scene(DeleteView);
		DeleteController controller = loader.getController();
		Stage window = (Stage) ((Node)Event.getSource()).getScene().getWindow();
		controller.initSongList(listOfSongs, listview.getSelectionModel().getSelectedItem());
		window.setScene(DeleteViewscene);
		window.show();
	}
	
	
	public void addSong(ActionEvent Event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AddView.fxml")); // dir of your .fxml file
		Parent AddView = loader.load();
		Scene AddViewscene = new Scene(AddView);
		AddController controller = loader.getController();
		Stage window = (Stage) ((Node)Event.getSource()).getScene().getWindow();
		controller.initSongList(listOfSongs);
		window.setScene(AddViewscene);
		window.show();
		
		
	}
	public void editSong(ActionEvent Event)
	{
		Song temp = (Song) listview.getSelectionModel().getSelectedItem();
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
			if(one.name.equals(s.name) && one.artist.equals(s.artist))
			{
				return true;
			}
		}
		return false;
	}
	
	public void insertSong(ObservableList<Song> list)
	{
		for(int i = 0;i < list.size(); i++)
		{
			int comp = (this.name.toLowerCase()).compareTo(list.get(i).name.toLowerCase());
			{
				if(comp < 0)
				{
					list.add(i, this);
					return;
				}
				else if(comp == 0)
				{
					int artcomp = this.artist.toLowerCase().compareTo(list.get(i).artist.toLowerCase());
					{
						if(artcomp < 0)
						{
							list.add(i, this);
							return;
						}
					}
				}
			}
		}
	}
	
	public String toString() {
		String x = name + " by " + artist;
		if(!(this.album.equals("&"))) {
			x = x + " from album: " + album;
		}
		if(!(this.year.equals("&"))) {
			x = x + " created in year " + year;
		}
		return x;
		
	}

}

