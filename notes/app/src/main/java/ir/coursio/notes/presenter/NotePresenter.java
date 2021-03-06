package ir.coursio.notes.presenter;

import ir.coursio.notes.model.NoteModel;
import ir.coursio.notes.view.NoteView;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */

public class NotePresenter implements NoteView.OnNewNoteRequestListener {
    private final NoteView view;
    private final NoteModel model;

    public NotePresenter(NoteView view, NoteModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate() {
        view.setPresenter(this);
        view.setToolbarTitle(model.getFolderName());
    }

    public void onDestroy() {
    }

    /**
     * Request model to provide a new AddNoteActivity, Implements {@link NoteView.OnNewNoteRequestListener}
     */
    @Override
    public void onNewTextNoteRequest() {
        model.newTextNoteRequest();
    }

    /**
     * Request model to provide a new AddDrawingActivity, Implements {@link NoteView.OnNewNoteRequestListener}
     */
    @Override
    public void onNewDrawingNoteRequest() {
        model.newDrawingNoteRequest();
    }
}
