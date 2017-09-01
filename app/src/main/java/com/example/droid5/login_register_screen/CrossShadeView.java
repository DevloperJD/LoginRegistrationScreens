package com.example.droid5.login_register_screen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.LinearLayout;


/**
 * Created by droid5 on 30/8/17.
 */

public class CrossShadeView extends ConstraintLayout


{
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;


    public CrossShadeView(Context context) {
        super(context);
        Init();
    }

    public CrossShadeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init();
    }

    public CrossShadeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }






    private void Init() {
        paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint.setAntiAlias(true);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh) {
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bitmap);
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }








}