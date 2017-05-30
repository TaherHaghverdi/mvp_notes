package ir.coursio.notes.presenter;

import ir.coursio.notes.model.AddNoteModel;
import ir.coursio.notes.view.AddNoteView;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

public class AddNotePresenter {

    private final AddNoteView view;
    private final AddNoteModel model;

    public AddNotePresenter(AddNoteView view, AddNoteModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate() {
    }

    public void onDestroy() {
    }
}
