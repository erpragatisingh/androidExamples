package com.Innobuzz;



import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class Inno extends TabActivity{
	 private TabHost mtabhost;
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.inno);
	        
	        Resources res=getResources();
	        mtabhost =getTabHost();
	        TabHost.TabSpec spec;
	        Intent intent;
	        // Home tab
	        intent =new Intent().setClass(this, Home.class);
	        spec =mtabhost.newTabSpec("Home")
	        		.setIndicator("Home",res.getDrawable(R.drawable.tab_icon))
	        		.setContent(intent);
	        mtabhost.addTab(spec);
	        
	        // Programs tab
	        intent =new Intent().setClass(this, Programs.class);
	        spec =mtabhost.newTabSpec("Programs")
	        		.setIndicator("Programs",res.getDrawable(R.drawable.tab_icon_pro))
	        		.setContent(intent);
	        mtabhost.addTab(spec);
	        
	        
	        // Map tab
	        intent =new Intent().setClass(this, Map.class);
	        spec =mtabhost.newTabSpec("Map")
	        		.setIndicator("Map",res.getDrawable(R.drawable.tab_icon_map))
	        		.setContent(intent);
	        mtabhost.addTab(spec);
	        
	        
		     // Events tab
	        intent =new Intent().setClass(this, Event.class);
	        spec =mtabhost.newTabSpec("Event")
	        		.setIndicator("Event",res.getDrawable(R.drawable.tab_icon_event))
	        		.setContent(intent);
	        mtabhost.addTab(spec);
	               
	        mtabhost.setCurrentTab(0);
		
		
	}

}
