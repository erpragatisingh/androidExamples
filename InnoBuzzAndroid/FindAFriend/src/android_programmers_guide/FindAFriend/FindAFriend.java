package android_programmers_guide.FindAFriend;


import android_programmers_guide.FindAFriend.Friends;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class FindAFriend extends ListActivity {
    
    public static final int DELETE_ID = Menu.FIRST;
    public static final int INSERT_ID = Menu.FIRST + 1;
    public static final int FIND_FRIENDS = Menu.FIRST + 2;

    private static final String[] PROJECTION = new String[] {
            Friends.Friend._ID, Friends.Friend.NAME};
    
     private Cursor mCursor;


    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setDefaultKeyMode(SHORTCUT_DEFAULT_KEYS);

        Intent intent = getIntent();
        if (intent.getData() == null) {
            intent.setData(Friends.Friend.CONTENT_URI);
        }
        
        setupList();

        mCursor = managedQuery(getIntent().getData(), PROJECTION, null, null);
        
        ListAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.findafriend_item, mCursor,
                new String[] {Friends.Friend.NAME}, new int[] {android.R.id.text1});
        setListAdapter(adapter);
        
    }

    private void setupList() {
        View view = getViewInflate().inflate(
                android.R.layout.simple_list_item_1, null, null);

        TextView v = (TextView) view.findViewById(android.R.id.text1);
        v.setText("X");
        getListView().setBackgroundColor(Color.GRAY);
        v.measure(MeasureSpec.makeMeasureSpec(View.MeasureSpec.EXACTLY, 100),
                MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, 0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, INSERT_ID, R.string.menu_insert).setShortcut('3', 'a');

        Intent intent = new Intent(null, getIntent().getData());
        intent.addCategory(Intent.ALTERNATIVE_CATEGORY);
        menu.addIntentOptions(
            Menu.ALTERNATIVE, 0, new ComponentName(this, FindAFriend.class),
            null, intent, 0, null);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        final boolean haveItems = mCursor.count() > 0;

        if (haveItems) {
            Uri uri = ContentUris.withAppendedId(getIntent().getData(), getSelectedItemId());

            Intent[] specifics = new Intent[1];
            specifics[0] = new Intent(Intent.EDIT_ACTION, uri);
            Menu.Item[] items = new Menu.Item[1];

            Intent intent = new Intent(null, uri);
            intent.addCategory(Intent.SELECTED_ALTERNATIVE_CATEGORY);
            menu.addIntentOptions(Menu.SELECTED_ALTERNATIVE, 0, null, specifics,
                                  intent, 0, items);

            menu.add(Menu.SELECTED_ALTERNATIVE, DELETE_ID, R.string.menu_delete)
                    .setShortcut('2', 'd');
            menu.add(Menu.SELECTED_ALTERNATIVE, FIND_FRIENDS, R.string.find_friends).setShortcut('4', 'f');
            if (items[0] != null) {
                items[0].setShortcut('1', 'e');
            }
        } else {
            menu.removeGroup(Menu.SELECTED_ALTERNATIVE);
        }

        menu.setItemShown(DELETE_ID, haveItems);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(Menu.Item item) {
        switch (item.getId()) {
        case DELETE_ID:
            deleteItem();
            return true;
        case INSERT_ID:
            insertItem();
            return true;
        case FIND_FRIENDS:
        	Intent findfriends = new Intent(this, FriendsMap.class);
        	startActivity(findfriends);
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Uri url = ContentUris.withAppendedId(getIntent().getData(), id);
        
        String action = getIntent().getAction();
        if (Intent.PICK_ACTION.equals(action)
                || Intent.GET_CONTENT_ACTION.equals(action)) {
            setResult(RESULT_OK, url.toString());
        } else {
            startActivity(new Intent(Intent.EDIT_ACTION, url));
        }
    }

    private final void deleteItem() {
        mCursor.moveTo(getSelectedItemPosition());
        mCursor.deleteRow();
    }

    private final void insertItem() {
        startActivity(new Intent(Intent.INSERT_ACTION, getIntent().getData()));
    }
}