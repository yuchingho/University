package com.uws.pnai.gameapp;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {
    GameView GV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GV = new GameView(this);
        setContentView(GV);
    }
}


