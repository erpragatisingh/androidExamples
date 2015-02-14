package pragati.singh.iimt;

import pragati.singh.iimt.DBHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{
	//private static final String FILE_NAME = "D://config//db.properties";
	private static final String TAG = DBHelper.class.getSimpleName();
	public static final String DB_NAME = "education.db";
	public static final int DB_VERSION = 1;
	public static final String S_TABLE = "placement";
	public static final String C_ID = BaseColumns._ID ; //BaseColumns._ID specially for id BaseColumns
	public static final String C_STU_NAME = "Name";
	public static final String C_COMP_NAME = "Company_Name";
	public static final String C_TECHNOLOGY = "Technology";
	public static final String C_POSITION = "Position";
	public static final String C_PACKAGE = "Package";
	//public static final String C_TEXT = "text";
	///Another Table
	public static  String TABENQUIRY = "enquiry";
	public static final String C_EID = "_id"; //BaseColumns._ID specially for id BaseColumns
	public static final String C_NAME = "ename";
	public static final String C_USEREMAIL = "eemail";
	public static final String C_MNO = "emNo";
	public static final String C_DOB = "edob";
	public static final String C_COURSE="ecourse";
	public static final String C_REMARK = "eremark";
	
   	  Context context;
	  public DBHelper(Context context) {
		  super(context, DB_NAME, null, DB_VERSION);
		 /* PropertyReader l_property = new PropertyReader();
		  try {
			l_property.readProperties(FILE_NAME);
			TABENQUIRY = (String)l_property.getProperty("TABLE.NAME");
		} catch (PropertyFileNotFoundException e) {
			e.printStackTrace();
		}*/
    }
	 public static final String DATABASE_CREATE = "create table "
         + S_TABLE + "(" + C_ID
         + " integer primary key, " + C_STU_NAME + " text not null, " 
         + C_COMP_NAME + " text not null, "
         + C_TECHNOLOGY + " text, "
         +C_POSITION + " text, "
         +C_PACKAGE + " integer);";
	 
	 private static final String TABENQUIRY_CREATE = "create table "
         + TABENQUIRY + "(" + C_EID
         + " integer primary key, " + C_NAME + " text not null, "
         + C_USEREMAIL + " text not null, "
         + C_MNO + " text, "
         + C_DOB + " text, "
         + C_COURSE + "text, "
         + C_REMARK + " text not null);";
	 
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(DATABASE_CREATE);
		Log.e(TAG,"TABLE CREATED");
		//Toast.makeText(DBHelper.this, "table ceated.", Toast.LENGTH_LONG);
		db.execSQL(TABENQUIRY_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists "+ S_TABLE);
		db.execSQL("drop table if exists "+ TABENQUIRY);
		this.onCreate(db);
		
	}

}
