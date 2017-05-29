package ir.coursio.notes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import ir.coursio.notes.R;
import ir.coursio.notes.view.custom.fab.FloatingActionButton;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */
@SuppressLint("ViewConstructor")
public class NoteView extends FrameLayout {

    ViewGroup mainLayout;

    public NoteView(@NonNull Activity activity) {
        super(activity);
        View view = inflate(getContext(), R.layout.activity_note, this);

        mainLayout = (ViewGroup) view.findViewById(R.id.mainLayout);
        final FloatingActionButton fabAddText = (FloatingActionButton) view.findViewById(R.id.fabAddText);
        FloatingActionButton fabAddDrawing = (FloatingActionButton) view.findViewById(R.id.fabAddDrawing);

    }

    public void showMessage(String message) {
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_SHORT).show();
    }
}
