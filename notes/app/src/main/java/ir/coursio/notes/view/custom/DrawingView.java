package ir.coursio.notes.view.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Taher on 30/05/2017.
 * Project: notes
 */

public class DrawingView extends View {

    private Path mPath;
    private Paint mPaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private boolean isEraser = false;


    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        int paintColor = Color.BLACK;
        mPaint.setColor(paintColor);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
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

        //for call onDraw method
        invalidate();
        return true;
    }

    public void paintBitmap(Bitmap bmp) {
        mBitmap = bmp;
        mCanvas = new Canvas(mBitmap);

        Matrix m = new Matrix();
        RectF s = new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        RectF d = new RectF(0, 0, mCanvas.getWidth(), mCanvas.getHeight());
        m.setRectToRect(s, d, Matrix.ScaleToFit.CENTER);
        mCanvas.drawBitmap(mBitmap, 0, 0, mPaint);
        invalidate();
    }

    public void isEraser(boolean isEraser) {
        this.isEraser = isEraser;
        if (isEraser) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        } else {
            mPaint.setXfermode(null);
        }

    }

    public void setPaintXfermode() {
        mPaint.setXfermode(null);
    }

}
