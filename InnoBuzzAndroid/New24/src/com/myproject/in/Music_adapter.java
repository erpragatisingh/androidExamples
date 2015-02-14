package com.myproject.in;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Music_adapter extends ListActivity {
	 
    private static final String MEDIA_PATH = new String("/sdcard");
    private List<String> songs = new ArrayList<String>();
    private MediaPlayer mp = new MediaPlayer();
    private int currentPosition = 0;

    @Override
    public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            setContentView(R.layout.view_archive);
            updateSongList();
    }
    
    
    public void updateSongList() {
        File home = new File(MEDIA_PATH);
        if (home.listFiles(new Mp3Filter()).length > 0) {
                for (File file : home.listFiles(new Mp3Filter())) {
                        songs.add(file.getName());
                }
 
                ArrayAdapter<String> songList = new ArrayAdapter<String>(this,
                                R.layout.audio_item_text, songs);
                setListAdapter(songList);
        }
    }
    
    
    class Mp3Filter implements FilenameFilter {
        public boolean accept(File dir, String name) {
                return (name.endsWith(".amr"));
        }
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
            currentPosition = position;
            playSong(MEDIA_PATH + songs.get(position));
    }
    private void playSong(String songPath) {
        try {
 
                mp.reset();
                mp.setDataSource(songPath);
                mp.prepare();
                mp.start();
 
                
        } catch (IOException e) {
                Log.v(getString(R.string.app_name), e.getMessage());
        }
    }
}