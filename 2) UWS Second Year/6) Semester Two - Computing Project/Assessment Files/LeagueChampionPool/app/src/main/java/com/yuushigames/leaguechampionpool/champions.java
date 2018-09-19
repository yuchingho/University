package com.yuushigames.leaguechampionpool;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class champions extends AppCompatActivity implements View.OnClickListener {

    ImageButton aButton; // links to remove button
    ImageButton bButton; // links to lanes button
    ImageButton cButton; // links to save button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);

        // Buttons
        aButton = (ImageButton)findViewById(R.id.menuButton);
        bButton = (ImageButton)findViewById(R.id.lanesButton);
        cButton = (ImageButton)findViewById(R.id.saveButton);

        aButton.setOnClickListener(this);   // links to remove button
        bButton.setOnClickListener(this);   // links to lane button
        cButton.setOnClickListener(this);   // links to save button

    }

    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.menuButton:
                intent = new Intent(this,home.class);
                startActivity(intent);
                break;

            case R.id.lanesButton:
                intent = new Intent(this,lanes.class);
                startActivity(intent);
                break;

            case R.id.saveButton:
                Toast.makeText(this, "Saved preferences!", Toast.LENGTH_SHORT).show();
                //startActivity(intent);
                break;
        }

    }
}