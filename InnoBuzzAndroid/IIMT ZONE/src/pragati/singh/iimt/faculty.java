package pragati.singh.iimt;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class faculty extends ListActivity{

	String faculty[]= {"Vipin Sir(Android)","Sathish Sir(Networking)","Hunny Sir(Networking)","Vivek Sir(Php)"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.faculty);
		ArrayAdapter<String> adp=new ArrayAdapter (this, android.R.layout.simple_list_item_1,faculty);
		setListAdapter(adp);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		switch(position)
		{
		case 0:
			Toast.makeText(getApplicationContext(),""+faculty[position],Toast.LENGTH_LONG).show();
			break;
		case 1:
			Toast.makeText(getApplicationContext(),""+faculty[position],Toast.LENGTH_LONG).show();
			break;
		case 2:
			Toast.makeText(getApplicationContext(),""+faculty[position],Toast.LENGTH_LONG).show();
			break;
		case 3:
			Toast.makeText(getApplicationContext(),""+faculty[position],Toast.LENGTH_LONG).show();
			break;
		
		}
	}
	
	

}
