package com.text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class text1 extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text1);
		Button bbb=(Button)findViewById(R.id.bbb);
		bbb.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent xyz = new Intent(text1.this,TextActivity.class);
				startActivity(xyz);
				
			}
		});
		
	}
	

}
