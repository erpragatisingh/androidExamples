package android_programmers_guide.FindAFriend;

import android_programmers_guide.FindAFriend.Friends;
import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

public class FriendsProvider extends ContentProvider {

    private SQLiteDatabase mDB;

    private static final String TAG = "FriendsProvider";
    private static final String DATABASE_NAME = "friends";
    private static final int DATABASE_VERSION = 2;

    private static HashMap<String, String> FRIENDS_PROJECTION_MAP;

    private static final int FRIENDS = 1;
    private static final int FRIENDS_ID = 2;

    private static final UriMatcher URL_MATCHER;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE friends (_id INTEGER PRIMARY KEY,"
                    + "name TEXT," + "location TEXT," + "created INTEGER,"
                    + "modified INTEGER" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS friends");
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        mDB = dbHelper.openDatabase(getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        return (mDB == null) ? false : true;

    }

    @Override
    public Cursor query(Uri url, String[] projection, String selection,
            String[] selectionArgs, String sort) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        switch (URL_MATCHER.match(url)) {
        case FRIENDS:
            qb.setTables("friends");
            qb.setProjectionMap(FRIENDS_PROJECTION_MAP);
            break;

        case FRIENDS_ID:
            qb.setTables("friends");
            qb.appendWhere("_id=" + url.getPathSegments().get(1));
            break;

        default:
            throw new IllegalArgumentException("Unknown URL " + url);
        }

        String orderBy;
        if (TextUtils.isEmpty(sort)) {
            orderBy = Friends.Friend.DEFAULT_SORT_ORDER;
        } else {
            orderBy = sort;
        }

        Cursor c = qb.query(mDB, projection, selection, selectionArgs, null, null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), url);
        return c;
    }

    @Override
    public String getType(Uri url) {
        switch (URL_MATCHER.match(url)) {
        case FRIENDS:
            return "vnd.android.cursor.dir/vnd.android_programmers_guide.friend";

        case FRIENDS_ID:
            return "vnd.android.cursor.item/vnd.android_programmers_guide.friend";

        default:
            throw new IllegalArgumentException("Unknown URL " + url);
        }
    }

    @Override
    public Uri insert(Uri url, ContentValues initialValues) {
        long rowID;
        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }

        if (URL_MATCHER.match(url) != FRIENDS) {
            throw new IllegalArgumentException("Unknown URL " + url);
        }

        Long now = Long.valueOf(System.currentTimeMillis());
        Resources r = Resources.getSystem();

        if (values.containsKey(Friends.Friend.CREATED_DATE ) == false) {
            values.put(Friends.Friend.CREATED_DATE, now);
        }

        if (values.containsKey(Friends.Friend.MODIFIED_DATE) == false) {
            values.put(Friends.Friend.MODIFIED_DATE, now);
        }

        if (values.containsKey(Friends.Friend.NAME) == false) {
            values.put(Friends.Friend.NAME, r.getString(android.R.string.untitled));
        }

        if (values.containsKey(Friends.Friend.LOCATION) == false) {
            values.put(Friends.Friend.LOCATION , "");
        }

        rowID = mDB.insert("friends", "friend", values);
        if (rowID > 0) {
            Uri uri = ContentUris.withAppendedId(Friends.Friend.CONTENT_URI , rowID);
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        }

        throw new SQLException("Failed to insert row into " + url);
    }

    @Override
    public int delete(Uri url, String where, String[] whereArgs) {
        int count;
        long rowId = 0;
        switch (URL_MATCHER.match(url)) {
        case FRIENDS:
            count = mDB.delete("friends", where, whereArgs);
            break;

        case FRIENDS_ID:
            String segment = url.getPathSegments().get(1);
            rowId = Long.parseLong(segment);
            count = mDB
                    .delete("friends", "_id="
                            + segment
                            + (!TextUtils.isEmpty(where) ? " AND (" + where
                                    + ')' : ""), whereArgs);
            break;

        default:
            throw new IllegalArgumentException("Unknown URL " + url);
        }

        getContext().getContentResolver().notifyChange(url, null);
        return count;
    }

    @Override
    public int update(Uri url, ContentValues values, String where, String[] whereArgs) {
        int count;
        switch (URL_MATCHER.match(url)) {
        case FRIENDS:
            count = mDB.update("friends", values, where, whereArgs);
            break;

        case FRIENDS_ID:
            String segment = url.getPathSegments().get(1);
            count = mDB
                    .update("friends", values, "_id="
                            + segment
                            + (!TextUtils.isEmpty(where) ? " AND (" + where
                                    + ')' : ""), whereArgs);
            break;

        default:
            throw new IllegalArgumentException("Unknown URL " + url);
        }

        getContext().getContentResolver().notifyChange(url, null);
        return count;
    }

    static {
        URL_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URL_MATCHER.addURI("android_programmers_guide.FindAFriend.Friends", "friend", FRIENDS);
        URL_MATCHER.addURI("android_programmers_guide.FindAFriend.Friends", "friend/#", FRIENDS_ID);

        FRIENDS_PROJECTION_MAP = new HashMap<String, String>();
        FRIENDS_PROJECTION_MAP.put(Friends.Friend._ID, "_id");
        FRIENDS_PROJECTION_MAP.put(Friends.Friend.NAME, "name");
        FRIENDS_PROJECTION_MAP.put(Friends.Friend.LOCATION, "location");
        FRIENDS_PROJECTION_MAP.put(Friends.Friend.CREATED_DATE, "created");
        FRIENDS_PROJECTION_MAP.put(Friends.Friend.MODIFIED_DATE, "modified");
    }
}