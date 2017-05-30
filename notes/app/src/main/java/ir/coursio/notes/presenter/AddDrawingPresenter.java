package ir.coursio.notes.presenter;

import ir.coursio.notes.model.AddDrawingModel;
import ir.coursio.notes.view.AddDrawingView;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

public class AddDrawingPresenter {
    private AddDrawingView view;
    private AddDrawingModel model;

    public AddDrawingPresenter(AddDrawingView view, AddDrawingModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate() {
    }

    public void onDestroy() {
    }
}
