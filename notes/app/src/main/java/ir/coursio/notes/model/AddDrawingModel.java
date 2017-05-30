package ir.coursio.notes.model;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.ByteArrayOutputStream;

import ir.coursio.notes.App;
import ir.coursio.notes.R;
import ir.coursio.notes.model.db.DataContract;
import ir.coursio.notes.model.structures.NoteStruct;
import ir.coursio.notes.view.custom.DrawingView;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

public class AddDrawingModel {

    private final Activity activity;

    public static final String NOTE = "note";
    public static final String FOLDER_ID = "folder_id";
    public static final String IS_EDITING = "is_editing";

    public AddDrawingModel(Activity activity) {
        this.activity = activity;
    }

    /**
     * This method store user drawing as an array of bytes.
     * If isEditing is true means user is editing an existing note otherwise user is making new note.
     *
     * @param title       string that is title of drawing note
     * @param drawingView the view that contains user drawing input
     */
    public void saveDrawing(String title, DrawingView drawingView) {
        ContentValues values = new ContentValues();
        values.put(DataContract.NoteEntry.COLUMN_NOTE_TITLE, title);
        values.put(DataContract.NoteEntry.COLUMN_NOTE_DRAW, drawingView.getByteArray());
        values.put(DataContract.NoteEntry.COLUMN_FOLDER_ID, activity.getIntent().getStringExtra(FOLDER_ID));

        if (activity.getIntent().getBooleanExtra(IS_EDITING, false)) {
            App.getAppContext().getContentResolver().
                    update(Uri.withAppendedPath(DataContract.FoldersEntry.CONTENT_URI_NOTES
                            , activity.getIntent().getStringExtra(FOLDER_ID)), values, DataContract.NoteEntry._ID + "=" + ((NoteStruct) activity.getIntent().getParcelableExtra(NOTE)).getId(), null);
        } else {
            App.getAppContext().getContentResolver().insert(Uri.withAppendedPath(DataContract.FoldersEntry.CONTENT_URI_NOTES
                    , activity.getIntent().getStringExtra(FOLDER_ID)), values);
        }

        drawingView.getDrawingCache().recycle();
        drawingView.destroyDrawingCache();
        activity.finish();
    }

    /**
     * Checks if we are editing an existing drawing or making new one
     *
     * @return boolean The extra boolean sent from NoteActivity
     */
    public boolean isInEditMode() {
        return activity.getIntent().getBooleanExtra(IS_EDITING, false);
    }

    /**
     * Gets a NoteStruct instance with current note parameters.
     *
     * @return NoteStruct The NoteStruct that was clicked in NoteActivity
     */
    public NoteStruct getExtraNote() {
        return activity.getIntent().getParcelableExtra(NOTE);
    }

}
