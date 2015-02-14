package com.Innobuzz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class WebAppSecurity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView tv=new TextView(this);
		tv.setText("Web App Security");
		setContentView(tv);
	}

}
