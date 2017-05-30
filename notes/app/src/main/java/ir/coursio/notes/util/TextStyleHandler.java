package ir.coursio.notes.util;

import android.content.Context;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.Toast;

import ir.coursio.notes.R;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

public class TextStyleHandler {

    private Context context;

    public TextStyleHandler(Context context) {
        this.context = context;
    }

    /**
     * This method gets an EditText and a style, Then apply style to selected part of a EditText
     *
     * @param editText The EditText to find selected part
     * @param style    The style to apply to selected part of the text
     */
    public void applyStyleToText(EditText editText, int style) {
        //Gets selected part of text
        int selectionStart = editText.getSelectionStart();
        int selectionEnd = editText.getSelectionEnd();
        //If nothing is selected!
        if (selectionEnd == selectionStart) {
            Toast.makeText(context, context.getString(R.string.message_no_text_selected), Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectionStart > selectionEnd) {
            int temp = selectionEnd;
            selectionEnd = selectionStart;
            selectionStart = temp;
        }
        if (selectionEnd > selectionStart) {
            Spannable str = editText.getText();
            boolean exists = false;
            StyleSpan[] styleSpans;
            styleSpans = str.getSpans(selectionStart, selectionEnd, StyleSpan.class);

            // If the selected text-part already has BOLD style on it, then
            // we need to disable it
            for (StyleSpan styleSpan : styleSpans) {
                if (styleSpan.getStyle() == style) {
                    str.removeSpan(styleSpan);
                    exists = true;
                }
            }
            // Else we set BOLD style on it
            if (!exists) {
                str.setSpan(new StyleSpan(style), selectionStart, selectionEnd,
                        Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            editText.setSelection(selectionStart, selectionEnd);
        }
    }
}
