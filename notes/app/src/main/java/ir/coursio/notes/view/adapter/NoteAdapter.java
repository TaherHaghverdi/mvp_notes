package ir.coursio.notes.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import ir.coursio.notes.model.structures.NoteStruct;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */

public class NoteAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<NoteStruct> notes;

    public NoteAdapter(Context context, ArrayList<NoteStruct> notes) {
        this.context = context;
        this.notes = notes;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

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
        void onNoteClicked(NoteStruct note);
    }


    public void swapData(ArrayList<NoteStruct> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
        notifyDataSetChanged();
    }

}
