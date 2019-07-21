package com.yuushigames.leaguechampionpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class lanes extends AppCompatActivity implements View.OnClickListener {

    ImageButton aButton;
    ImageButton bButton;
    ImageButton cButton;
    ImageButton dButton;
    ImageButton eButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanes);

        aButton = (ImageButton)findViewById(R.id.topButton);
        bButton = (ImageButton)findViewById(R.id.jungleButton);
        cButton = (ImageButton)findViewById(R.id.midButton);
        dButton = (ImageButton)findViewById(R.id.supportButton);
        eButton = (ImageButton)findViewById(R.id.adcButton);

        aButton.setOnClickListener(this);
        bButton.setOnClickListener(this);
        cButton.setOnClickListener(this);
        dButton.setOnClickListener(this);
        eButton.setOnClickListener(this);
    }

    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.topButton:
                intent = new Intent(this,filters.class);
                startActivity(intent);
                break;

            case R.id.jungleButton:
                intent = new Intent(this,filters.class);
                startActivity(intent);
                break;

            case R.id.midButton:
                intent = new Intent(this,filters.class);
                startActivity(intent);
                break;

            case R.id.supportButton:
                intent = new Intent(this,filters.class);
                startActivity(intent);
                break;

            case R.id.adcButton:
                intent = new Intent(this,filters.class);
                startActivity(intent);
                break;
        }
    }
}
