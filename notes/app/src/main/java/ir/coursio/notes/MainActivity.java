package ir.coursio.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import ir.coursio.notes.model.MainModel;
import ir.coursio.notes.model.NoteModel;
import ir.coursio.notes.presenter.MainPresenter;
import ir.coursio.notes.view.MainView;
import ir.coursio.notes.view.dialog.AddNewFolderDialog;
import ir.coursio.notes.view.list.FoldersListFragment;

public class MainActivity extends PermissionActivity implements FoldersListFragment.OnFolderClickedListener, AddNewFolderDialog.OnSaveClickListener {

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
    public void onFolderClicked(String id, String name) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra(NoteModel.FOLDER_ID, id);
        intent.putExtra(NoteModel.FOLDER_NAME, name);
        startActivity(intent);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    /**
     * Save a folder in database
     *
     * @param name Save folder's name in database
     */
    @Override
    public void onSaveClick(String name) {
        presenter.addFolderToDb(name);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    view.showMessage(model.importDatabase(data.getData().getPath()));
                }
                break;
        }
    }
}
