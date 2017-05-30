package ir.coursio.notes.presenter;

import android.text.Editable;

import ir.coursio.notes.model.AddNoteModel;
import ir.coursio.notes.view.AddNoteView;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

public class AddNotePresenter implements AddNoteView.OnSaveListener {

    private final AddNoteView view;
    private final AddNoteModel model;

    public AddNotePresenter(AddNoteView view, AddNoteModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate() {
        view.setPresenter(this);

        if (model.isInEditMode()) {
            view.editMode(model.getExtraNote());
        }
    }

    public void onDestroy() {
    }

    /**
     * Save user note using model saveNote method
     *
     * @param title user note title
     * @param text  user note text
     */
    @Override
    public void onSave(String title, Editable text) {
        model.saveNote(title, text);
    }
}
