package ir.coursio.notes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ir.coursio.notes.model.NoteModel;
import ir.coursio.notes.model.structures.NoteStruct;
import ir.coursio.notes.presenter.NotePresenter;
import ir.coursio.notes.view.NoteView;
import ir.coursio.notes.view.list.NotesListFragment;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public class NoteActivity extends AppCompatActivity implements NotesListFragment.OnNoteClickListener {
    NoteView view;
    NotePresenter presenter;
    NoteModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new NoteModel(this);
        view = new NoteView(this);

        setContentView(view);

        presenter = new NotePresenter(view, model);
        presenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onNoteClick(NoteStruct note) {
        model.editNoteRequest(note);
    }

    @Override
    public void onNoteDelete(String id) {
        model.removeNote(id);
    }

}
