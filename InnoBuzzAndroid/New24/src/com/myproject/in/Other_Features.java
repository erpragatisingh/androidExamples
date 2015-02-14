package com.myproject.in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Other_Features extends Activity implements OnClickListener  {
	Button _schedule,_compass,_my_info,_back;
	@Override
	   public void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	         setContentView(R.layout.other_features);
	         
	         _schedule=(Button) findViewById(R.id.for_scheduling);
	         _compass=(Button) findViewById(R.id.for_campass);
	         _my_info=(Button) findViewById(R.id.for_myinfo);
	         _back=(Button) findViewById(R.id.backbutton);
	          _back.setText("Back");
	          TextView name=(TextView) findViewById(R.id.headername);
	          name.setText("Other Features");
	          Button save=(Button) findViewById(R.id.Save_call);
	          save.setVisibility(View.INVISIBLE);
	         _back.setOnClickListener(this);
	         _schedule.setOnClickListener(this);
	         _compass.setOnClickListener(this);
	         _my_info.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.backbutton:
			finish();
			break;
		
		case R.id.for_scheduling:
			Intent intent=new Intent(Other_Features.this,Fake_Call_Scheduler.class);
			startActivity(intent);
			break;
		case R.id.for_campass:
			
			break;
		case R.id.for_myinfo:
			break;
		
		}
		
	}
}
