package ir.coursio.notes.presenter;

import android.Manifest;

import ir.coursio.notes.MainActivity;
import ir.coursio.notes.model.MainModel;
import ir.coursio.notes.util.PermissionHandler;
import ir.coursio.notes.view.MainView;
import ir.coursio.notes.view.adapter.FolderAdapter;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public class MainPresenter {
    private final MainView view;
    private final MainModel model;

    public MainPresenter(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate() {
    }

    public void onDestroy() {
    }

    public void addFolderToDb(String s) {
        model.addFolder(s);
    }

}
