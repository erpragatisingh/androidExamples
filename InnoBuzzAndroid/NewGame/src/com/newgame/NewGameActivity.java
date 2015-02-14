package com.newgame;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NewGameActivity extends Activity {
	
	Button btn1,btn2,btn4;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn4=(Button)findViewById(R.id.btn4);
        
        btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent in1= new Intent(NewGameActivity.this,Second.class);
				startActivity(in1);
				
			}
		});
        
        
        btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent in2= new Intent(NewGameActivity.this,About.class);
				startActivity(in2);
				
			}
		});
        
        btn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent in3= new Intent(NewGameActivity.this,Help.class);
				startActivity(in3);
				
			}
		});
    }
}