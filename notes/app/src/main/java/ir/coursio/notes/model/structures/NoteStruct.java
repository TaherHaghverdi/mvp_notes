package ir.coursio.notes.model.structures;

import android.database.Cursor;

import ir.coursio.notes.model.db.DataContract;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */

public class NoteStruct {
    private String id;
    private String title;
    private String text;
    private String folderId;
    private byte[] drawing;

    public NoteStruct(Cursor data) {
        id= data.getString(data.getColumnIndex(DataContract.NoteEntry._ID));
        title=data.getString(data.getColumnIndex(DataContract.NoteEntry.COLUMN_NOTE_TITLE));
        text =data.getString(data.getColumnIndex(DataContract.NoteEntry.COLUMN_NOTE_TEXT));
        folderId=data.getString(data.getColumnIndex(DataContract.NoteEntry.COLUMN_FOLDER_ID));
        drawing=data.getBlob(data.getColumnIndex(DataContract.NoteEntry.COLUMN_NOTE_DRAW));
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getFolderId() {
        return folderId;
    }

    public byte[] getDrawing() {
        return drawing;
    }
}
