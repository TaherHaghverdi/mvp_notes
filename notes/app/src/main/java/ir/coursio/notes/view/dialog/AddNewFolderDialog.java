package ir.coursio.notes.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import ir.coursio.notes.R;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public class AddNewFolderDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_new_folder, container, false);
        final EditText edtFolderName = (EditText) view.findViewById(R.id.edtFolderName);
        TextView save = (TextView) view.findViewById(R.id.txtSave);
        TextView cancel = (TextView) view.findViewById(R.id.txtCancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtFolderName.getText().toString().length() > 0) {
                    ((OnSaveClickListener) getContext()).onSaveClick(edtFolderName.getText().toString());
                    getDialog().dismiss();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


    public interface OnSaveClickListener {
        /**
         * @param name Save folder's name in database
         */
        void onSaveClick(String name);
    }
}
