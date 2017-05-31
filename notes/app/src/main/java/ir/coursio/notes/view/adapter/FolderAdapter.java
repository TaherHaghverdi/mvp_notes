package ir.coursio.notes.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ir.coursio.notes.R;
import ir.coursio.notes.model.structures.FolderStruct;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public class FolderAdapter extends RecyclerView.Adapter {
    //Custom onListItemClickedListener to handle each item click
    private onListItemClickedListener onListItemClickedListener;
    //list of user's folders
    private ArrayList<FolderStruct> folders;

    public FolderAdapter(onListItemClickedListener onListItemClickedListener, ArrayList<FolderStruct> folders) {
        this.onListItemClickedListener = onListItemClickedListener;
        this.folders = folders;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_folder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (folders != null) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.bindView(folders.get(position));
        }
    }

    /**
     * ViewHolder class stores data that makes binding view contents  easier
     */
    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtFolderName;
        private ImageView imgDelete;
        private View view;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            txtFolderName = (TextView) view.findViewById(R.id.txtFolderName);
            imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
        }

        void bindView(final FolderStruct mFolder) {
            txtFolderName.setText(mFolder.getName());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onListItemClickedListener.onFolderClicked(mFolder.getId(), mFolder.getName());
                }
            });
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onListItemClickedListener.onRemoveClicked(mFolder.getId());
                    if (folders.size() <= 1) {
                        swapData(folders);
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (folders != null) {
            return folders.size();
        } else {
            return 0;
        }
    }

    public interface onListItemClickedListener {
        void onFolderClicked(String id, String name);

        void onRemoveClicked(String id);
    }

    /**
     * Swap new data into adapter and update folders list
     *
     * @param folders updated folders list
     */
    public void swapData(ArrayList<FolderStruct> folders) {
        this.folders.clear();
        this.folders.addAll(folders);
        notifyDataSetChanged();
    }
}
