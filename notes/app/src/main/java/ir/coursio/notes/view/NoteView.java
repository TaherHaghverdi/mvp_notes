package ir.coursio.notes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import ir.coursio.notes.R;

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
        FloatingActionButton fabAddNote = (FloatingActionButton) view.findViewById(R.id.fabAddNote);

    }

    public void showMessage(String message) {
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_SHORT).show();
    }
}
