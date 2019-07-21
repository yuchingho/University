package com.uws.assessment01campingtrip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.uws.assessment01campingtrip.MainActivity;

public class reservations extends AppCompatActivity implements View.OnClickListener {

    TextView parkTextView;

    TextView radioTextView;

    TextView dateTextView;
    TextView monthTextView;

    TextView nightsTextView;
    TextView adultsTextView;
    TextView teensTextView;
    TextView childrenTextView;

    TextView overageTextView;
    TextView petsTextView;

    TextView costTextView;

    Button mButton; // Menu button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        // Park Spinner
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        parkTextView = (TextView)findViewById(R.id.parkChoiceOut);

        SharedPreferences mySharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String park = mySharedPreferences.getString("parkChoiceOut", "N/A");

        parkTextView.setText(park);

        // Radio Group
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        radioTextView = (TextView)findViewById(R.id.residenceChoiceOut);

        mySharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String radio = mySharedPreferences.getString("radioChoiceOut", "N/A");

        radioTextView.setText(radio);

        // Date Spinner
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        dateTextView = (TextView)findViewById(R.id.dateChoiceOut);

        mySharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String date = mySharedPreferences.getString("dateChoiceOut", "DD");

        dateTextView.setText(date);

        // Month Spinner
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        monthTextView = (TextView)findViewById(R.id.monthChoiceOut);

        mySharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String month = mySharedPreferences.getString("monthChoiceOut", "MM");

        monthTextView.setText(month);

        // TextView
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        nightsTextView = (TextView)findViewById(R.id.nightsOutput);
        adultsTextView = (TextView)findViewById(R.id.adultsOutput);
        teensTextView = (TextView)findViewById(R.id.teensOutput);
        childrenTextView = (TextView)findViewById(R.id.childrenOutput);

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String nights = sharedPreferences.getString("nights", "");
        String adults = sharedPreferences.getString("adults", "");
        String teens = sharedPreferences.getString("teens", "");
        String children = sharedPreferences.getString("children", "");

        nightsTextView.setText(nights);
        adultsTextView.setText(adults);
        teensTextView.setText(teens);
        childrenTextView.setText(children);

        // Overage
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        overageTextView = (TextView)findViewById(R.id.overageOutput);

        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String overage = sharedPreferences.getString("overageOut", "Unchecked");

        overageTextView.setText(overage);

        // Pets
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        petsTextView = (TextView)findViewById(R.id.petsOutput);

        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String pets = sharedPreferences.getString("petsOut", "Unchecked");

        petsTextView.setText(pets);

        // Cost
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        costTextView = (TextView)findViewById(R.id.costOutput);

        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String cost = sharedPreferences.getString("cost", "");

        costTextView.setText( cost);

        // Menu Button
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        mButton = (Button) findViewById(R.id.menuButton);
        mButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.menuButton:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
