package com.myproject.in;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

public class Fake_Incoming_call_Screen extends Activity implements OnClickListener {
	private ImageButton _callin,_callend;
	private Chronometer _calltimer;
	private MediaPlayer _mplayer;
	String _Name,_ph_no;
	@Override
    public void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	         getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
	         setContentView(R.layout.fake_incoming_call_screen);
	         ringtone();
	         _callin=(ImageButton) findViewById(R.id.imageButton1);
	         _callend=(ImageButton) findViewById(R.id.imageButton2);
	         _calltimer=(Chronometer) findViewById(R.id.chronometer1);
	          TextView callid=(TextView) findViewById(R.id.textView2);
	          callid.setText(getIntent().getExtras().getCharSequence("Name"));
	         _calltimer.setVisibility(View.GONE);
	         _callin.setOnClickListener(this);
	         _callend.setOnClickListener(this);
	}

	
	public void ringtone(){
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
		}
	
	
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.imageButton1:
			_mplayer.stop();
			_calltimer.setVisibility(View.VISIBLE);
			_calltimer.setBase(SystemClock.elapsedRealtime());
			_calltimer.start();
			_callin.setVisibility(View.GONE);
			break;
			
		case R.id.imageButton2:
			_mplayer.stop();
			_calltimer.stop();
			finish();
			break;	
		}
		
	}
	
//	@Override public boolean onKeyDown(int keyCode, KeyEvent event) {
//		
//		switch(keyCode){
//			case KeyEvent.KEYCODE_CALL:             
//				_mplayer.stop();
//				_calltimer.setVisibility(View.VISIBLE);
//				_calltimer.setBase(SystemClock.elapsedRealtime());
//				_calltimer.start();
//				_callin.setVisibility(View.GONE);  
//				return true;
//				 
//			case KeyEvent.KEYCODE_ENDCALL:
//				_mplayer.stop();
//				_calltimer.stop();
//				finish();
//				return true;
//				}     
//		 return super.onKeyDown(keyCode, event);
//		}
   
}
