package ir.coursio.notes.model;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;

import java.util.ArrayList;

import ir.coursio.notes.App;
import ir.coursio.notes.MainActivity;
import ir.coursio.notes.model.db.DataContract;
import ir.coursio.notes.util.PermissionHandler;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public class MainModel {
    private final Activity activity;

    public MainModel(Activity activity) {
        this.activity = activity;
    }

    public void addFolder(final String name) {
        ContentValues values = new ContentValues();
        values.put(DataContract.FoldersEntry.COLUMN_FOLDER_NAME, name);
        App.getAppContext().getContentResolver().insert(DataContract.FoldersEntry.CONTENT_URI, values);
    }

    //  public ArrayList<FolderStruct> getFolders() {
    //    return realm.where(FolderStruct.class).findAll();
    //}
}
