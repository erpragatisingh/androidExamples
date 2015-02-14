package com.ngma;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class AnimalSoundActivity extends Activity implements OnClickListener {
	 private MediaPlayer mp;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View button1 = findViewById(R.id.imageButton1);
        View button2 = findViewById(R.id.imageButton2);
        View button3 = findViewById(R.id.imageButton3);    
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }
    
    public void onClick(View v) {
    	 if(mp != null) mp.release();
        // Find which ImageButton was pressed and take appropriate action
        
        switch(v.getId()){
        
            // The cow button
        case R.id.imageButton1:
            mp = MediaPlayer.create(this, R.raw.cow);	
        break;
            
            // The duck button
            case R.id.imageButton2:
                 //   Log.i("Test", "Duck Button");
                    mp = MediaPlayer.create(this, R.raw.duck);
            break;

            // The sheep button                
            case R.id.imageButton3:
                  //  Log.i("Test", "Sheep Button");
                    mp = MediaPlayer.create(this, R.raw.sheep);
            break;
        
        }  
        mp.seekTo(0);
        mp.start();
       
    }
    @Override
    public void onPause() {
        super.onPause();
        // Release the MediaPlayer if going into background
        if(mp != null) mp.release();
    }
    
}