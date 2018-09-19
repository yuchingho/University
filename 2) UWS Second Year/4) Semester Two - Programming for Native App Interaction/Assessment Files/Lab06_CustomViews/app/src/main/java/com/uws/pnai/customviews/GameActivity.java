package com.uws.pnai.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    GameView GV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);

        GV = new GameView(this);// creates the custom view – invoking the GameView constructor
        setContentView(GV); //sets the content view to the game view and that is then displayed.
    }
}
