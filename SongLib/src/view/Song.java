//Nikunj Jhaveri and Louie Zhou
package view;

import javafx.collections.ObservableList;


public class Song {
	
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
		public Song(String name, String artist) 
		{
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
			if(listOfSongs.isEmpty()) {
				return false;
			}
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
			if(list.isEmpty()) {
				list.add(this);
				return;
			}
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
			list.add(this);
			return;
		}
		
		public String toString() 
		{
			String x = name + " by " + artist;
			return x;
			
		}
		public String SongDetails()
		{
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

