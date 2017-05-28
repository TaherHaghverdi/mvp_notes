package ir.coursio.notes.component;

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
         * @param presenter
         */
        void setPresenter(FoldersComponent.Presenter presenter);
    }
}
