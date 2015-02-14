package com.videocapture;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VideocaptureActivity extends Activity {
	private static final int REQUEST_VIDEO = 100;
	Button captureButton;
	TextView text;
	File file;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        captureButton = (Button)findViewById(R.id.capture);
        captureButton.setOnClickListener(listener);
        text = (TextView)findViewById(R.id.file);
        
        File sdCard = Environment.getExternalStorageDirectory();
		File directory = new File (sdCard.getAbsolutePath() +"/MyVideos");
		directory.mkdirs();
		 file = new File(directory, "myvideo");
		
       
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(requestCode == REQUEST_VIDEO && resultCode == Activity.RESULT_OK) {
    String location = data.getData().toString();
    text.setText(location);
    }
    }
    private View.OnClickListener listener = new View.OnClickListener() {
    	@Override
    	public void onClick(View v) {
    	Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
    	//Add (optional) extra to save video to our file
    	intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
    	//Optional extra to set video quality
    	intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
    	startActivityForResult(intent, REQUEST_VIDEO);
    	}
    	};
}