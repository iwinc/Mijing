package com.example.mijing.Tools;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Created by 程延宏 on 2017/4/27 0027.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
