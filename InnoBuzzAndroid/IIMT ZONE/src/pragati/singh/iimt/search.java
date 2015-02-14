package pragati.singh.iimt;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class search extends Activity{
	EditText id;
	TextView resultTextview;
	Button search;
	statusDataSource status_Datasource;
	Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		status_Datasource = new statusDataSource(this);
		//statusDataSource.open();
		id=(EditText)findViewById(R.id.editText1);
		resultTextview=(TextView)findViewById(R.id.searchTextview);
		search=(Button)findViewById(R.id.button1);
		search.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				String Student_id=String.valueOf(id.getText());
				if(Student_id.equals(""))
				{
					Toast.makeText(getApplicationContext(), "please enter id", Toast.LENGTH_LONG).show();
				}
				
				else
				{
					status_Datasource.open();
					cursor = status_Datasource.fetchQueriedRow(Integer.parseInt(Student_id));
					if(cursor.getCount()>0)
					{
						cursor.moveToFirst();
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append(cursor.getString(cursor.getColumnIndex(DBHelper.C_NAME))+"\n");
						stringBuilder.append(cursor.getString(cursor.getColumnIndex(DBHelper.C_MNO))+"\n");
						stringBuilder.append(cursor.getString(cursor.getColumnIndex(DBHelper.C_USEREMAIL))+"\n");
						resultTextview.setText(stringBuilder.toString());
						status_Datasource.close();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "No Record In Database", Toast.LENGTH_LONG).show();
					}
				}
				
			}
		});
		
	}

}
