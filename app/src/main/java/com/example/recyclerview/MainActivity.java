package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> myList;
    ArrayList<SongList> listSong;//song List
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        List<String> names = Arrays.asList("mark", "ugo", "tammy", "syed", "siham", "carlos", "ayna",
                "cozmo", "regis", "regina", "hector", "aziz", "tasha", "nicole");
        myList = new ArrayList<>();
        myList.addAll(names);
        //creating songs objects
        SongList yummy =  new SongList("Yummy","Justin Bieber",
                "https://en.wikipedia.org/wiki/Justin_Bieber",
                "https://en.wikipedia.org/wiki/Yummy_(Justin_Bieber_song)",
                "https://www.youtube.com/watch?v=8EJ3zbKTWQ8&ab_channel=JustinBieberVEVO",
                 R.drawable.yummy);
        SongList theLazySong = new SongList("The Lazy Song", "Bruno Mars",
                "https://en.wikipedia.org/wiki/Bruno_Mars",
                "https://en.wikipedia.org/wiki/The_Lazy_Song",
                "https://www.youtube.com/watch?v=fLexgOxsZu0&ab_channel=BrunoMars",
                R.drawable.tls);
        SongList hello = new SongList("Hello", "Adele",
                "https://en.wikipedia.org/wiki/Adele",
                "https://en.wikipedia.org/wiki/Hello_(Adele_song)",
                "https://www.youtube.com/watch?v=YQHsXMglC9A&ab_channel=AdeleVEVO",
                R.drawable.hello);
        SongList perfect = new SongList("Perfect", "Ed Sheeran",
                "https://en.wikipedia.org/wiki/Ed_Sheeran",
                "https://en.wikipedia.org/wiki/Perfect_(Ed_Sheeran_song)#Music_video",
                "https://www.youtube.com/watch?v=2Vv-BfVoq4g&ab_channel=EdSheeran",
                R.drawable.perfect);
        SongList saveYourTears = new SongList("Save Your Tears", "The Weeknd",
                "https://en.wikipedia.org/wiki/The_Weeknd",
                "https://en.wikipedia.org/wiki/Save_Your_Tears",
                "https://www.youtube.com/watch?v=XXYlFuWEuKI&ab_channel=TheWeekndVEVO",
                R.drawable.syt);
        SongList happy = new SongList("Happy", "Pharrel Williams",
                "https://en.wikipedia.org/wiki/Pharrell_Williams",
                "https://en.wikipedia.org/wiki/Happy_(Pharrell_Williams_song)",
                "https://www.youtube.com/watch?v=y6Sxv-sUYtM&ab_channel=iamOTHER",
                R.drawable.happy);
        SongList letItGo = new SongList("Let It Go", "Idina Menzel",
                "https://en.wikipedia.org/wiki/Idina_Menzel",
                "https://en.wikipedia.org/wiki/Let_It_Go",
                "https://www.youtube.com/watch?v=moSFlvxnbgk&ab_channel=WaltDisneyAnimationStudios",
                R.drawable.letitgo);
        //Adding all the songs in the list
        listSong = new ArrayList<SongList>();
        listSong.add(yummy);
        listSong.add(theLazySong);
        listSong.add(hello);
        listSong.add(perfect);
        listSong.add(saveYourTears);
        listSong.add(happy);
        listSong.add(letItGo);
        //Define the listener with a lambda and access the list of names with the position passed in

        //listener to click on the song
        RVClickListener video = (view,index)->{
            Uri songURL = Uri.parse(listSong.get(index).song_link);
            TextView name = (TextView)view.findViewById(R.id.songName);
            Intent intent = new Intent(Intent.ACTION_VIEW, songURL);
            startActivity(intent); //option to open song with single click
            Toast.makeText(this,listSong.get(index).title,Toast.LENGTH_SHORT).show();
            Log.i("video", "Does it work? 1.0");
        };
        RVClickListener singer = (view,index)->{
            Uri singer_info = Uri.parse(listSong.get(index).artist_wiki);
            TextView name = (TextView)view.findViewById(R.id.artistName);
            Intent intent = new Intent(Intent.ACTION_VIEW, singer_info);
            //startActivity(intent);
            Toast.makeText(this,listSong.get(index).artist_wiki,Toast.LENGTH_SHORT).show();
            Log.i("singer", "Does it work? 2.0");

        };
        RVClickListener song_info = (view,index)->{
            Uri song_info_Link = Uri.parse(listSong.get(index).song_wiki);
            Intent intent = new Intent(Intent.ACTION_VIEW, song_info_Link);
            //startActivity(intent);
            Toast.makeText(this,listSong.get(index).song_wiki,Toast.LENGTH_SHORT).show();
            Log.i("song_info", "Does it work? 3.0");
        };

        ArrayList<Integer> songImage= new ArrayList<Integer>();
        for(int i = 0; i < listSong.size(); i++){
            songImage.add(listSong.get(i).image);
        }
        MyAdapter adapter = new MyAdapter(this, listSong, video, singer, song_info, songImage);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
      //  nameView.setLayoutManager(new GridLayoutManager(this,3)); //use this line to see as a grid
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //use this line to see as a standard vertical list


    }
    //Menu option for layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.layout_menu, menu);
        return true;
    }
    //you can put the contextItem selected method here or use a listener in the ViewHolder class
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.grid_menu) //if layout selected change layout
        {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        if(item.getItemId() == R.id.list_menu)
        {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        return true;
    }


}