package ir.coursio.notes.presenter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import java.util.ArrayList;

import ir.coursio.notes.App;
import ir.coursio.notes.component.FoldersComponent;
import ir.coursio.notes.model.structures.FolderStruct;
import ir.coursio.notes.model.db.DataContract;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public class FoldersListPresenter implements FoldersComponent.Presenter, LoaderManager.LoaderCallbacks<Cursor> {
    //unique identifier for loader
    private static final int LOADER_IDENTIFIER = 100;
    //manage content loaders
    private LoaderManager loaderManager;
    //view
    private FoldersComponent.View view;

    public FoldersListPresenter(FoldersComponent.View view, LoaderManager loaderManager) {
        this.view = view;
        this.loaderManager = loaderManager;
        view.setPresenter(this);
    }

    @Override
    public void present() {
        loaderManager.initLoader(LOADER_IDENTIFIER, null, this);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        // create new cursor loader from db to load folders
        String[] projection = {"*"};
        return new CursorLoader(App.getAppContext(),
                DataContract.FoldersEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null) {
            cursor.moveToFirst();
            ArrayList<FolderStruct> folders = new ArrayList<>();
            try {
                while (cursor.moveToNext()) {
                    folders.add(new FolderStruct(cursor));
                }
            } finally {
                view.updateFolders(folders);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
    }
}
