package com.yuushigames.leaguechampionpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.yuushigames.leaguechampionpool.R;

public class home extends AppCompatActivity implements View.OnClickListener {

    ImageButton aButton;
    ImageButton bButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        aButton = (ImageButton)findViewById(R.id.selectButton);
        bButton = (ImageButton)findViewById(R.id.laneButton);

        aButton.setOnClickListener(this);
        bButton.setOnClickListener(this);
    }

    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.selectButton:
                intent = new Intent(this,champions.class);
                startActivity(intent);
                break;

            case R.id.laneButton:
                intent = new Intent(this,lanes.class);
                startActivity(intent);
                break;
        }
    }
}
