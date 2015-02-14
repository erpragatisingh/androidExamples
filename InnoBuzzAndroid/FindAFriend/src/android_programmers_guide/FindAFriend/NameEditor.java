package android_programmers_guide.FindAFriend;


import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class NameEditor extends Activity implements View.OnClickListener {

    public static final String EDIT_NAME_ACTION =
        "android_programmers_guide.FindAFriend.action.EDIT_NAME";

    private static final int NAME_INDEX = 1;

    private static final String[] PROJECTION = new String[] {
            Friends.Friend._ID, 
            Friends.Friend.NAME, 
    };
    
    Cursor mCursor;

    EditText mText;
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.name_editor);
        Uri uri = getIntent().getData();
        mCursor = managedQuery(uri, PROJECTION, null, null);
        mText = (EditText) this.findViewById(R.id.name);
        mText.setOnClickListener(this);
        Button b = (Button) findViewById(R.id.ok);
        b.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mCursor != null) {
            mCursor.first();
            String title = mCursor.getString(NAME_INDEX);
            mText.setText(title);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mCursor != null) {
            String title = mText.getText().toString();
            mCursor.updateString(NAME_INDEX, title);
            mCursor.commitUpdates();
        }
    }

    public void onClick(View v) {
         finish();
    }
}
