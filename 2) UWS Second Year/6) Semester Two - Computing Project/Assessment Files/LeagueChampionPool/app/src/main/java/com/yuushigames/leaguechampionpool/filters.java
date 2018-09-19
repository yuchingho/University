package com.yuushigames.leaguechampionpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class filters extends AppCompatActivity implements View.OnClickListener {

    ImageButton aButton; // links to remove button
    ImageButton bButton; // links to lane button
    ImageButton cButton; // links to menu button

    ListView filterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        aButton = (ImageButton)findViewById(R.id.removeButton);
        bButton = (ImageButton)findViewById(R.id.lanesButton);
        cButton = (ImageButton)findViewById(R.id.menuButton);

        aButton.setOnClickListener(this);   // links to remove button
        bButton.setOnClickListener(this);   // links to lanes button
        cButton.setOnClickListener(this);   // links to menu button

    }


    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.removeButton:
                //intent = new Intent(this,League_Champion_Pool_Screen1.class);
                Toast.makeText(this, "Removed !", Toast.LENGTH_SHORT).show();
                //startActivity(intent);
                break;

            case R.id.menuButton:
                intent = new Intent(this,home.class);
                startActivity(intent);
                break;

            case R.id.lanesButton:
                intent = new Intent(this,lanes.class);
                startActivity(intent);
                break;
        }

    }
}