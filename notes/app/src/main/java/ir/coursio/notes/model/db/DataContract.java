package ir.coursio.notes.model.db;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 * contains folders static fields based on google schema explained here:
 * https://developer.android.com/training/basics/data-storage/databases.html#DefineContract
 */

public class DataContract {

    public static final String CONTENT_AUTHORITY = "ir.coursio.notes";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_FOLDERS = "folders";
    public static final String PATH_NOTES = "notes/";


    //Contract class cannot be initiated
    private DataContract() {
    }

    /* Inner class that defines the table contents for folders */
    public static abstract class FoldersEntry implements BaseColumns {
        public final static String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "folders";
        public static final String COLUMN_FOLDER_NAME = "folder_name";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_FOLDERS);
        public static final Uri CONTENT_URI_NOTES = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NOTES + PATH_FOLDERS);
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FOLDERS;
    }

    /* Inner class that defines the table contents for notes */
    public static abstract class NoteEntry implements BaseColumns {
        public final static String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NOTE_TITLE = "note_title";
        public static final String COLUMN_NOTE_TEXT = "note_text";
        public static final String COLUMN_NOTE_DRAW = "note_draw";
        public static final String COLUMN_FOLDER_ID = "cat_id";
    }
}
