package ir.coursio.notes.view.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ir.coursio.notes.R;
import ir.coursio.notes.model.structures.NoteStruct;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */

public class NoteAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<NoteStruct> notes;
    private Listener listener;


    public NoteAdapter(Context context, ArrayList<NoteStruct> notes, Listener listener) {
        this.context = context;
        this.notes = notes;
        this.listener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_note, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.bindView(notes.get(position));

    }

    @Override
    public int getItemCount() {
        if (notes != null) {
            return notes.size();
        } else {
            return 0;
        }
    }

    public interface Listener {
        void onNoteSelect(NoteStruct note);
    }


    public void swapData(ArrayList<NoteStruct> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
        notifyDataSetChanged();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtText;
        private ImageView imgId;
        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            txtTitle = (TextView) view.findViewById(R.id.txtNoteTitle);
            txtText = (TextView) view.findViewById(R.id.txtNoteText);
            imgId = (ImageView) view.findViewById(R.id.txtCancel);
        }


        public void bindView(final NoteStruct note) {
            txtTitle.setText(note.getTitle());

            if (note.getDrawing() == null) {
                setImageDrawable(R.drawable.ic_note_24dp, imgId);
                txtText.setText(note.getText());
            } else {
                setImageDrawable(R.drawable.ic_image_24dp, imgId);
                txtText.setVisibility(View.GONE);
            }


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //  listener.onNoteClicked(note);
                }
            });
        }
    }

    private void setImageDrawable(int id, ImageView img) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            img.setImageDrawable(context.getDrawable(id));
        } else {
            img.setImageDrawable(ContextCompat.getDrawable(context, id));
        }

    }
}
