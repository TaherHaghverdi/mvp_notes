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
import ir.coursio.notes.component.FoldersComponent;
import ir.coursio.notes.model.structures.FolderStruct;
import ir.coursio.notes.view.adapter.FolderAdapter;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public class FoldersListFragment extends Fragment implements FoldersComponent.View, FolderAdapter.onListItemClickedListener {

    private FoldersComponent.Presenter presenter;
    private FolderAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        presenter.present();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FolderAdapter(this, new ArrayList<FolderStruct>());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folders, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    /**
     * @param presenter provides presenter for view
     */
    @Override
    public void setPresenter(FoldersComponent.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * @param folders update folders by swapping adapter's data
     */
    @Override
    public void updateFolders(ArrayList<FolderStruct> folders) {
        adapter.swapData(folders);
    }

    /**
     * @param id select specific folder to get it's data
     */
    @Override
    public void onFolderClicked(String id, String name) {
        ((OnListItemClickedListener) getContext()).onFolderClicked(id, name);
    }

    @Override
    public void onRemoveClicked(String id) {
        ((OnListItemClickedListener) getContext()).onRemoveClicked(id);
    }

    public interface OnListItemClickedListener {
        void onFolderClicked(String id, String name);

        void onRemoveClicked(String id);
    }
}
