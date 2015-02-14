package com.myproject.in;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnLongClickListener {
     static MediaPlayer _mplayer;
    
     @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_layout);
       ((Button)findViewById(R.id.btn_siren)).setOnLongClickListener(this);
       
    }
    
    
    public void MyClickHandler(View view){
    	switch(view.getId())
    	{
    	case R.id.btn_info: 
    	    Toast.makeText(getApplicationContext(), "click button 0", Toast.LENGTH_SHORT).show();
    	   break;
    	  
    	 case R.id.btn_flash: 
    	    Toast.makeText(getApplicationContext(), "click button 1", Toast.LENGTH_SHORT).show();
    	     break;
    	 
    	 case R.id.btn_siren:
    		 _mplayer=MediaPlayer.create(getApplicationContext(), R.raw.start);
    		 try {
    			_mplayer.prepare();
    		} catch (IllegalStateException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		 _mplayer.start();
	    	 _mplayer.stop();
    		 break;

    	 case R.id.btn_emergency: 
    		 Emergency_popup  emergency_popup = new Emergency_popup(this);
    		 emergency_popup.show();
          	 break;
    	 
    	 case R.id.btn_locater:
    		 
            break;
    	 
    	 case R.id.btn_recorder: 
    		 Recorder_popup  recorder_popup = new Recorder_popup(this);
    		 recorder_popup.show();
    		 break;   
    	 
    	 case R.id.btn_other_feature:
    		 Intent intent2=new Intent(MainActivity.this, Other_Features.class);
    		 startActivity(intent2);
           break;
    	}
    }




	@Override
	public boolean onLongClick(View v) {
		Siren_popup  siren_popup = new Siren_popup(this,_mplayer);
		siren_popup.show();
   	 	return true;
	}
}