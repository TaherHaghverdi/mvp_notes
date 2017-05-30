package ir.coursio.notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ir.coursio.notes.model.AddDrawingModel;
import ir.coursio.notes.presenter.AddDrawingPresenter;
import ir.coursio.notes.view.AddDrawingView;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

public class AddDrawingActivity extends AppCompatActivity {
    AddDrawingPresenter presenter;
    AddDrawingView view;
    AddDrawingModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate();
        model = new AddDrawingModel(this);
        view = new AddDrawingView(this);

        presenter = new AddDrawingPresenter(view, model);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
