package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<SongList> songList; //data: the names displayed
    private RVClickListener RVvideo; //listener defined in main activity
    private RVClickListener RVsinger_wiki; //listener defined in main activity
    private RVClickListener RVsong_info; //listener defined in main activity
    private Context context;
    private RVClickListener RVimage;
    private ArrayList<Integer> images;

    /*
    passing in the data and the listener defined in the main activity
     */
    //Adapter Constructor
    public MyAdapter(Context con, ArrayList<SongList> theList, RVClickListener video,
                     RVClickListener singer, RVClickListener song_info, ArrayList<Integer> image){
        this.context = con;
        songList = theList;
        this.RVvideo = video;
        this.RVsinger_wiki = singer;
        this.RVsong_info = song_info;
        images = image;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //CreateHolder
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.my_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView, RVvideo, RVsinger_wiki, RVsong_info);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//BindViewHolder
        holder.artist_1.setText(songList.get(position).artist);
        holder.title_1.setText(songList.get(position).title);
        holder.image_1.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();//image size equals to the list size
    }




    /*
        This class creates a wrapper object around a view that contains the layout for
         an individual item in the list. It also implements the onClickListener so each ViewHolder in the list is clickable.
        It's onclick method will call the onClick method of the RVClickListener defined in
        the main activity.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{

        public TextView title_1;
        public TextView artist_1;
        public ImageView image_1;
        private RVClickListener listener;
        private View itemView;
        private RVClickListener RVvideo;
        private RVClickListener RVsinger_wiki;
        private RVClickListener RVsong_wiki;


        public ViewHolder(@NonNull View itemView, RVClickListener video, RVClickListener singer, RVClickListener song_wiki ) {
            super(itemView);//Holder
            title_1 = (TextView) itemView.findViewById(R.id.songName);
            artist_1 = (TextView) itemView.findViewById(R.id.artistName);
            image_1 = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.itemView = itemView;
            itemView.setOnCreateContextMenuListener(this); //set context menu for each list item (long click)
            this.RVvideo = video;
            this.RVsinger_wiki = singer;
            this.RVsong_wiki = song_wiki;


            /*
                don't forget to set the listener defined here to the view (list item) that was
                passed in to the constructor.
             */
            itemView.setOnClickListener(this); //set short click listener
        }

        @Override
        public void onClick(View v) {
            RVvideo.onClick(v, getAdapterPosition());
            Log.i("ON_CLICK", "in the onclick in view holder");
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            //inflate menu from xml
            //Menu Options for Context Menu
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.context_menu, menu );
            menu.getItem(0).setOnMenuItemClickListener(onMenu);
            menu.getItem(1).setOnMenuItemClickListener(onMenu);
            menu.getItem(2).setOnMenuItemClickListener(onMenu);

        }


        private final MenuItem.OnMenuItemClickListener onMenu = new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                Log.i("ON_CLICK", title_1.getText() + " adapter pos: " + getAdapterPosition());
                Log.i("getItemID", "item id is " + item.getItemId() + " but songName is " + R.id.menu1);
                int temp = item.getItemId() - R.id.songName;
                //menu options click Context menu
                if(item.getItemId() == R.id.menu1)
                {
                    //Open the pop up
                    Uri songURL = Uri.parse(songList.get(getAdapterPosition()).song_link);
                    Intent intent = new Intent(Intent.ACTION_VIEW, songURL);
                    context.startActivity(intent);
                }
                if(item.getItemId() == R.id.menu2)
                {
                    Uri songURL = Uri.parse(songList.get(getAdapterPosition()).artist_wiki);
                    Intent intent = new Intent(Intent.ACTION_VIEW, songURL);
                    context.startActivity(intent);

                }
                if(item.getItemId() == R.id.menu3)
                {
                    Uri songURL = Uri.parse(songList.get(getAdapterPosition()).song_wiki);
                    Intent intent = new Intent(Intent.ACTION_VIEW, songURL);
                    context.startActivity(intent);
                }
                return true;
            }
        };



    }
}
