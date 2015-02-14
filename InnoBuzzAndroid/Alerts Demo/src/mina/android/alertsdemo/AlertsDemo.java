package mina.android.alertsdemo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.Duration;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AlertsDemo extends Activity {
    /** Called when the activity is first created. */
	Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn=(Button)findViewById(R.id.btn);
    }
    
    public void ClickHandler(View view)
    {
    	
    	ShowStandardAlert();
    	//ShowAdapterAlert();
    	//ShowListAlertSingleChoice();
    	//ShowItemsAlert();
    	//ShowListAlertMultipleChoice();
    	
    }
    
    void ShowItemsAlert()
    {
    	final String [] items=new String []{"Item 1","Item 2","Item 3","Item 4"};
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	builder.setTitle("Items alert");
    	
    	builder.setItems(items, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				TextView txt=(TextView)findViewById(R.id.txt);
				txt.setText(items[which]);
			}
		});
    	
    	builder.show();
    }
    
    
    void ShowListAlertMultipleChoice()
    {
    	final String [] items=new String []{"Item 1","Item 2","Item 3","Item 4"};
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	builder.setTitle("List alert");
    	builder.setMultiChoiceItems(items,null, new OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				TextView txt=(TextView)findViewById(R.id.txt);
				txt.setText(txt.getText()+" "+items[which]);
			}
		});
    	builder.setPositiveButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
    	builder.show();
    }
    
    void ShowListAlertSingleChoice()
    {
    	final String [] items=new String []{"Item 1","Item 2","Item 3","Item 4"};
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	builder.setTitle("List alert");
    	builder.setSingleChoiceItems(items, 0, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				TextView txt=(TextView)findViewById(R.id.txt);
				txt.setText(items[which]);
			}
		});
    	builder.setPositiveButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
    	builder.show();
    	
    }
    
    void ShowAdapterAlert()
    {
    	final String [] items=new String []{"Item 1","Item 2","Item 3","Item 4"};
    	ArrayAdapter<String> arr=new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,items);
    	
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	builder.setTitle("Adapter alert");
    	
    	builder.setAdapter(arr, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				TextView txt=(TextView)findViewById(R.id.txt);
				txt.setText(items[which]);
			}
		});
    	/*
    	builder.setPositiveButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
    	*/
    	builder.show();
    }
    
    void ShowStandardAlert()
    {
    	//declared as final to be able to reference it in inner class declartations of the handlers 
    	final AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	builder.setTitle("Alert Dialog");
    	//builder.setMessage("This is the alert's body");
    	final View bodyView=getLayoutInflater().inflate(R.layout.alertview, (ViewGroup)findViewById(R.id.toastView));
    	final View titleView=getLayoutInflater().inflate(R.layout.title, (ViewGroup)findViewById(R.id.titleView));
    	
    	Button btnAlertTitle=(Button)titleView.findViewById(R.id.btnAlertTitle);
    	
    	btnAlertTitle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView txtDate2=(TextView)titleView.findViewById(R.id.txtDate2);
				txtDate2.setText(Calendar.getInstance().getTime().toLocaleString());
			}
		});
    	
    	TextView txtDate=(TextView)bodyView.findViewById(R.id.txtTitle);
    	txtDate.setText(Calendar.getInstance().getTime().toLocaleString());
    	Button btnAlert=(Button)bodyView.findViewById(R.id.btnAlert);
    	btnAlert.setOnClickListener(new View.OnClickListener() {
    		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView txtDate=(TextView)bodyView.findViewById(R.id.txtTitle);
		    	txtDate.setText(Calendar.getInstance().getTime().toLocaleString());
		    	TextView txt=(TextView)findViewById(R.id.txt);
				txt.setText(Calendar.getInstance().getTime().toLocaleString());
			}
		});
    	builder.setView(bodyView);
    	builder.setCustomTitle(titleView);
    	builder.setIcon(android.R.drawable.ic_dialog_alert);
    	
    	builder.setPositiveButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				TextView txt=(TextView)findViewById(R.id.txt);
				txt.setText("You clicked Ok");
			}
		});
    	
    	builder.setNegativeButton("Cancel", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				TextView txt=(TextView)findViewById(R.id.txt);
				txt.setText("You clicked Cancel");
			}
		});
    	
    	builder.setNeutralButton("Do something", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				TextView txt=(TextView)findViewById(R.id.txt);
				txt.setText("Neutral Button Clicked");
				AlertDialog ad=builder.create();
				
				ad.cancel();
			}
		});
    	
    	builder.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				TextView txt=(TextView)findViewById(R.id.txt);
				txt.setText(txt.getText()+" the cancel listner invoked");
			}
		});
    	
    	
    	
    	builder.show();
    }
}