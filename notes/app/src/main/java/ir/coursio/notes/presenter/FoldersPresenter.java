package ir.coursio.notes.presenter;

import ir.coursio.notes.component.FoldersComponent;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public class FoldersPresenter implements FoldersComponent.Presenter {

    private FoldersComponent.View view;

    public FoldersPresenter(FoldersComponent.View view){
        this.view = view;
    }

    @Override
    public void present() {

    }
}
