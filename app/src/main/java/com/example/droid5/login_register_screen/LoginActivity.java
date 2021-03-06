package com.example.droid5.login_register_screen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    Button login;
    TextView termsAndConditions, forgetpassword;
    EditText passwordField;
    boolean passwordVisible = false;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // making toolbar transparent
        transparentToolbar();

        setContentView(R.layout.login2);


        login = findViewById(R.id.button);
        forgetpassword = findViewById(R.id.forgot_password);
        termsAndConditions = findViewById(R.id.terms_conditions);
        passwordField = findViewById(R.id.loginScreen_password);

        getSupportActionBar().hide();

        floatingActionButton = findViewById(R.id.fab2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int revealX = (int) (view.getX() + view.getWidth() / 2);
                int revealY = (int) (view.getY() + view.getHeight() / 2);
//

//                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
//                intent.putExtra("positionX", revealX);
//                intent.putExtra("positionY", revealY);
//                startActivity(intent);

                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(LoginActivity.this, floatingActionButton, "fab");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Login Button was Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Forget Password ?", Toast.LENGTH_SHORT).show();
            }
        });
        termsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Terms and Conditions was Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        passwordField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (passwordField.getRight() - passwordField.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        Log.e(TAG, "on drawable right tap");
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
