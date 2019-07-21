package com.uws.pnai.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button aButton;
    Button bButton;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aButton = (Button)findViewById(R.id.toActA);
        bButton = (Button)findViewById(R.id.toActB);

        aButton.setOnClickListener(this);
        bButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toActA:
                Toast.makeText(this, "Button A been clicked.", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ActivityA.class);
                startActivity(intent);
                break;
            case R.id.toActB:
                Toast.makeText(this, "Button B been clicked.", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ActivityB.class);
                startActivity(intent);
                break;
        }
    }
}
