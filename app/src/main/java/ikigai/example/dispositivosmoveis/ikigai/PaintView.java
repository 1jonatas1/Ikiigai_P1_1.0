package ikigai.example.dispositivosmoveis.ikigai;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class PaintView extends View {

    public static int BRUSH_SIZE = 20;
    public int DEFAULT_COLOR = Color.RED;
    public static final int DEFAULT_BG_COLOR = Color.WHITE;
    private static final float TOUCH_TOLERANCE = 4;
    private float mX, mY;
    private Path mPath;
    private Paint mPaint;
    private ArrayList<FingerPath> paths = new ArrayList<>();
    private ArrayList<PointF> circulo = new ArrayList<>();
    ArrayList<ArrayList<PointF>> circulos = new ArrayList<>(new ArrayList<>());
    private int currentColor;
    private int backgroundColor = DEFAULT_BG_COLOR;
    private int strokeWidth;
    private boolean emboss;
    private boolean blur;
    private MaskFilter mEmboss;
    private MaskFilter mBlur;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);

    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xff);

        mEmboss = new EmbossMaskFilter(new float[]{1, 1, 1}, 0.4f, 6, 3.5f);
        mBlur = new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL);
    }

    public void init(DisplayMetrics metrics) {
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        currentColor = DEFAULT_COLOR;
        strokeWidth = BRUSH_SIZE;
    }

    public void normal() {
        emboss = false;
        blur = false;
    }

    public void emboss() {
        emboss = true;
        blur = false;
    }

    public void blur() {
        emboss = false;
        blur = true;
    }

    Point point;

    public void setDEFAULT_COLOR(int color) {
        this.DEFAULT_COLOR = color;
    }

    public void clear() {
        backgroundColor = DEFAULT_BG_COLOR;
        paths.clear();
        normal();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        mCanvas.drawColor(backgroundColor);

        for (FingerPath fp : paths) {
            mPaint.setColor(fp.color);
            mPaint.setStrokeWidth(fp.strokeWidth);
            mPaint.setMaskFilter(null);

            if (fp.emboss)
                mPaint.setMaskFilter(mEmboss);
            else if (fp.blur)
                mPaint.setMaskFilter(mBlur);

            mCanvas.drawPath(fp.path, mPaint);

        }

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.restore();
    }

    private void touchStart(float x, float y) {
        mPath = new Path();
        FingerPath fp = new FingerPath(currentColor, emboss, blur, strokeWidth, mPath);
        paths.add(fp);

        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp() {
        mPath.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                circulo = new ArrayList<PointF>();
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                PointF point = new PointF(x, y);
                circulo.add(point);
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                circulos.add(circulo);
                touchUp();
                invalidate();
                break;
        }

        return true;
    }

    boolean isIkigai() {
        int c = 0;
        int inter = 0;
        int teste = 0;
        for (int i = 0; i < circulos.size(); i++) {
            for (int x = 0; x < circulos.size(); x++) {
                teste = 0;
                if (circulos.get(i) != circulos.get(x)) {
                    c++;
                }
//                inter = 0;
//                for (int j = 0; j < circulos.get(i).size(); j++) {
//
//                    for (int y = 0; y < circulos.get(x).size(); y++) {
//
//                        if (circulos.get(i).get(j).x == circulos.get(x).get(y).x && circulos.get(i).get(j).y == circulos.get(x).get(y).y) {
//                            inter++;
//                            //Toast.makeText(getContext(), "" + inter + "", Toast.LENGTH_SHORT).show();
//
//                        }
//                        if (inter >= 2) {
//                            teste += 2;
//                        }
//                    }
//                }
//                if (teste >= 6) {
//                    c++;
//                }
//            }
        }
    }
        Toast.makeText(getContext(), "" + c+ "", Toast.LENGTH_SHORT).show();
        return true;
    }


    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }
    public int getCurrentColor(){
        return this.currentColor;
    }

    public int getDEFAULT_COLOR() {
        return DEFAULT_COLOR;
    }
}



