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
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,};
        new PermissionHandler().checkPermission(activity, permissions, new PermissionHandler.OnPermissionResponse() {
            @Override
            public void onPermissionGranted() {
                ContentValues values = new ContentValues();
                values.put(DataContract.FoldersEntry.COLUMN_FOLDER_NAME, name);
                App.getAppContext().getContentResolver().insert(DataContract.FoldersEntry.CONTENT_URI, values);
            }

            @Override
            public void onPermissionDenied() {

            }
        });
    }

    //  public ArrayList<FolderStruct> getFolders() {
    //    return realm.where(FolderStruct.class).findAll();
    //}
}
