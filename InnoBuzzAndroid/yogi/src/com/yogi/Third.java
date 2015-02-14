package com.yogi;


 
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Third extends ListActivity {
 ListView test;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);
		
		test=(ListView)findViewById(R.id.list);
		
		
		
	}
}
