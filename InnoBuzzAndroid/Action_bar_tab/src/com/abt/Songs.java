package com.abt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Songs extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		TextView textview=new TextView(this);
		textview.setText("Songs Content");
		setContentView(textview);
	}

}
