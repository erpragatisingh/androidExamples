package pragati.singh.iimt;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class AccountDetails extends Activity {
	//Variables Declaration
	//private ERPDataSource datasource;
	String sIdate1="",sIdate2="",sIdate3="";
	 RadioButton oneInstallment,twoInstallment,threeInstallment;
	 TableLayout table=null;
	 Button btn=null;
	 EditText etBal=null,etFee=null;
	 TextView tvBal=null;
	 int rowNo, myYear, myMonth, myDay;
	 static final int ID_DATEPICKER = 0;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//show appliaction on full screen
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount);
        
      /*  datasource=new ERPDataSource(this);
		try {
			datasource.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
        //Initialize Widgets
        btn=(Button)findViewById(R.id.btnSubmit);
        etBal=(EditText)findViewById(R.id.editTextBalance);
        etFee=(EditText)findViewById(R.id.editTextFee);
        tvBal=(TextView)findViewById(R.id.txtBalance);
        oneInstallment=(RadioButton)findViewById(R.id.rbOne);
        twoInstallment=(RadioButton)findViewById(R.id.rbTwo);
        threeInstallment=(RadioButton)findViewById(R.id.rbThree);
       
        //Add Values in Spinner
        Spinner spinner=(Spinner)findViewById(R.id.spCourse);
        ArrayAdapter<?> adapter=ArrayAdapter.createFromResource(this, R.array.Course,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        
        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        etBal.setText(currentDateTimeString);
       
    }
    
   //Called when Radio button clicked
    public void PayInstallment(View v)
    {
    	 if(!(etFee.getText().toString().equals("") ))
         {
    		  table = (TableLayout) findViewById( R.id.tblInstallment ); 
	      	  table.removeAllViews();
		      if(oneInstallment.isChecked())
		      {		    	 
		      	  LoadDynamicRow();
		      }
		       else if(twoInstallment.isChecked())
		      {
		    	   LoadDynamicRow();
		    	   LoadDynamicRow();	
		      }
		      else
		      {
		    	  LoadDynamicRow();
		    	  LoadDynamicRow();
		    	  LoadDynamicRow();
			    
		      }
		      btn.setVisibility(Button.VISIBLE);
		      etBal.setVisibility(EditText.VISIBLE);
		      tvBal.setVisibility(TextView.VISIBLE);
         }
    	 else
    	 { Toast.makeText(this, "Enter Fee Details", Toast.LENGTH_SHORT).show();
    	
    	 }
      
           
    }
   //Function- dynamically created row for installment
	public void LoadDynamicRow()
    {    
	       table = (TableLayout) findViewById( R.id.tblInstallment ); 
	    	
	       int ctlInRow = 0; 
	       int numRows = table.getChildCount(); 
	       TableRow row = null; 
       
	       if( numRows > 0 ){ 
	            row = (TableRow) table.getChildAt( numRows - 1 ); 
	            ctlInRow = row.getChildCount();   
	           
	        } 
	       
	        if( numRows == 0 || ctlInRow == 5 ){         	
	            row = new TableRow( this );
	            table.addView( row ); 
	            ctlInRow = 0; 
	        }
	        //Installment EditText
           	EditText etI = new EditText(this);
            etI.setTag("etInstall"+numRows);
            etI.setHint(numRows+1+"Installment");
            etI.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            
           /* ViewGroup layout=new LinearLayout(this);
            layout.setLayoutParams(new   ViewGroup.LayoutParams(80, 40));
            layout.addView(etI);
            row.addView(layout);*/
            row.addView(etI,80,40 );
           
                    
            
            //* TextView
        	TextView tv = new TextView(this);
        	tv.setText("*");
        	row.addView(tv);
            
        	//Tax EditText
        	EditText etTax = new EditText(this);
        	etTax.setTag("etTax"+numRows);
        	etTax.setHint("Tax");
        	etTax.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            row.addView(etTax, 40,40 );
            
        	//Total EditText
        	EditText etTotal = new EditText(this);
        	etTotal.setTag("etTotal"+numRows);
        	etTotal.setHint("Total");
        	etTotal.setOnFocusChangeListener(OnChangeDoSomething(etTotal,numRows));
        	etTotal.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            row.addView(etTotal, 70,40 );
           
            //Calender Button which open DatePicker in Dialog Box
            Button btnDate = new Button(this);
            btnDate.setTag("btnDate"+numRows);
            btnDate.setBackgroundResource(R.drawable.calender);
            btnDate.setOnClickListener(datePickerButtonOnClickListener);
            row.addView(btnDate,40,35);
    }               
    
	//DatePicker OnClick Listener
	private Button.OnClickListener datePickerButtonOnClickListener = new Button.OnClickListener()
	{
		   @Override
		   public void onClick(View v) {
		    // TODO Auto-generated method stub
		    final Calendar c = Calendar.getInstance();
		    
		    myYear = c.get(Calendar.YEAR);
		    myMonth = c.get(Calendar.MONTH);
		    myDay = c.get(Calendar.DAY_OF_MONTH);
		   		   
		    showDialog(ID_DATEPICKER);
		   }
	};

    //Called when showDialog(ID_DATEPICKER) invoked
    @Override
    protected Dialog onCreateDialog(int id)
    {
     // TODO Auto-generated method stub
     switch(id){
      case ID_DATEPICKER:
          return new DatePickerDialog(this, myDateSetListener, myYear, myMonth, myDay);
      default:
          return null;
     }
    }
    
  //the callback received when the user "sets" the date in the dialog
    private  DatePickerDialog.OnDateSetListener myDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
      @Override
      public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
      {
       // TODO Auto-generated method stub
    	  
    	 String date = String.valueOf(year) + "-"
                     + String.valueOf(monthOfYear+1) + "-"
                     + String.valueOf(dayOfMonth);
    	 
    	
    	 /*if(oneInstallment.isChecked())
	      {		    	 
    		 sIdate1=date;
	      }
	       else if(twoInstallment.isChecked())
	      {
	    	   sIdate2=date;	
	      }
	      else
	      {
	    	  sIdate3=date;
		    
	      }*/
    	   Toast.makeText(AccountDetails.this, date, Toast.LENGTH_LONG).show();
	        
    	   //tvBal.setText(sIdate1 +" "+ sIdate2+" "+sIdate3);
           
      } 
    };

	//Calculate Total=installment*tax when Total EditText Got Focus
	View.OnFocusChangeListener OnChangeDoSomething(final EditText etTotal,final int i) {
		
		return new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				float tax;
				float intallment,Total;
				
				 table = (TableLayout) findViewById( R.id.tblInstallment );
				 EditText etIntall1 = (EditText)table.findViewWithTag("etInstall"+i);
				 EditText etTotal1 = (EditText)table.findViewWithTag("etTotal"+i);
				 EditText etTax1 = (EditText)table.findViewWithTag("etTax"+i);
				 if(!(etIntall1.getText().toString().equals("") ) && !(etTax1.getText().toString().equals("") ))
				 {
					 intallment = Float.parseFloat(etIntall1.getText().toString());
					 tax = Float.parseFloat(etTax1.getText().toString());
					 //Toast.makeText(getApplicationContext(), "HI I M "+etTotal.getText().toString(), Toast.LENGTH_SHORT).show();
					 Total = (intallment + ((intallment*tax)/100));
					 etTotal1.setText(""+Total);
				 }
				 			
			}
		};
	}
	
	public void InsertData(View v)
	{
		//datasource.insertRecords(ID, sENo, sCourse, sFee, sFInstall, sSInstall, sTInstall, fInstallDt, sInstallDt, tInstallDt);
		
		//Toast.makeText(this, "Submit button click", Toast.LENGTH_SHORT).show();
	}
	
	public void home(View v)
	{
		Intent i=new Intent(this, ProFile1Activity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}
	
    
}