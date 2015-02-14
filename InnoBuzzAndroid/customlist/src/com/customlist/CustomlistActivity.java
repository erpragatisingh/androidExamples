package com.customlist;



import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

public class CustomlistActivity extends ListActivity {
    /** Called when the activity is first created. */
	
	ListView list;
	MyAdapter adapter;
	Button btn1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list=(ListView)findViewById(R.id.list);
        adapter= new MyAdapter(this,);
        list.setAdapter(adapter);
        
         btn1=(Button)findViewById(R.id.btn1);
        
       
    }
}