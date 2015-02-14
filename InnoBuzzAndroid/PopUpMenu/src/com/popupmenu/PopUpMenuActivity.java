package com.popupmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class PopUpMenuActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btn=(Button) findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				PopupMenu popup=new PopupMenu(getBaseContext(), v);
				MenuInflater inflater = popup.getMenuInflater();
			    inflater.inflate(R.menu.menuitem, popup.getMenu());
			    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
		            public boolean onMenuItemClick(MenuItem item) {
		                Toast.makeText(getBaseContext(), "Clicked popup menu item " + item.getTitle(),
		                        Toast.LENGTH_SHORT).show();
		                return true;
		            }
		        });
			    popup.show();
			}
		});
        
    }

	
}