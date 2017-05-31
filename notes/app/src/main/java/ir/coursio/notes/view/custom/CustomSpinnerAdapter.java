package ir.coursio.notes.view.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ir.coursio.notes.R;

/**
 * Created by Taher on 31/05/2017.
 * Project: notes
 */

public class CustomSpinnerAdapter extends BaseAdapter {
    //Color and drawables for color spinner
    public static String[] colorNames = {"Black", "Red", "Green"};
    public static int[] colorDrawables = {R.drawable.ic_color_black_24dp, R.drawable.ic_color_red_24dp, R.drawable.ic_color_green_24dp};

    //Color and drawables for brush spinner
    public static String[] brushNames = {"Pen", "Brush", "Marker"};
    public static int[] brushDrawables = {R.drawable.ic_pen_24dp, R.drawable.ic_brush_24dp, R.drawable.ic_circle_24dp};

    private int drawables[];
    private String[] names;
    private LayoutInflater layoutInflater;

    public CustomSpinnerAdapter(Context applicationContext, int[] drawables, String[] names) {
        this.drawables = drawables;
        this.names = names;
        layoutInflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return drawables.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.custom_color_spinner_item, null);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        TextView txt = (TextView) view.findViewById(R.id.txt);
        img.setImageResource(drawables[i]);
        txt.setText(names[i]);
        return view;
    }
}
