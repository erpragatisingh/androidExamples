package pragati.singh.iimt;

import android.content.ContentValues;
import android.content.Context;
//import android.database.Cursor;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class statusDataSource {
	// Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
   // private String[] allColumns = { DBHelper.C_ID ,DBHelper.C_CREATEDAT,DBHelper.C_USER		};

  public statusDataSource(Context context) {
        dbHelper = new DBHelper(context);
  }
    public void open() throws SQLException {
          database = dbHelper.getWritableDatabase();
    }

    public void close() {
          dbHelper.close();
    }

    public long insertPlacementStatus(String stu_id, String stu_name, String stu_com, String stu_tech, String stu_pos, String stu_pack ) {
    	
          ContentValues values = new ContentValues();
          values.put(DBHelper.C_ID, stu_id);
          values.put(DBHelper.C_STU_NAME, stu_name);
          values.put(DBHelper.C_COMP_NAME, stu_com);
          values.put(DBHelper.C_TECHNOLOGY, stu_tech);
          values.put(DBHelper.C_POSITION, stu_pos);
          values.put(DBHelper.C_PACKAGE, stu_pack);
          long insertId = database.insert(DBHelper.S_TABLE, null,values);
         // if(insertId < 0) {
        	  //database.execSQL(DBHelper.DATABASE_CREATE);  
        	 
        	//  insertId = database.insert(DBHelper.S_TABLE, null,values);
          //}
        	
        
        
          return insertId;
    }

   
    public long insertEnquiry(String ID,String NAME,String EMAIL,String MNO,String DBO,String REMARK) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.C_EID, ID);
        values.put(DBHelper.C_NAME, NAME);
        values.put(DBHelper.C_USEREMAIL, EMAIL);
        values.put(DBHelper.C_MNO, MNO);
		values.put(DBHelper.C_DOB, DBO);
		values.put(DBHelper.C_REMARK, REMARK);
        long insertId = database.insert(DBHelper.TABENQUIRY, null,values);
        return insertId;
  }
	public Cursor fetchQueriedRow(int id) {
			String sql = "SELECT ename,eemail,emNO FROM " + DBHelper.TABENQUIRY +" WHERE _id = " +id;
			Cursor cursor;
			cursor= database.rawQuery(sql, null);
			return cursor;
		}
		
	}
	


