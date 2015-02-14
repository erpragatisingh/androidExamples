package com.myproject.in;

import java.io.IOException;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;
public class Recorder_Screen extends Dialog implements OnClickListener { 
	
	  
	    private Context context;
	    Button _stop,_start;
	    Chronometer _record_time;
	    String _SaveAs;
	    EditText editext;
//	    AudioRecorder audio_recorder;
	    MediaRecorder recorder;
	    public Recorder_Screen(Context context){
	            super(context);
	            this.context = context;
	            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	          
	          
	    }
	 
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.recording);
	        this. getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
	          recorder = new MediaRecorder();
	     
	         _record_time=(Chronometer) findViewById(R.id.recordingtime);
	          editext=(EditText) findViewById(R.id.recordng_name);
	        
	          _start= (Button) findViewById(R.id.Start_btn);
	          _stop= (Button) findViewById(R.id.Stop_btn);
	         
	         _start.setOnClickListener(this);
	         _stop.setOnClickListener(this);
	         
	         
	    }
	 
	 
		@Override
		public void onClick(View v) {
			 _SaveAs=new String(editext.getText().toString()) ;
			 
			 //			 audio_recorder=new AudioRecorder("/"+_SaveAs);
			 
			 switch(v.getId()){
				case R.id.Start_btn:
				    
					recordngstrt();
					break;
					
				 
				case R.id.Stop_btn:
					
				     if(!_start.isClickable()){
				    		 recorder.stop();
				             recorder.release();
				            _record_time.stop();
				      }
				     dismiss();
				     // You can reuse the object by going back to setAudioSource() step
				    
//					_record_time.stop();
//					 audio_recorder.stop();
				
				break;
			}
			
		}
		
		public void recordngstrt(){
			  if(_SaveAs.length()==0){
						  Toast.makeText(context, "Enter Recording Name First", Toast.LENGTH_SHORT).show();
					  }
					
			  else{		
				    editext.setEnabled(false);
					try {
						 recorder.reset();
						 recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
						 recorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
						 recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
						 recorder.setOutputFile("/sdcard/Sounds"+_SaveAs+".amr");
						 recorder.prepare();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    
					   _record_time.setBase(SystemClock.elapsedRealtime());
					   _record_time.start();
					   _start.setClickable(false);
					    recorder.start();
					 }
		    }
		
//	  public void Recordstrt(){
//		  if(_SaveAs.length()==0){
//			  Toast.makeText(context, "Enter Recording Name First", Toast.LENGTH_SHORT).show();
//		  }
//		  else{
//			  _record_time.setBase(SystemClock.elapsedRealtime());
//			  _record_time.start();
//			   _start.setClickable(false);
//			   Toast.makeText(context,editext.getText().toString(), Toast.LENGTH_SHORT).show();
//			  try {
//				  
//				  audio_recorder.start();
//			
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		  }
//	  }	
	    

}


