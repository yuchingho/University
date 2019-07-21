package com.uws.assessment02games;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button aButton;
    Button bButton;

    GameView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aButton = (Button)findViewById(R.id.gameOneButton);
        bButton = (Button)findViewById(R.id.gameTwoButton);

        aButton.setOnClickListener(this);
        bButton.setOnClickListener(this);
    }

    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.gameOneButton:
                intent = new Intent(this,Screen1.class);
                startActivity(intent);
                break;

            case R.id.gameTwoButton:
                intent = new Intent(this,Screen2.class);
                startActivity(intent);
                break;
        }

    }
}
