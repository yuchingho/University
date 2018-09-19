package com.uws.assessment01campingtrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class place3 extends AppCompatActivity implements View.OnClickListener {

    Button bButton; // links to back button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place3);

        bButton = (Button) findViewById(R.id.backButton);
        bButton.setOnClickListener(this);
    }

    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.backButton:
                intent = new Intent(this,places.class);
                startActivity(intent);
                break;
        }
    }
}