package ir.coursio.notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ir.coursio.notes.model.AddNoteModel;
import ir.coursio.notes.presenter.AddNotePresenter;
import ir.coursio.notes.view.AddNoteView;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

public class AddNoteActivity extends AppCompatActivity {

    AddNotePresenter presenter;
    AddNoteView view;
    AddNoteModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new AddNoteView(this);
        model = new AddNoteModel(this);

        setContentView(view);

        presenter = new AddNotePresenter(view, model);
        presenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
