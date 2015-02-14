package com.list;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyList extends Activity {
	ListView listname;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		listname=(ListView)findViewById(R.id.list);
		
		listname.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				getResources().getStringArray(R.array.empname)));
		
		listname.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long arg3) {
				// TODO Auto-generated method stub
				if (position==0)
				{
					Intent in = new Intent(MyList.this, Yogesh.class);
					startActivity(in);
					
				}
				if (position==1)
				{
					Intent in = new Intent(MyList.this, Pragati.class);
					startActivity(in);
					
				}
				
				
			}
			
		});
		
		
		
	
		
		
	}

}
