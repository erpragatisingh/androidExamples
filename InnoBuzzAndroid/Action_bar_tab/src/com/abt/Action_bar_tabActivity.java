package com.abt;


import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;



public class Action_bar_tabActivity extends TabActivity {
	
	 private TabHost mtabhost;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Resources res=getResources();
        mtabhost =getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        // artists tab
        intent =new Intent().setClass(this, Artists.class);
        spec =mtabhost.newTabSpec("artists")
        		.setIndicator("Artists",res.getDrawable(R.drawable.tab_icon))
        		.setContent(intent);
        mtabhost.addTab(spec);
        
     // songs tab
        intent =new Intent().setClass(this, Songs.class);
        spec =mtabhost.newTabSpec("songs")
        		.setIndicator("Songs",res.getDrawable(R.drawable.tab_icon))
        		.setContent(intent);
        mtabhost.addTab(spec);
        
        
     // albums tab
        intent =new Intent().setClass(this, Albums.class);
        spec =mtabhost.newTabSpec("albums")
        		.setIndicator("Albums",res.getDrawable(R.drawable.tab_icon))
        		.setContent(intent);
        mtabhost.addTab(spec);
               
        mtabhost.setCurrentTab(1);
    }   
  
   
}

