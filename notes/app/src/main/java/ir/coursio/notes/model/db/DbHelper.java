package ir.coursio.notes.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 * Database upgrade policy is to discard the data and start over
 */

public class DbHelper extends SQLiteOpenHelper {

    //Database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notes.db";

    //Util Strings
    private static final String TEXT_TYPE = " TEXT";
    private static final String BLOB_TYPE = " BLOB";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String PRIMARY_KEY = " PRIMARY KEY AUTOINCREMENT";
    private static final String COMMA_SEP = ",";

    //Queries
    private static final String QUERY_CREATE_NOTES =
            "CREATE TABLE " + DataContract.NoteEntry.TABLE_NAME + " (" +
                    DataContract.NoteEntry._ID +
                    INTEGER_TYPE +
                    PRIMARY_KEY +
                    COMMA_SEP +
                    DataContract.NoteEntry.COLUMN_NOTE_TITLE +
                    TEXT_TYPE + COMMA_SEP +
                    DataContract.NoteEntry.COLUMN_NOTE_TEXT +
                    TEXT_TYPE + COMMA_SEP +
                    DataContract.NoteEntry.COLUMN_NOTE_DRAW +
                    BLOB_TYPE + COMMA_SEP +
                    DataContract.NoteEntry.COLUMN_CAT_ID +
                    INTEGER_TYPE +
                    COMMA_SEP +
                    " FOREIGN KEY (" + DataContract.NoteEntry.COLUMN_CAT_ID
                    + ") REFERENCES " + DataContract.FoldersEntry.TABLE_NAME
                    + "(" + DataContract.FoldersEntry._ID + "));";

    private static final String QUERY_CREATE_FOLDERS =
            "CREATE TABLE " + DataContract.FoldersEntry.TABLE_NAME + " (" +
                    DataContract.FoldersEntry._ID +
                    INTEGER_TYPE +
                    PRIMARY_KEY +
                    COMMA_SEP +
                    DataContract.FoldersEntry.COLUMN_FOLDER_TITLE +
                    TEXT_TYPE +
                    " )";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_FOLDERS);
        db.execSQL(QUERY_CREATE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.FoldersEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.NoteEntry.TABLE_NAME);
        onCreate(db);

    }
}
