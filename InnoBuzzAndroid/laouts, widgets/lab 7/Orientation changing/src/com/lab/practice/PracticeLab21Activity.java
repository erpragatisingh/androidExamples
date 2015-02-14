package com.lab.practice;

import android.app.Activity;
import android.os.Bundle;

public class PracticeLab21Activity extends Activity {
   // @Override
	//public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//return super.onCreateOptionsMenu(menu);
    /*	MenuInflater inflate = getMenuInflater();
    	inflate.inflate(R.menu.mymenu, menu);
    	return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//return super.onOptionsItemSelected(item);
		int id = item.getItemId();
		switch(item.getItemId()){
		case R.id.item1:
			break;
		case R.id.item2:
			break;
		}
		return true;
	}*/

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}