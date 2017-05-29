package ir.coursio.notes.component;

import java.util.ArrayList;

import ir.coursio.notes.model.structures.FolderStruct;
import ir.coursio.notes.model.structures.NoteStruct;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */

public interface NotesComponent {

    interface Presenter {
        void present();
    }

    interface View {
        /**
         * provides presenter for view
         *
         * @param presenter inject presenter into view
         */
        void setPresenter(NotesComponent.Presenter presenter);

        /**
         * update folders by swapping adapter's data
         *
         * @param notes inject notes into adapter's RecyclerView
         */
        void updateNotes(ArrayList<NoteStruct> notes);

    }
}
