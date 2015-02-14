package com.ngma;

import java.io.File;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

import com.sartaj.video.R;

public class MyVideoPlaybackActivity extends Activity {
	
	static private final String pathToFile = "Wildlife.mp4";
	VideoView videoView;
	MediaController controller;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        File root = Environment.getExternalStorageDirectory(); 
        
        videoView = new VideoView(this);
        videoView.setVideoURI( Uri.parse(root + "/" + pathToFile) );
        controller = new MediaController(this);
        videoView.setMediaController(controller);
        videoView.start();
        setContentView(videoView);
    }
    
    @Override
    public void onDestroy() {
    super.onDestroy();
    videoView.stopPlayback();
    }
}