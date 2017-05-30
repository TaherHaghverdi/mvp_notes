package ir.coursio.notes.model;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.Html;

import ir.coursio.notes.App;
import ir.coursio.notes.model.db.DataContract;
import ir.coursio.notes.model.structures.NoteStruct;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

public class AddNoteModel {
    private final Activity activity;
    // Static strings to identify intent extras
    public static final String NOTE = "note";
    public static final String FOLDER_ID = "folder_id";
    public static final String IS_EDITING = "is_editing";


    public AddNoteModel(Activity activity) {
        this.activity = activity;
    }

    /**
     * This method saves/updates a note to database. We save text as html in order to save bold and italic property.
     * If isEditing is true means user is editing an existing note otherwise user is making new note.
     *
     * @param title user note title
     * @param text  user note text
     */
    public void saveNote(String title, Editable text) {
        Intent intent = activity.getIntent();
        ContentValues values = new ContentValues();
        values.put(DataContract.NoteEntry.COLUMN_NOTE_TITLE, title);
        values.put(DataContract.NoteEntry.COLUMN_NOTE_TEXT, Html.toHtml(text));
        values.put(DataContract.NoteEntry.COLUMN_FOLDER_ID, intent.getStringExtra(FOLDER_ID));
        if (intent.getBooleanExtra(IS_EDITING, false)) {
            App.getAppContext().getContentResolver().
                    update(Uri.withAppendedPath(DataContract.FoldersEntry.CONTENT_URI_NOTES
                            , intent.getStringExtra(FOLDER_ID)), values,
                            DataContract.NoteEntry._ID + "=" + ((NoteStruct) intent.getParcelableExtra(NOTE)).getId(), null);
        } else {
            App.getAppContext().getContentResolver().insert(Uri.withAppendedPath(DataContract.FoldersEntry.CONTENT_URI_NOTES
                    , intent.getStringExtra(FOLDER_ID)), values);
        }

    }
}
