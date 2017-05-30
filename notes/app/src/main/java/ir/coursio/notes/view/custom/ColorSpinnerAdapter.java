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

public class ColorSpinnerAdapter extends BaseAdapter {
    public static String[] colorNames = {"Black", "Red", "Green"};
    public static int[] colorDrawables = {R.drawable.ic_color_black_24dp, R.drawable.ic_color_red_24dp, R.drawable.ic_color_green_24dp};

    private Context context;
    private int colors[];
    private String[] colorsName;
    private LayoutInflater layoutInflater;

    public ColorSpinnerAdapter(Context applicationContext, int[] colors, String[] colorsName) {
        this.context = applicationContext;
        this.colors = colors;
        this.colorsName = colorsName;
        layoutInflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return colors.length;
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
        ImageView imgColor = (ImageView) view.findViewById(R.id.imgColor);
        TextView txtColor = (TextView) view.findViewById(R.id.txtColor);
        imgColor.setImageResource(colors[i]);
        txtColor.setText(colorsName[i]);
        return view;
    }
}
