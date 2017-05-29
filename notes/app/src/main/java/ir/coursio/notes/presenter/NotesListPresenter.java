package ir.coursio.notes.presenter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import java.util.ArrayList;

import ir.coursio.notes.App;
import ir.coursio.notes.component.NotesComponent;
import ir.coursio.notes.model.db.DataContract;
import ir.coursio.notes.model.structures.FolderStruct;
import ir.coursio.notes.model.structures.NoteStruct;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */

public class NotesListPresenter implements NotesComponent.Presenter, LoaderManager.LoaderCallbacks<Cursor> {

    //unique identifier for loader
    private static final int LOADER_IDENTIFIER = 100;
    //view
    private NotesComponent.View view;
    //manage content loaders
    private LoaderManager loaderManager;
    // Selected folder id
    private String folderId;

    public NotesListPresenter(NotesComponent.View view, LoaderManager loaderManager, String folderId) {
        this.view = view;
        this.loaderManager = loaderManager;
        this.folderId = folderId;
        view.setPresenter(this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
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
            ArrayList<NoteStruct> notes = new ArrayList<>();
            try {
                while (cursor.moveToNext()) {
                    notes.add(new NoteStruct(cursor));
                }
            } finally {
                view.updateNotes(notes);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void present() {
        loaderManager.initLoader(LOADER_IDENTIFIER, null, this);
    }
}
