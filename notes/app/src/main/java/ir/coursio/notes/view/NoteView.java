package ir.coursio.notes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

import ir.coursio.notes.R;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */
@SuppressLint("ViewConstructor")
public class NoteView extends FrameLayout {

    public NoteView(@NonNull Activity activity) {
        super(activity);
        View view = inflate(getContext(), R.layout.activity_main, this);

    }
}
