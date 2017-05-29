package ir.coursio.notes.model.structures;

import android.database.Cursor;

import ir.coursio.notes.model.db.DataContract;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public class FolderStruct {
    private String id;
    private String name;

    public FolderStruct(Cursor cursor) {
        this.id = cursor.getString(cursor.getColumnIndex(DataContract.FoldersEntry._ID));
        this.name = cursor.getString(cursor.getColumnIndex(DataContract.FoldersEntry.COLUMN_FOLDER_NAME));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
