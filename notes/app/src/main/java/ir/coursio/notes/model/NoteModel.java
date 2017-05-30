package ir.coursio.notes.model;

import android.app.Activity;
import android.content.Intent;

import ir.coursio.notes.AddDrawingActivity;
import ir.coursio.notes.AddNoteActivity;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */

public class NoteModel {
    public static final String FOLDER_ID = "folder_id";

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

}
