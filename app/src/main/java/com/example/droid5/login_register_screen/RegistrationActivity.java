package com.example.droid5.login_register_screen;

import android.animation.Animator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;


public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = RegistrationActivity.class.getSimpleName();
    ImageView clear;
    boolean passwordVisible = false;
    EditText passwordField;
    View layout;
    int positionX, positionY;
    float finalRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transparentToolbar();
        setContentView(R.layout.registration);
        clear = findViewById(R.id.clear);
        getSupportActionBar().hide();
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                onBackPressed();

            }
        });
        passwordField = findViewById(R.id.passwordfieldRegistration);
        layout = findViewById(R.id.registerLayout);


        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //makeTransition();
        }

        passwordField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (passwordField.getRight() - passwordField.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if (passwordVisible) {
                            passwordVisible = false;
                            passwordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        } else {
                            passwordVisible = true;
                            passwordField.setInputType(InputType.TYPE_CLASS_TEXT);
                        }


                        return true;
                    }
                }
                return false;
            }
        });


    }

    private void makeTransition() {
        ViewTreeObserver viewTreeObserver = layout.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    final Intent intent = getIntent();
                    positionX = intent.getIntExtra("positionX", 0);
                    positionY = intent.getIntExtra("positionY", 0);
                    finalRadius = (float) (Math.max(layout.getWidth(), layout.getHeight()) * 1.1);
                    Animator animator = ViewAnimationUtils.createCircularReveal(layout, positionX, positionY, 0, finalRadius);
                    animator.setDuration(400);
                    layout.setVisibility(View.VISIBLE);
                    animator.start();
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                }
            });
        } else layout.setVisibility(View.VISIBLE);


    }


    private void transparentToolbar() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
