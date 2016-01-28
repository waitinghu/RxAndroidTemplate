package com.bingfeng.rxandroid.common;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    

}
