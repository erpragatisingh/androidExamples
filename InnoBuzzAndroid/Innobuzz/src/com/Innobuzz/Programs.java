package com.Innobuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Programs extends Activity  {
	ListView programlist;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.program);
		
	    programlist=(ListView)findViewById(R.id.list);
	 
	    programlist.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				getResources().getStringArray(R.array.programs)));
	    
	    
	    programlist.setOnItemClickListener(new OnItemClickListener() {

	    	Intent i;
	    	
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,long id) {
				// TODO Auto-generated method stub
				switch(position)
				{
				case 0:
					Intent i  = new Intent(Programs.this,EthicalHacking.class);
		             startActivity(i);
		             break;
				case 1:
					 i=new Intent(Programs.this, NetworkSecurity.class);
					 startActivity(i);
		             break;
				case 2:
					 i = new Intent(Programs.this, MalwareAnalysis.class);
		             startActivity(i);
		             break;
				case 3:
					 i=new Intent(Programs.this, ExploitWriting.class);
					 startActivity(i);
		             break;
				case 4:
					 i = new Intent(Programs.this, WebAppSecurity.class);
		             startActivity(i);
		             break;
				case 5:
					 i=new Intent(Programs.this, ReverseEngineering.class);
					 startActivity(i);
		             break;
				case 6:
					 i = new Intent(Programs.this, Php.class);
		             startActivity(i);
		             break;
				case 7:
					 i=new Intent(Programs.this, Android.class);
					 startActivity(i);
		             break;
				case 8:
					 i=new Intent(Programs.this, Seo.class);
					 startActivity(i);
		             break;
		             
		            
		             default: 

				}
			
				
				
			}
		});
		
	}

}
