package ir.coursio.notes;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import ir.coursio.notes.model.MainModel;
import ir.coursio.notes.presenter.MainPresenter;
import ir.coursio.notes.view.MainView;
import ir.coursio.notes.view.dialog.AddNewFolderDialog;
import ir.coursio.notes.view.fragment.FoldersFragment;

public class MainActivity extends BaseActivity implements FoldersFragment.Listener,AddNewFolderDialog.Listener{

    MainView view;
    MainModel model;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new MainModel(this);
        view = new MainView(this);
        setContentView(view);
        presenter = new MainPresenter(view,model);
        presenter.onCreate();

    }

    @Override
    public void showFileData(String id) {

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
