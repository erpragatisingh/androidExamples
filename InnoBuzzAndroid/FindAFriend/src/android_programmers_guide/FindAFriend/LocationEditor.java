package android_programmers_guide.FindAFriend;

import android_programmers_guide.FindAFriend.Friends;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;

import java.util.Map;

public class LocationEditor extends Activity {

    private static final String TAG = "Friends";

    private static final int FRIEND_INDEX = 1;
    private static final int NAME_INDEX = 2;
    private static final int MODIFIED_INDEX = 3;

    private static final String[] PROJECTION = new String[] {
            Friends.Friend._ID, 
            Friends.Friend.LOCATION, 
            Friends.Friend.NAME, 
            Friends.Friend.MODIFIED_DATE 
    };
    
    private static final String ORIGINAL_CONTENT = "origContent";

    private static final int REVERT_ID = Menu.FIRST;
    private static final int DISCARD_ID = Menu.FIRST + 1;
    private static final int DELETE_ID = Menu.FIRST + 2;

    private static final int STATE_EDIT = 0;
    private static final int STATE_INSERT = 1;

    private int mState;
    private boolean mNoteOnly = false;
    private Uri mURI;
    private Cursor mCursor;
    private EditText mText;
    private String mOriginalContent;

    public static class MyEditText extends EditText {
        private Rect mRect;
        private Paint mPaint;

        public MyEditText(Context context, AttributeSet attrs, Map params) {
            super(context, attrs, params);
            
            mRect = new Rect();
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(0xFF0000FF);
        }
        
        @Override
        protected void onDraw(Canvas canvas) {

            int count = getLineCount();
            Rect r = mRect;
            Paint paint = mPaint;

            for (int i = 0; i < count; i++) {
                int baseline = getLineBounds(i, r);

                canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1,
                                paint);
            }

            super.onDraw(canvas);
        }
    }

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        final Intent intent = getIntent();
        final String type = intent.resolveType(this);

         final String action = intent.getAction();
        if (action.equals(Intent.EDIT_ACTION)) {
            mState = STATE_EDIT;
            mURI = intent.getData();

        } else if (action.equals(Intent.INSERT_ACTION)) {
            mState = STATE_INSERT;
            mURI = getContentResolver().insert(intent.getData(), null);

            if (mURI == null) {
                Log.e("Friends", "Failed to insert new note into "
                        + getIntent().getData());
                finish();
                return;
            }

            setResult(RESULT_OK, mURI.toString());

        } else {
            Log.e(TAG, "Unknown action, exiting");
            finish();
            return;
        }

        setContentView(R.layout.location_editor);
        
        mText = (EditText) findViewById(R.id.location);

        mCursor = managedQuery(mURI, PROJECTION, null, null);

        if (icicle != null) {
            mOriginalContent = icicle.getString(ORIGINAL_CONTENT);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mCursor != null) {
            mCursor.first();

            if (mState == STATE_EDIT) {
                setTitle(getText(R.string.title_edit));
            } else if (mState == STATE_INSERT) {
                setTitle(getText(R.string.title_create));
            }

            String note = mCursor.getString(FRIEND_INDEX);
            mText.setTextKeepState(note);
            
            if (mOriginalContent == null) {
                mOriginalContent = note;
            }

        } else {
            setTitle(getText(R.string.error_title));
            mText.setText(getText(R.string.error_message));
        }
    }

    @Override
    protected void onFreeze(Bundle outState) {
        outState.putString(ORIGINAL_CONTENT, mOriginalContent);
    }

    @Override
    protected void onPause() {
        super.onPause();

         if (mCursor != null) {
            String text = mText.getText().toString();
            int length = text.length();

            if (isFinishing() && (length == 0) && !mNoteOnly) {
                setResult(RESULT_CANCELED);
                deleteFriend();

            } else {
                if (!mNoteOnly) {
                    mCursor.updateLong(MODIFIED_INDEX, System.currentTimeMillis());

                    if (mState == STATE_INSERT) {
                        String title = text.substring(0, Math.min(30, length));
                        if (length > 30) {
                            int lastSpace = title.lastIndexOf(' ');
                            if (lastSpace > 0) {
                                title = title.substring(0, lastSpace);
                            }
                        }
                        mCursor.updateString(NAME_INDEX, title);
                    }
                }

                mCursor.updateString(FRIEND_INDEX, text);

                managedCommitUpdates(mCursor);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        if (mState == STATE_EDIT) {
            menu.add(0, REVERT_ID, R.string.menu_revert).setShortcut('0', 'r');
            if (!mNoteOnly) {
                menu.add(0, DELETE_ID, R.string.menu_delete).setShortcut('1', 'd');
            }

        } else {
            menu.add(0, DISCARD_ID, R.string.menu_discard).setShortcut('0', 'd');
        }

        if (!mNoteOnly) {
            Intent intent = new Intent(null, getIntent().getData());
            intent.addCategory(Intent.ALTERNATIVE_CATEGORY);
            menu.addIntentOptions(
                Menu.ALTERNATIVE, 0,
                new ComponentName(this, LocationEditor.class), null,
                intent, 0, null);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(Menu.Item item) {
        switch (item.getId()) {
        case DELETE_ID:
            deleteFriend();
            finish();
            break;
        case DISCARD_ID:
            cancelFriend();
            break;
        case REVERT_ID:
            cancelFriend();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private final void cancelFriend() {
        if (mCursor != null) {
            if (mState == STATE_EDIT) {
                mCursor.updateString(FRIEND_INDEX, mOriginalContent);
                mCursor.commitUpdates();
                mCursor.deactivate();
                mCursor = null;
            } else if (mState == STATE_INSERT) {
                deleteFriend();
            }
        }
        setResult(RESULT_CANCELED);
        finish();
    }

    private final void deleteFriend() {
        if (mCursor != null) {
            mText.setText("");
            mCursor.deleteRow();
            mCursor.deactivate();
            mCursor = null;
        }
    }
}