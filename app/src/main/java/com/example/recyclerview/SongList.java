package com.example.recyclerview;
//SongList Object contains all the information with getters and setters
public class SongList {
    String title;
    String artist;
    String artist_wiki;
    String song_wiki;
    String song_link;
    int image; //image
    public SongList(String title, String artist, String artist_wiki, String song_wiki, String song_link, int pic)
    {
        this.title = title;
        this.artist = artist;
        this.artist_wiki = artist_wiki;
        this.song_wiki = song_wiki;
        this.song_link = song_link;
        this.image = pic;
    }

    public String getTitle(){
        return title;
    }
    public String getArtist(){
        return artist;
    }
    public String getArtist_wiki(){
        return artist_wiki;
    }
    public String getSong_wiki(){
        return song_wiki;
    }
    public String getSong_link(){
        return song_link;
    }
    public int getImage(){
        return image;
    }
    public void setImage(int input){
        this.image = input;
    }
    public void setTitle(String input){
        this.title = input;
    }
    public void setArtist(String input){
        this.artist = input;
    }
    public void setArtist_wiki(String input){
        this.artist_wiki = input;
    }
    public void setSong_wiki(String input){
        this.song_wiki = input;
    }
    public void setSong_link(String input){
        this.song_link = input;
    }

}
