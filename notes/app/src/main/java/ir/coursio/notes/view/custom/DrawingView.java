package ir.coursio.notes.view.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.ByteArrayOutputStream;

import ir.coursio.notes.R;
import ir.coursio.notes.util.Const;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

public class DrawingView extends View {
    //Statics that contains different brush sizes
    private static final int PEN_SIZE = 15;
    private static final int BRUSH_SIZE = 25;
    private static final int MARKER_SIZE = 40;

    private Path mPath;
    private Paint mPaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * This method init a round black stylus for user to paint with.
     */
    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(PEN_SIZE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        Log.i("tag", "initialized");

    }

    /**
     * Set the paint's color.
     *
     * @param color The id of new color to set in the paint.
     */
    public void changeColor(int color) {
        switch (color) {
            case Const.BLACK:
                mPaint.setColor(Color.BLACK);
                break;
            case Const.RED:
                mPaint.setColor(Color.RED);
                break;
            case Const.GREEN:
                mPaint.setColor(Color.GREEN);
                break;
        }
    }

    /**
     * Set the width for stroking.
     *
     * @param brush The int set the paint's stroke width.
     */
    public void changeBrush(int brush) {
        switch (brush) {
            case Const.PEN:
                mPaint.setStrokeWidth(PEN_SIZE);
                break;
            case Const.BRUSH:
                mPaint.setStrokeWidth(BRUSH_SIZE);
                break;
            case Const.MARKER:
                mPaint.setStrokeWidth(MARKER_SIZE);
                break;
        }
    }

    /**
     * Draw the specified bitmap, with its top/left corner at (x,y), using
     * the specified paint, transformed by the current matrix.
     *
     * @param bmp The bitmap to be drawn.
     */
    public void drawBitmap(Bitmap bmp) {
        mBitmap = bmp;
        mCanvas = new Canvas(mBitmap);

        Matrix m = new Matrix();
        RectF s = new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        RectF d = new RectF(0, 0, mCanvas.getWidth(), mCanvas.getHeight());
        m.setRectToRect(s, d, Matrix.ScaleToFit.CENTER);
        mCanvas.drawBitmap(mBitmap, 0, 0, mPaint);
        invalidate();
    }

    /**
     * Clear the screen and remove all drawings.
     */
    public void clear() {
        mCanvas.drawColor(ContextCompat.getColor(getContext(), R.color.main_bg));
    }

    /**
     * Clear the xfermode object
     */
    public void setPaintXfermode() {
        mPaint.setXfermode(null);
    }

    /**
     * Transforms user drawing to an array of bytes.
     *
     * @return the current contents of this output stream, as a byte array.
     */
    public byte[] getByteArray() {
        this.setPaintXfermode();
        this.buildDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        this.getDrawingCache().compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                mCanvas.drawPath(mPath, mPaint);
                mPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
}
