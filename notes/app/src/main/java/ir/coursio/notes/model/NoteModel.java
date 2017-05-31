package ir.coursio.notes.model;

import android.app.Activity;
import android.content.Intent;

import ir.coursio.notes.AddDrawingActivity;
import ir.coursio.notes.AddNoteActivity;
import ir.coursio.notes.model.structures.NoteStruct;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */

public class NoteModel {
    // Static strings to identify intent extras
    public static final String FOLDER_ID = "folder_id";
    public static final String FOLDER_NAME = "folder_name";

    private final Activity activity;

    public NoteModel(Activity activity) {
        this.activity = activity;
    }

    public void newTextNoteRequest() {
        Intent intent = new Intent(activity, AddNoteActivity.class);
        intent.putExtra(FOLDER_ID, activity.getIntent().getStringExtra(FOLDER_ID));
        activity.startActivity(intent);
    }

    public void newDrawingNoteRequest() {
        Intent intent = new Intent(activity, AddDrawingActivity.class);
        intent.putExtra(FOLDER_ID, activity.getIntent().getStringExtra(FOLDER_ID));
        activity.startActivity(intent);
    }

    public void editNoteRequest(NoteStruct note) {
        if (!note.isPainting()) {
            Intent intent = new Intent(activity, AddNoteActivity.class);
            intent.putExtra(AddNoteModel.FOLDER_ID, note.getFolderId());
            intent.putExtra(AddNoteModel.IS_EDITING, true);
            intent.putExtra(AddNoteModel.NOTE, note);
            activity.startActivity(intent);
        } else {
            Intent intent = new Intent(activity, AddDrawingActivity.class);
            intent.putExtra(AddDrawingModel.FOLDER_ID, note.getFolderId());
            intent.putExtra(AddDrawingModel.IS_EDITING, true);
            intent.putExtra(AddDrawingModel.NOTE, note);
            activity.startActivity(intent);
        }
    }

    /**
     * Gets current folder's name.
     *
     * @return The String folder's name
     */
    public String getFolderName() {
        return activity.getIntent().getStringExtra(FOLDER_NAME);
    }
}
