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

    /**
     * Calls model to get imported path and pass it to {@link ir.coursio.notes.MainActivity}
     */
    public void importDatabase() {
        model.getImportedPath();
    }

    /**
     * Calls model to export data to database
     *
     * @return The String message of whether import was successful or failed.
     */
    public String exportDatabase() {
        return model.exportDatabase();
    }

}
