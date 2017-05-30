package ir.coursio.notes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

import ir.coursio.notes.R;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

@SuppressLint("ViewConstructor")
public class AddNoteView extends FrameLayout {

    public AddNoteView(@NonNull Activity activity) {
        super(activity);
        View view = inflate(getContext(), R.layout.activity_add_note, this);
    }

}
