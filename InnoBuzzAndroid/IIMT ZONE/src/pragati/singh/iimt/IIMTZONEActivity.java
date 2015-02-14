package pragati.singh.iimt;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
//import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IIMTZONEActivity extends Activity {
	private statusDataSource datasource;
	
	Button submit;
	EditText enroll,name,company,tech,pos,pack;
	String name1,company1,tech1,pos1;
	String enroll1,pack1;
	SQLiteDatabase db;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placement);
        //Open the Data base
        datasource = new statusDataSource(this);
        datasource.open();
        
        enroll=(EditText)findViewById(R.id.e1);
        name=(EditText)findViewById(R.id.e2);
        company=(EditText)findViewById(R.id.e3);
        tech=(EditText)findViewById(R.id.e4);
        pos=(EditText)findViewById(R.id.e5);
        pack=(EditText)findViewById(R.id.e6);
        submit=(Button)findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
		public void onClick(View v) {
				// TODO Auto-generated method stub
			enroll1=String.valueOf(enroll.getText().toString());
			name1=String.valueOf(name.getText().toString());
			company1=String.valueOf(company.getText().toString());
			tech1=String.valueOf(tech.getText().toString());
			pos1=String.valueOf(pos.getText().toString());
			pack1=String.valueOf(pack.getText().toString());
			
			Map<String,String> l_info_map = new HashMap<String,String>();
			l_info_map.put("package",pack1);
			l_info_map.put("position",pos1);
			l_info_map.put("technology",tech1);
			l_info_map.put("company",company1);
			l_info_map.put("name",name1);
			l_info_map.put("enrollment_number",enroll1);
			

			if(!validateInputInformation(l_info_map)){
				return;				
			}
			
			placeInsert(enroll1,name1,company1,tech1,pos1,pack1);
			}

			private boolean validateInputInformation(
					Map<String, String> p_info_map) {
				for(String l_info : p_info_map.keySet()) {
					String l_info_value = p_info_map.get(l_info);
					if(l_info_value == null || l_info_value.equals("")) {
						String l_msg = "Please fill "+ l_info+ " First";
						Toast.makeText(getApplicationContext(), l_msg, Toast.LENGTH_SHORT).show();
						return false;					
					}
					
				}
				
				return true;
			}
		});
        
    }
	protected void placeInsert(String enroll12, String name12, String company12,
			String tech12, String pos12, String pack12) {
		
		long placeValue=datasource.insertPlacementStatus(enroll12,name12,company12,tech12,pos12,pack12);
		Toast.makeText(getApplicationContext(), "DATA INSERTED SUCCESSFULLY AT INDEX "+placeValue, Toast.LENGTH_LONG).show();
		
	}
	
	public void menuCalling(View v)
	{
		Intent intent=new Intent(this,ProFile1Activity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
	}
}