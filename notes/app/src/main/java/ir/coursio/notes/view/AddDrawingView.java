package ir.coursio.notes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.MenuItem;
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


        // Toolbar Setup
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.add_note_toolbar);
        MenuItem save = toolbar.getMenu().findItem(R.id.save);
        toolbar.setNavigationIcon(getIcon(R.drawable.ic_arrow_back_24dp));
        save.setIcon(getIcon(R.drawable.ic_check_24dp));
        save.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ((OnSaveListener) presenter).onSave(edtTitle.getText().toString());
                return false;
            }
        });

    }

    public void setPresenter(AddDrawingPresenter presenter) {
        this.presenter = presenter;
    }

    private Drawable getIcon(int id) {
        return ContextCompat.getDrawable(getContext(), id);
    }

    public interface OnSaveListener {
        void onSave(String title);
    }
}
