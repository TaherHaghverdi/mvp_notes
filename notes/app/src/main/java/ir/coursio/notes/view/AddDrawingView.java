package ir.coursio.notes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;


import ir.coursio.notes.R;
import ir.coursio.notes.model.structures.NoteStruct;
import ir.coursio.notes.presenter.AddDrawingPresenter;
import ir.coursio.notes.util.Const;
import ir.coursio.notes.view.custom.CustomSpinnerAdapter;
import ir.coursio.notes.view.custom.DrawingView;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */
@SuppressLint("ViewConstructor")
public class AddDrawingView extends FrameLayout implements View.OnClickListener {
    private AddDrawingPresenter presenter;
    //User painting pallet
    private DrawingView painting;
    //Note title edittext
    private EditText edtTitle;
    private LinearLayout mainLayout;

    public AddDrawingView(@NonNull final Activity activity) {
        super(activity);
        View view = inflate(getContext(), R.layout.activity_add_drawing, this);

        edtTitle = (EditText) view.findViewById(R.id.edtTitle);
        painting = (DrawingView) view.findViewById(R.id.painting);
        mainLayout = (LinearLayout) view.findViewById(R.id.mainLayout);

        // Action item click listeners setup
        ImageView imgClear = (ImageView) view.findViewById(R.id.imgClear);
        imgClear.setOnClickListener(this);


        // Toolbar Setup
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.add_note_toolbar);
        MenuItem save = toolbar.getMenu().findItem(R.id.save);
        toolbar.setNavigationIcon(getIcon(R.drawable.ic_arrow_back_24dp));
        save.setIcon(getIcon(R.drawable.ic_check_24dp));
        save.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ((OnSaveListener) presenter).onSave(edtTitle.getText().toString(), painting);
                return false;
            }
        });
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        //Set custom spinner to choose color
        Spinner spnColor = (Spinner) view.findViewById(R.id.spnColor);
        spnColor.setAdapter(new CustomSpinnerAdapter(getContext(),
                CustomSpinnerAdapter.colorDrawables, CustomSpinnerAdapter.colorNames));
        spnColor.setOnItemSelectedListener(spnColorListener);

        //Set custom spinner to choose brush
        Spinner spnBrush = (Spinner) view.findViewById(R.id.spnBrush);
        spnBrush.setAdapter(new CustomSpinnerAdapter(getContext(),
                CustomSpinnerAdapter.brushDrawables, CustomSpinnerAdapter.brushNames));
        spnBrush.setOnItemSelectedListener(spnBrushListener);
    }

    public void setPresenter(AddDrawingPresenter presenter) {
        this.presenter = presenter;
    }

    private Drawable getIcon(int id) {
        return ContextCompat.getDrawable(getContext(), id);
    }

    public interface OnSaveListener {
        void onSave(String title, DrawingView drawingView);
    }


    @Override
    public void onClick(View v) {
        painting.clear();
    }

    /**
     * If the viewing note is not a new note we need to retrieve user data.
     * This method gets data from AddNoteModel and set them on current views.
     *
     * @param note The NoteStruct which contains user's saved data.
     */
    public void editMode(final NoteStruct note) {
        mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                edtTitle.setText(note.getTitle());

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inMutable = true;
                Bitmap bmp = BitmapFactory.decodeByteArray(note.getDrawing(), 0, note.getDrawing().length, options);
                painting.drawBitmap(bmp);

                if (Build.VERSION.SDK_INT < 16) {
                    mainLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    mainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    /**
     * An OnItemSelectedListener to change color based on user interests
     */
    AdapterView.OnItemSelectedListener spnColorListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            painting.changeColor(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            painting.changeColor(Const.BLACK);
        }
    };

    /**
     * An OnItemSelectedListener to change color based on user interests
     */
    AdapterView.OnItemSelectedListener spnBrushListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    painting.changeBrush(Const.PEN);
                    break;
                case 1:
                    painting.changeBrush(Const.BRUSH);
                    break;
                case 2:
                    painting.changeBrush(Const.MARKER);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            painting.changeColor(Const.BLACK);
        }
    };
}
