package com.Innobuzz;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Home extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		Spinner spin_pro=(Spinner)findViewById(R.id.spinner_program);
		Spinner spin_loc=(Spinner)findViewById(R.id.spinner_city_location);
		ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, 
				getResources().getStringArray(R.array.programs));
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin_pro.setAdapter(myAdapter);
		
		ArrayAdapter<String> mAdapter=new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, 
				getResources().getStringArray(R.array.location));
		mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spin_loc.setAdapter(mAdapter);

	}
	

}
