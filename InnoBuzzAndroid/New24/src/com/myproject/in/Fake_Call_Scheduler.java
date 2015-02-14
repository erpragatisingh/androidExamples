package com.myproject.in;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;



public class Fake_Call_Scheduler extends Activity implements OnClickListener {
	  /** Called when the activity is first created. */
	 
	 private PendingIntent pendingIntent;
	 TimePicker _alarmtime;
	 private Button _Savebtn,_back;
	 EditText _caller_name;
	 private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss a");
	 String strWidgetText = "";
	 EditText _schedule_D_T;
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	         setContentView(R.layout.call_scheduler_screen);
	         
	        _alarmtime=(TimePicker) findViewById(R.id.Time_picker);
	        _caller_name=(EditText) findViewById(R.id.caller);
	         _schedule_D_T=(EditText) findViewById(R.id.schedule_tm);
	        _Savebtn = (Button)findViewById(R.id.Save_call);
	        _back=(Button) findViewById(R.id.backbutton);
	         
	        _back.setOnClickListener(this);
	        _Savebtn.setOnClickListener(this);
	        
	        String currentTime = formatter.format(new Date());
	        strWidgetText = strWidgetText + currentTime;
	        _schedule_D_T.setText(strWidgetText.toString());
	         
	    }

	   @Override
		public void onClick(View v) {
		   switch(v.getId()){
		   
		   case R.id.backbutton:
			   finish();
			   break;
		   
		   case R.id.Save_call:
		      Intent callIntent = new Intent(Fake_Call_Scheduler.this, Call_Broadcast.class); 
		      Bundle b=new Bundle();
		      b.putCharSequence("Name",_caller_name.getText().toString());
		      callIntent.putExtras(b);
	          pendingIntent=PendingIntent.getBroadcast(Fake_Call_Scheduler.this, 0, callIntent,PendingIntent.FLAG_CANCEL_CURRENT);
		   
		   int alarmtime=(_alarmtime.getCurrentMinute()+(_alarmtime.getCurrentHour()*60))*60000;
		      AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	            
		      Calendar calendar = Calendar.getInstance();
	       int currenttime=((((calendar.get(Calendar.HOUR_OF_DAY)*60)+calendar.get(Calendar.MINUTE))*60)+calendar.get(Calendar.SECOND))*1000;
	          calendar.add(Calendar.MILLISECOND, (alarmtime-currenttime));
	        
	          alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pendingIntent);
	          finish();
	          break;
		   } 
	   }

	}
