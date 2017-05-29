package ir.coursio.notes;

import android.content.Intent;
import android.os.Bundle;

import ir.coursio.notes.model.MainModel;
import ir.coursio.notes.presenter.MainPresenter;
import ir.coursio.notes.view.MainView;
import ir.coursio.notes.view.dialog.AddNewFolderDialog;
import ir.coursio.notes.view.fragment.FoldersFragment;

public class MainActivity extends BaseActivity implements FoldersFragment.Listener, AddNewFolderDialog.Listener {

    MainView view;
    MainModel model;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new MainModel(this);
        view = new MainView(this);
        setContentView(view);
        presenter = new MainPresenter(view, model);
        presenter.onCreate();

    }

    /**
     * this method opens NoteActivity with notes inside a specific folder
     *
     * @param id folder's id
     */
    @Override
    public void goToFolder(String id) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra(NoteActivity.FOLDER_ID, id);
        startActivity(intent);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onSaveClickListener(String name) {
        presenter.addFolderToDb(name);
    }
}
