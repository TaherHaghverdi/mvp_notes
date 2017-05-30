package ir.coursio.notes.model.db;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import ir.coursio.notes.App;

/**
 * Created by Taher on 31/05/2017.
 * Project: notes
 */

public class DataBackupHandler {

    /**
     * Export db to external storage
     */
    public static void exportDB() {
        File sd = Environment.getExternalStorageDirectory();
        FileChannel source = null;
        FileChannel destination = null;
        String backupDBPath = "notes_backup";
        File currentDbFile = App.getAppContext().getDatabasePath(DbHelper.DATABASE_NAME);
        File backupDbFile = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDbFile).getChannel();
            destination = new FileOutputStream(backupDbFile).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();

            Toast.makeText(App.getAppContext(), "Saved backups successfully :)", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(App.getAppContext(), "Export Failed :(", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Import data from external storage
     *
     * @param path     Where database is saved
     * @param activity Instance of current activity that will be reset after importing database
     */
    public static void importDb(String path, Activity activity) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            if (sd.canWrite()) {
                File backupDB = new File(path);
                File currentDB = App.getAppContext().getDatabasePath(DbHelper.DATABASE_NAME);

                FileChannel src = new FileInputStream(backupDB).getChannel();
                FileChannel dst = new FileOutputStream(currentDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();

                activity.recreate();
            }
        } catch (Exception e) {
            Toast.makeText(App.getAppContext(), "Import Failed :(", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
