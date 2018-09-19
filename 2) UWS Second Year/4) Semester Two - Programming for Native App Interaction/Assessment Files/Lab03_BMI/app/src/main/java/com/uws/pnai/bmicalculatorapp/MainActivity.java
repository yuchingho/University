package com.uws.pnai.bmicalculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = (Button)findViewById(R.id.btnCalculate);
        myButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        Toast.makeText(this, "Button has been clicked.", Toast.LENGTH_LONG).show();

        EditText weightText = (EditText)findViewById(R.id.inWeight);
        Toast.makeText(this, weightText.getText(), Toast.LENGTH_LONG).show();

        EditText heightText = (EditText)findViewById(R.id.inHeight);
        Toast.makeText(this, heightText.getText(), Toast.LENGTH_LONG).show();

        float myWeight = Float.parseFloat(weightText.getText().toString());
        float myHeight= Float.parseFloat(heightText.getText().toString());
        float bmi = myWeight/(myHeight*myHeight);

        EditText bmiResult = (EditText)findViewById(R.id.bmiResult);
        bmiResult.setText(String.valueOf(bmi));
    }
}
