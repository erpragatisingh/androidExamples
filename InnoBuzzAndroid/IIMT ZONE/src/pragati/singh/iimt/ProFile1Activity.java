package pragati.singh.iimt;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProFile1Activity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String forms[]={
        		"ENQUIRY FORM",
        		"REGISTRATION FORM",
        		"ACCOUNT DETAIL",
        		"PLACEMENT DETAILS",
        		"FACULTY INFORMATION",
        		"CAMPUS GALARY",
        		"UNIVERSITY LOGIN",
        		"COLLEGE LOGIN",
        		"UNIVERSITY RESUT",
        		"College Placement Portal"
        		};
        setContentView(R.layout.main);
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,forms);
        
        setListAdapter(adp);
        
    }
    
    public void onListItemClick(ListView lv,View v,int pos,long id)
    {
    	Intent i=null;
    	super.onListItemClick(lv, v, pos, id);
    	switch(pos)
    	{
    	case 0:
    		i=new Intent(this,EnquiryActivity.class);
        	startActivity(i);
        	break;
    	case 1:
    		i=new Intent(this,registration_db.class);
        	startActivity(i);
        	   		
    		
    		//Toast.makeText(this, "Page is UnderConstruction", Toast.LENGTH_SHORT).show();
    		break;
        case 2:
        	i= new Intent(this, AccountDetails.class);
        	startActivity(i);
        	break;
    	case 3:
    		i=new Intent(getApplicationContext(),IIMTZONEActivity.class);
    		startActivity(i);
    		break;
    	case 4:
    		i=new Intent(this , facultyDetails.class);
    		startActivity(i);
    		break;
    	case 5:
    		i=new Intent(this , GridviewDemoActivity.class);
    		startActivity(i);
    		break;
    	case 6:
    		i=new Intent(this , unilogin.class);
    		startActivity(i);
    		break;
    	case 7:
    		i=new Intent(this , unilogin .class);
    		startActivity(i);
    		break;
    	case 8:
    		i=new Intent(this , upturesult.class);
    		startActivity(i);
    		break;    		
    	case 9:
    		i=new Intent(this , placementPortalclg.class);
    		startActivity(i);
    		break;
    	 
    	 
    	}
    	
    }

	@Override
	public boolean onCreateOptionsMenu(Menu m) {
		// TODO Auto-generated method stub
		MenuInflater inflator=getMenuInflater();
		inflator.inflate(R.menu.mymenu, m);
		return true;
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i=null;
		switch(item.getItemId())
		{
		case R.id.search:
			i=new Intent(this,search.class);
			startActivity(i);
			break;
			
		case R.id.delete:
			break;
			
		case R.id.update:
			break;
		}
		return true;
	}
}