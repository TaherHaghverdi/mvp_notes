package ir.coursio.notes.model;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;


import ir.coursio.notes.App;
import ir.coursio.notes.MainActivity;
import ir.coursio.notes.model.db.DataBackupHandler;
import ir.coursio.notes.model.db.DataContract;

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

    /**
     * Request an intention to get exported data.
     * Calls onActivityResult in {@link MainActivity}
     */
    public void getImportedPath() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        activity.startActivityForResult(intent, 1);
    }

    /**
     * Make a call to {@link DataBackupHandler} to import database from external storage.
     *
     * @param path The string which contains stored database path.
     * @return The String message of whether import was successful or failed.
     */
    public String importDatabase(String path) {
        return DataBackupHandler.importDb(path, activity);
    }

    /**
     * Requests to {@link DataBackupHandler} to export database in external storage.
     *
     * @return The String message of whether import was successful or failed.
     */
    public String exportDatabase() {
        return DataBackupHandler.exportDB();
    }

    /**
     * Requests to ContentProvider to delete a folder and all of it's notes.
     *
     * @param id The String id of deleting folder.
     * @return int which is number of deleted rows.
     */
    public int removeFolder(String id) {
        Uri uri = DataContract.FoldersEntry.CONTENT_URI;
        return App.getAppContext().getContentResolver().delete(uri, id, null);
    }

}
