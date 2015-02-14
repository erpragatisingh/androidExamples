package com.ngma.labs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	  
	    setContentView(R.layout.receiveactivity);
	    Intent intent = getIntent();
	    Bundle bundle = intent.getExtras();
	    int val = bundle.getInt("pass_value");
	    
	    TextView tv = (TextView)findViewById(R.id.textView1);
	    tv.append(String.valueOf(val));
	   
	}

}
