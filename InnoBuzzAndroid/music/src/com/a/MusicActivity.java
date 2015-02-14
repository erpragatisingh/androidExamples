package com.a;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MusicActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		Button b=(Button)findViewById(R.id.button1);
		Button b2=(Button)findViewById(R.id.button2);
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in2=new Intent(MusicActivity.this,show.class);
				startActivity(in2);
			}
		});
		b.setOnClickListener(new OnClickListener() {
		EditText name=(EditText)findViewById(R.id.editText1);
		EditText email=(EditText)findViewById(R.id.editText2);
		//EditText location=(EditText)findViewById(R.id.editText3);
		
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MusicActivity.this,home.class);
				i.putExtra("name",name.getText().toString());
				i.putExtra("email",email.getText().toString());
				
				startActivity(i);
			}
		});
	}
	
}