package ir.coursio.notes.presenter;

import android.content.ContentUris;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import java.util.ArrayList;

import ir.coursio.notes.App;
import ir.coursio.notes.component.NotesComponent;
import ir.coursio.notes.model.db.DataContract;
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

    /**
     * Class default constructor
     *
     * @param view          The {@link NotesComponent.View} to provide presenter and updates folders list.
     * @param loaderManager The LoaderManager to fetch data from database.
     * @param folderId      The String that identify current folder.
     */
    public NotesListPresenter(NotesComponent.View view, LoaderManager loaderManager, String folderId) {
        this.view = view;
        this.loaderManager = loaderManager;
        this.folderId = folderId;
        view.setPresenter(this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //Instantiate and return a new Loader for the given ID
        String[] projection = {"*"};
        //Return a new Loader instance that is ready to start loading
        return new CursorLoader(App.getAppContext(),
                ContentUris.withAppendedId(DataContract.FoldersEntry.CONTENT_URI_NOTES, Integer.parseInt(folderId)),
                projection, null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Cursor contains data loaded by loader
        // We fetch data from cursor and pass it to view to update notes list
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                ArrayList<NoteStruct> notes = new ArrayList<>();
                try {
                    do {
                        notes.add(new NoteStruct(cursor));
                    }
                    while (cursor.moveToNext());
                } finally {
                    view.updateNotes(notes);
                }
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
