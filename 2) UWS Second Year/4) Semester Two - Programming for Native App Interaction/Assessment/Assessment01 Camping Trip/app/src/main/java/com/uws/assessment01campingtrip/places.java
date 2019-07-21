package com.uws.assessment01campingtrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class places extends AppCompatActivity implements View.OnClickListener {

    Button aButton; // links to place A - Lothianside Camping and Caravanning Park
    Button bButton; // links to place B - Loch Bannoch Touring Park
    Button cButton; // links to place C - Mckeowns Holiday Park
    Button mButton; // links to menu button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        aButton = (Button)findViewById(R.id.placeAButton);
        bButton = (Button)findViewById(R.id.placeBButton);
        cButton = (Button)findViewById(R.id.placeCButton);
        mButton = (Button)findViewById(R.id.menuButton);

        aButton.setOnClickListener(this);
        bButton.setOnClickListener(this);
        cButton.setOnClickListener(this);
        mButton.setOnClickListener(this);
    }

    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.placeAButton:
                intent = new Intent(this,place1.class);
                startActivity(intent);
                break;
            case R.id.placeBButton:
                intent = new Intent(this,place2.class);
                startActivity(intent);
                break;
            case R.id.placeCButton:
                intent = new Intent(this,place3.class);
                startActivity(intent);
                break;
            case R.id.menuButton:
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
