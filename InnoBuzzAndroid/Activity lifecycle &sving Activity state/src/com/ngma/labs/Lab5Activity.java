package com.ngma.labs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Lab5Activity extends Activity {
	//private int myState;
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("onPause", "inside onPause");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i("onRestart", "inside onRestart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("onResume", "inside onResume");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("onStart", "inside onStart");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("onDestroy", "inside onDestroy");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("onStop", "inside Stop");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.i("onSaveInstanceState", "inside onSaveInstanceState");
		//outState.putInt("var", myState);
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.i("onCreat", "inside onsCreat");
        
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//myState = 40;
				// TODO Auto-generated method stub
				Intent intent = new Intent(Lab5Activity.this, ReceiveActivity.class);
				intent.putExtra("pass_value", 50);
				startActivity(intent);
			}
		});
        
        
    /*    if(savedInstanceState != null){
        	myState = savedInstanceState.getInt("var");
        }else{
        	myState = 0;	
        }*/
    }
}