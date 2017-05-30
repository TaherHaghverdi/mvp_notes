package ir.coursio.notes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import ir.coursio.notes.R;
import ir.coursio.notes.presenter.AddDrawingPresenter;
import ir.coursio.notes.view.custom.DrawingView;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */
@SuppressLint("ViewConstructor")
public class AddDrawingView extends FrameLayout {

    private AddDrawingPresenter presenter;

    public AddDrawingView(@NonNull Activity activity) {
        super(activity);

        View view = inflate(getContext(), R.layout.activity_add_drawing, this);

        final EditText edtTitle = (EditText) view.findViewById(R.id.edtTitle);
        final DrawingView painting = (DrawingView) view.findViewById(R.id.painting);
    }

    public void setPresenter(AddDrawingPresenter presenter) {
        this.presenter = presenter;
    }
}
