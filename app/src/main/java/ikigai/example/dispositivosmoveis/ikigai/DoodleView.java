package ikigai.example.dispositivosmoveis.ikigai;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DoodleView extends View {
    //limiar para decidir se o usuário moveu o dedo o suficiente
    private static final float TOUCH_TOLERANCE = 10;
    private Bitmap bitmap; //Tela inicial
    private Canvas bitmapCanvas; //Para desenhar no bitmap
    private final Paint paintScreen; //Para desenhar na tela
    private final Paint paintLine; //Para fazer as linhas
    private final Map<Integer, Path> pathMap = new HashMap<>(); //mapa dos paths atualmente sendo desenhados
    private final  Map <Integer, Point> previousPointMap = new HashMap<>(); //mapa que armazena o último ponto em cada path
    int c1 = gerarCor();
    int c2 = gerarCor();
    int c3 = gerarCor();
    int c4 = gerarCor();
    String s1= getResources().getString(R.string.love),s2 = getResources().getString(R.string.good),s3 =getResources().getString(R.string.paid),
            s4=getResources().getString(R.string.what),s5 = getResources().getString(R.string.passion),s6 = getResources().getString(R.string.mission),
            s7=getResources().getString(R.string.profession),s8=getResources().getString(R.string.vocation);

    ArrayList<Path> paths = new ArrayList<Path>();

    public DoodleView (Context context, AttributeSet attributes){
        super (context, attributes);
        paintScreen = new Paint();
        paintLine = new Paint();
        paintLine.setAntiAlias(true);  //suavizar os limites das linhas
        paintLine.setColor(Color.BLACK); //cor
        paintLine.setStyle(Paint.Style.STROKE); //linha sólida
        paintLine.setStrokeWidth(5); //espessura
        paintLine.setStrokeCap(Paint.Cap.ROUND); //terminais das linhas arredondados

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        //o canvas desenha no bitmap
        bitmapCanvas = new Canvas (bitmap);
        bitmap.eraseColor(Color.WHITE);//apaga usando branco
    }

    public void setDrawingColor (int color){
        //paintLine.setColor(Color.WHITE);
        paintScreen.setColor(color);

    }

    public int getDrawingColor(){
        return paintLine.getColor();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint          = new Paint();
        Paint paintClear     = new Paint();
        int x = getWidth();
        int y = getHeight();



        PorterDuff.Mode mode = PorterDuff.Mode.OVERLAY;

        paintClear.setStyle(Paint.Style.FILL);
        paint.setStyle(Paint.Style.FILL);

        // ** clear canvas background to white**
        paintClear.setColor(Color.WHITE);
        canvas.drawPaint(paintClear);

        Bitmap compositeBitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
        Canvas compositeCanvas = new Canvas(compositeBitmap);
        paintClear.setColor(Color.TRANSPARENT);
//
//        Paint p1= new Paint();
//        p1.setColor(c1);
        //paint.setColor(Color.argb(255,255,255,0));
        //paint.setColor(Color.argb(255, 152, 251, 152));
        paint.setColor(c1);
        compositeCanvas.drawCircle(x / 2 , y / 2 - 150, 200, paint);
        paint.setXfermode(new PorterDuffXfermode(mode));
//        Paint p2= new Paint();
//        p1.setColor(c2);
        //paint.setColor(Color.argb(255,0,255,255));
        paint.setColor(c2);
        compositeCanvas.drawCircle(x / 2 , y / 2 + 150, 200,paint);
//        Paint p3= new Paint();
//        p3.setColor(c1);
        //paint.setColor(Color.argb(255,0,255,0));
        paint.setColor(c3);
        compositeCanvas.drawCircle(x / 2 - 150, y / 2, 200, paint);
//        Paint p4= new Paint();
//        p1.setColor(c4);
        //paint.setColor(Color.argb(255,255,0,0));
        paint.setColor(c4);
        compositeCanvas.drawCircle(x / 2 + 150, y / 2, 200, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(28);
        canvas.drawBitmap(compositeBitmap, 0, 0, null);
        canvas.drawText(s1, 50, 580, paint);
        canvas.drawText(s2, 530, 580, paint);
        canvas.drawText(s3, 310, 350, paint);
        canvas.drawText(s4, 280, 810, paint);
        canvas.drawText(s5, 190, 465, paint);
        canvas.drawText(s6, 420 , 465, paint);
        canvas.drawText(s7, 190, 665, paint);
        canvas.drawText(s8, 420, 665, paint);
    }




    public int gerarCor(){
        // Paint paint = new Paint();
        Random random = new Random ();
        int r = random.nextInt(155)+100;
        int g = random.nextInt(155)+100;
        int b = random.nextInt(155)+100;
        // paint.setStyle(Paint.Style.FILL);
        // paint.setColor(Color.WHITE);
        return Color.argb(122, r, g, b);
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }
    public void setC2(int c2) {
        this.c2 = c2;
    }
    public void setC3(int c3) {
        this.c3 = c3;
    }
    public void setC4(int c4){ this.c4 = c4; }
    public int getC1() { return c1; }
    public int getC2() { return c2; }
    public int getC3() { return c3; }
    public int getC4() { return c4; }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public void setS7(String s7) {
        this.s7 = s7;
    }

    public void setS8(String s8) {
        this.s8 = s8;
    }
}
