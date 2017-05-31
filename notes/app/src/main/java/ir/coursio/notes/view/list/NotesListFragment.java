package ir.coursio.notes.view.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ir.coursio.notes.R;
import ir.coursio.notes.component.NotesComponent;
import ir.coursio.notes.model.structures.NoteStruct;
import ir.coursio.notes.view.adapter.NoteAdapter;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */

public class NotesListFragment extends Fragment implements NotesComponent.View, NoteAdapter.OnNoteAdapterClickListener {

    private NotesComponent.Presenter presenter;
    private NoteAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NoteAdapter(getContext(), new ArrayList<NoteStruct>(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.present();
    }

    /**
     * @param presenter provides presenter for view
     */
    @Override
    public void setPresenter(NotesComponent.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * @param notes update notes by swapping adapter's data
     */
    @Override
    public void updateNotes(ArrayList<NoteStruct> notes) {
        adapter.swapData(notes);
    }

    /**
     * provides note's page for user to see or edit.
     *
     * @param note the specific note that was selected
     */
    @Override
    public void onNoteAdapterClick(NoteStruct note) {
        ((OnNoteClickListener) getContext()).onNoteClick(note);
    }

    @Override
    public void onNoteAdapterDelete(String id) {
        ((OnNoteClickListener) getContext()).onNoteDelete(id);
    }

    /**
     * This interface is an onNoteClickedListener to be implemented in activity to show notes to users.
     */
    public interface OnNoteClickListener {
        void onNoteClick(NoteStruct note);

        void onNoteDelete(String id);
    }
}
