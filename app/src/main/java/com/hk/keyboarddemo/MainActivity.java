package com.hk.keyboarddemo;

import android.inputmethodservice.KeyboardView;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private KeyboardView kv_keyboard;
    private EditText editText;
    private LinearLayout ll_keyboard_father;
    private KeyboardUtil keyboardUtil;
    private RelativeLayout rl_hide_keyboard;
    private ConstraintLayout father;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kv_keyboard = findViewById(R.id.kv_keyboard);
        editText = findViewById(R.id.edittext);
        ll_keyboard_father = findViewById(R.id.ll_keyboard_father);
        rl_hide_keyboard = findViewById(R.id.rl_hide_keyboard);
        father = findViewById(R.id.view_root);

        keyboardUtil = new KeyboardUtil(this, editText, kv_keyboard, ll_keyboard_father, true);
        keyboardUtil.forbidSoftInputMethod();
        keyboardUtil.hideKeyboard();

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                keyboardUtil.showKeyboard();

                return false;
            }
        });

        rl_hide_keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyboardUtil.hideKeyboard();
            }
        });

        father.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (keyboardUtil.isShow())
                    keyboardUtil.hideKeyboard();

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.e("TAG", keyCode + "------------");
            if (keyboardUtil.isShow()) {
                keyboardUtil.hideKeyboard();
                return false;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


}
