package com.santhosh;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SanthoshActivity extends Activity {
	ListView list;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        list=(ListView)findViewById(R.id.list);
        
        list.setAdapter(new ArrayAdapter<String>(this, 
        		android.R.layout.simple_list_item_1,
        		getResources().getStringArray(R.array.text)));
        		
    }
}