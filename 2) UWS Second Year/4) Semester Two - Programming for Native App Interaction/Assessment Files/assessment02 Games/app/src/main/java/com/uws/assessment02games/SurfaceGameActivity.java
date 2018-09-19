package com.uws.assessment02games;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SurfaceGameActivity extends Activity {

    SurfaceGameView SGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SGV = new SurfaceGameView(this);
        setContentView(SGV);
    }
}