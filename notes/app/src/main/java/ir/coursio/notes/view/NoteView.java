package ir.coursio.notes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import ir.coursio.notes.R;
import ir.coursio.notes.model.NoteModel;
import ir.coursio.notes.presenter.NotePresenter;
import ir.coursio.notes.presenter.NotesListPresenter;
import ir.coursio.notes.view.custom.fab.FloatingActionButton;
import ir.coursio.notes.view.custom.fab.FloatingActionsMenu;
import ir.coursio.notes.view.list.NotesListFragment;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */
@SuppressLint("ViewConstructor")
public class NoteView extends FrameLayout implements View.OnClickListener {

    private NotePresenter presenter;
    private FloatingActionsMenu fabAddNote;

    private enum ClickedItemType {TEXT, DRAWING}

    Toolbar toolbar;

    public NoteView(@NonNull final Activity activity) {
        super(activity);
        View view = inflate(getContext(), R.layout.activity_note, this);

        final ViewGroup mainLayout = (ViewGroup) view.findViewById(R.id.mainLayout);

        //FloatingActionButtons setup
        fabAddNote = (FloatingActionsMenu) view.findViewById(R.id.fabAddNote);
        FloatingActionButton fabAddText = (FloatingActionButton) view.findViewById(R.id.fabAddText);
        FloatingActionButton fabAddDrawing = (FloatingActionButton) view.findViewById(R.id.fabAddDrawing);
        fabAddText.setTag(ClickedItemType.TEXT);
        fabAddDrawing.setTag(ClickedItemType.DRAWING);
        fabAddDrawing.setOnClickListener(this);
        fabAddText.setOnClickListener(this);

        //Notes list setup
        FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        LoaderManager loaderManager = ((FragmentActivity) activity).getSupportLoaderManager();

        NotesListFragment notesList = (NotesListFragment) fragmentManager.findFragmentByTag("NotesListFragment");
        if (notesList == null) {
            notesList = new NotesListFragment();
            fragmentManager.beginTransaction().
                    add(R.id.mainLayout, notesList, "NotesFragment").commitAllowingStateLoss();
        }
        new NotesListPresenter(notesList, loaderManager, activity.getIntent().getStringExtra(NoteModel.FOLDER_ID));

        //Toolbar setup
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    }

    public void setPresenter(NotePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View v) {
        fabAddNote.collapseImmediately();

        switch ((ClickedItemType) v.getTag()) {
            //Create new Note
            case TEXT:
                ((OnNewNoteRequestListener) presenter).onNewTextNoteRequest();
                break;

            //Create new Drawing
            case DRAWING:
                ((OnNewNoteRequestListener) presenter).onNewDrawingNoteRequest();
                break;
        }
    }

    /**
     * This interface request presenter to make or update a note.
     */
    public interface OnNewNoteRequestListener {
        void onNewTextNoteRequest();

        void onNewDrawingNoteRequest();
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }
}
