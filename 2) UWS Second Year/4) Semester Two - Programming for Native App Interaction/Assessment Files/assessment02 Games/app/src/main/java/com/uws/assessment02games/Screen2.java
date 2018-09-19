package com.uws.assessment02games;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Screen2 extends AppCompatActivity implements View.OnClickListener {

    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        play = (Button) findViewById(R.id.playButton);
        play.setOnClickListener(this);

    }

    public void onClick(View view) {

        Intent intent;
        intent = new Intent(this, SurfaceGameActivity.class);
        startActivity(intent);
    }
}
