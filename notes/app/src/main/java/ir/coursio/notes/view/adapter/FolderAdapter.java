package ir.coursio.notes.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ir.coursio.notes.R;
import ir.coursio.notes.model.structures.FolderStruct;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public class FolderAdapter extends RecyclerView.Adapter {
    //Custom onFolderClickedListener to handle each item click
    private onFolderClickedListener onFolderClickedListener;
    private ArrayList<FolderStruct> folders;
    private Context context;

    public FolderAdapter(onFolderClickedListener onFolderClickedListener, ArrayList<FolderStruct> folders, Context context) {
        this.onFolderClickedListener = onFolderClickedListener;
        this.folders = folders;
        this.context = context;
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
    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtFolderName;
        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            txtFolderName = (TextView) view.findViewById(R.id.txtFolderName);
        }

        public void bindView(final FolderStruct mFolder) {
            txtFolderName.setText(mFolder.getName());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onFolderClickedListener.onFolderClicked(mFolder.getId());
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

    public interface onFolderClickedListener {
        void onFolderClicked(String id);
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
