package com.uws.assessment01campingtrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uws.assessment01campingtrip.R;
import com.uws.assessment01campingtrip.booking;
import com.uws.assessment01campingtrip.places;
import com.uws.assessment01campingtrip.reservations;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button aButton; // Places button
    Button bButton; // Book button
    Button cButton; // Reservations button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aButton = (Button)findViewById(R.id.placesButton);
        bButton = (Button)findViewById(R.id.bookButton);
        cButton = (Button)findViewById(R.id.reservationsButton);

        aButton.setOnClickListener(this);
        bButton.setOnClickListener(this);
        cButton.setOnClickListener(this);
    }

    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.placesButton:
                intent = new Intent(this,places.class);
                startActivity(intent);
                break;

            case R.id.bookButton:
                intent = new Intent(this,booking.class);
                startActivity(intent);
                break;

            case R.id.reservationsButton:
                intent = new Intent(this,reservations.class);
                startActivity(intent);
                break;
        }

    }
}
