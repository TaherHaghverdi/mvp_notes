package ir.coursio.notes.presenter;

import ir.coursio.notes.model.AddDrawingModel;
import ir.coursio.notes.view.AddDrawingView;
import ir.coursio.notes.view.custom.DrawingView;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

public class AddDrawingPresenter implements AddDrawingView.OnSaveListener {
    private AddDrawingView view;
    private AddDrawingModel model;

    public AddDrawingPresenter(AddDrawingView view, AddDrawingModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate() {
        view.setPresenter(this);
    }

    public void onDestroy() {
    }

    @Override
    public void onSave(String title, DrawingView drawingView) {
        model.saveDrawing(title, drawingView);
    }
}
