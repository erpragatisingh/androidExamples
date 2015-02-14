package com.sum;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class second extends Activity{
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		String str=this.getIntent().getExtras().getString("mykey");
		TextView t1=new TextView(second.this);
		t1.setText(str);
		setContentView(t1);
		
	}

}
