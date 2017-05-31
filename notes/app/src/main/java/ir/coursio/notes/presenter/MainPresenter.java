package ir.coursio.notes.presenter;

import ir.coursio.notes.model.MainModel;
import ir.coursio.notes.view.MainView;

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
        view.setPresenter(this);
    }

    public void onDestroy() {
    }

    public void addFolderToDb(String s) {
        model.addFolder(s);
    }

    public void importDatabase() {
        model.getImportPath();
    }

    public String exportDatabase() {
       return model.exportDatabase();
    }

}
