package ir.coursio.notes.component;

import java.util.ArrayList;

import ir.coursio.notes.model.FolderStruct;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public interface FoldersComponent {

    interface Presenter {
        void present();
    }

    interface View {
        /**
         * provides presenter for view
         *
         * @param presenter inject presenter into view
         */
        void setPresenter(FoldersComponent.Presenter presenter);

        /**
         * update folders by swapping adapter's data
         *
         * @param folders inject folders into adapter's RecyclerView
         */
        void updateFolders(ArrayList<FolderStruct> folders);

    }
}
