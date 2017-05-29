package ir.coursio.notes.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import ir.coursio.notes.R;
import ir.coursio.notes.presenter.FoldersListPresenter;
import ir.coursio.notes.presenter.MainPresenter;
import ir.coursio.notes.util.PermissionHandler;
import ir.coursio.notes.view.dialog.AddNewFolderDialog;
import ir.coursio.notes.view.list.FoldersListFragment;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

@SuppressLint("ViewConstructor")
public class MainView extends FrameLayout implements View.OnClickListener {

    ViewGroup mainLayout;
    FragmentManager fragmentManager;

    public MainView(@NonNull Activity activity) {
        super(activity);
        View view = inflate(getContext(), R.layout.activity_main, this);

        mainLayout = (ViewGroup) view.findViewById(R.id.mainLayout);
        FloatingActionButton fabAddFolder = (FloatingActionButton) view.findViewById(R.id.fabAddFolder);
        fabAddFolder.setOnClickListener(this);

        fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        LoaderManager loaderManager = ((FragmentActivity) activity).getSupportLoaderManager();
        FoldersListFragment foldersListFragment = (FoldersListFragment) fragmentManager.findFragmentByTag("FoldersListFragment");
        if (foldersListFragment == null) {
            foldersListFragment = new FoldersListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.mainLayout, foldersListFragment, "CategoryFragment").commitAllowingStateLoss();
        }
        new FoldersListPresenter(foldersListFragment, loaderManager);
    }

    public void showMessage(String message) {
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,};
        new PermissionHandler().checkPermission((Activity) getContext(), permissions, new PermissionHandler.OnPermissionResponse() {
            @Override
            public void onPermissionGranted() {
                AddNewFolderDialog dialog = new AddNewFolderDialog();
                dialog.setShowsDialog(true);
                dialog.show(fragmentManager, "NewFolderDialogFragment");
            }

            @Override
            public void onPermissionDenied() {
                // Show an alert dialog
            }
        });

    }

}