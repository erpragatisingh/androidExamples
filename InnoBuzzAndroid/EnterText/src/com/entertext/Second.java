package com.entertext;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Second extends Activity {
	TextView add;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		add=(TextView)findViewById(R.id.tv);
		
		String str=getIntent().getExtras().getString("mykey");
		add.setText(str);
		
		
		
	}

}
