package com.player;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class UrlvedioActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
      VideoView video = (VideoView) findViewById(R.id.videoView1);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        video.setKeepScreenOn(true);
        video.setVideoPath("http://localhost/myvedio/Wildlife.mp4");
        video.start();
        video.requestFocus();
        
        /*
         * 
         * mVideo = (VideoView) findViewById(R.id.videoView);
    MediaController controller = new MediaController(this);
    mVideo.setMediaController(controller);
    mVideo.setVideoURI(Uri.parse(STREAM_URL));
    mVideo.start();
    
    
    
    
    @Override
protected void onPause() {
    super.onPause();
    mVideo.suspend();
}

@Override
protected void onResume() {
    super.onResume();
    mVideo.resume();
}

@Override
public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
}
    
    
         * 
         */
        
        
        
        
    }
}