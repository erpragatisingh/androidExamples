package android_programmers_guide.FindAFriend;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Friends {
    public static final class Friend implements BaseColumns {
        public static final Uri CONTENT_URI
                = Uri.parse("content://android_programmers_guide.FindAFriend.Friends/friend");

        public static final String DEFAULT_SORT_ORDER = "modified DESC";

        public static final String NAME = "name";

        public static final String LOCATION = "location";

        public static final String CREATED_DATE = "created";

        public static final String MODIFIED_DATE = "modified";
    }
}
