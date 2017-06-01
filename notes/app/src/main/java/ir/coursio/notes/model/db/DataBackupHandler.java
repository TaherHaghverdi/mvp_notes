package ir.coursio.notes.model.db;

import android.app.Activity;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import ir.coursio.notes.App;
import ir.coursio.notes.R;

/**
 * Created by Taher on 31/05/2017.
 * Project: notes
 */

public class DataBackupHandler {

    /**
     * This method exports database to external storage
     *
     * @return String success or failed message.
     */
    public static String exportDB() {
        File sd = Environment.getExternalStorageDirectory();
        FileChannel source;
        FileChannel destination;
        String backupDBPath = "notes_backup";
        File currentDbFile = App.getAppContext().getDatabasePath(DbHelper.DATABASE_NAME);
        File backupDbFile = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDbFile).getChannel();
            destination = new FileOutputStream(backupDbFile).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            return App.getAppContext().getString(R.string.export_db_success);
        } catch (IOException e) {
            e.printStackTrace();
            return App.getAppContext().getString(R.string.export_db_fail);
        }

    }

    /**
     * Import backed up data into application.
     *
     * @param path     Where database is saved.
     * @param activity Instance of current activity that will be reset after importing database.
     * @return String success or failed message.
     */
    public static String importDb(String path) {
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
                return App.getAppContext().getString(R.string.import_db_success);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return App.getAppContext().getString(R.string.import_db_fail);
        }
        return App.getAppContext().getString(R.string.import_db_fail);
    }
}
