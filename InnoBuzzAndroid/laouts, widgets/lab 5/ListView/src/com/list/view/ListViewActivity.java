package com.list.view;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListViewActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] c=getResources().getStringArray(R.array.cities);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,c));
        
    }
}