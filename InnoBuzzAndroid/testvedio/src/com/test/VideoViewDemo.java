package com.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoViewDemo extends Activity {

    /**
     * TODO: Set the path variable to a streaming video URL or a local media
     * file path.
     */
    private String path = "R.raw.wildlife";
 
  //  mp = MediaPlayer.create(this, R.raw.cow);
    
    private VideoView mVideoView;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        mVideoView = (VideoView) findViewById(R.id.surface_view);

        if (path == "") {
            // Tell the user to provide a media file URL/path.
            Toast.makeText(
                    VideoViewDemo.this,
                    "Please edit VideoViewDemo Activity, and set path"
                            + " variable to your media file URL/path",
                    Toast.LENGTH_LONG).show();

        } else {

            /*
             * Alternatively,for streaming media you can use
             * mVideoView.setVideoURI(Uri.parse(URLstring));
             */
            mVideoView.setVideoPath(path);
            mVideoView.setMediaController(new MediaController(this));
            mVideoView.requestFocus();

        }
    }
}
