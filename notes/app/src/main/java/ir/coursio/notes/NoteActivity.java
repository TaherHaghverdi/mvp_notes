package ir.coursio.notes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ir.coursio.notes.view.NoteView;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public class NoteActivity extends AppCompatActivity {
    public static final String FOLDER_ID = "folder_id";
    NoteView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new NoteView(this);
        setContentView(view);

    }

}
