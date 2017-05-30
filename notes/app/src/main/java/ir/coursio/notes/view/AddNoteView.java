package ir.coursio.notes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;

import ir.coursio.notes.R;
import ir.coursio.notes.util.TextStyleHandler;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

@SuppressLint("ViewConstructor")
public class AddNoteView extends FrameLayout implements View.OnClickListener {

    private EditText edtText;

    private enum ClickedActionItem {BOLD, ITALIC, PASTE}


    public AddNoteView(@NonNull Activity activity) {
        super(activity);
        View view = inflate(getContext(), R.layout.activity_add_note, this);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        EditText edtTitle = (EditText) view.findViewById(R.id.edtTitle);
        edtText = (EditText) view.findViewById(R.id.edtText);

        // Action item click listeners setup
        ImageView imgBold = (ImageView) view.findViewById(R.id.imgBold);
        ImageView imgItalic = (ImageView) view.findViewById(R.id.imgItalic);
        ImageView imgPaste = (ImageView) view.findViewById(R.id.imgPaste);
        imgBold.setOnClickListener(this);
        imgItalic.setOnClickListener(this);
        imgPaste.setOnClickListener(this);
        imgBold.setTag(ClickedActionItem.BOLD);
        imgItalic.setTag(ClickedActionItem.ITALIC);
        imgPaste.setTag(ClickedActionItem.PASTE);

    }

    @Override
    public void onClick(View v) {
        final TextStyleHandler styleHandler = new TextStyleHandler(getContext());

        switch ((ClickedActionItem) v.getTag()) {
            case BOLD:
                styleHandler.applyStyleToText(edtText, android.graphics.Typeface.BOLD);
                break;
            case ITALIC:
                styleHandler.applyStyleToText(edtText, Typeface.ITALIC);
                break;
            case PASTE:
                // Paste here
                break;
        }
    }
}
