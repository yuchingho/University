package com.uws.pnai.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button myButton;
    private float EUROS_TO_POUND = 1.22f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = (Button)findViewById(R.id.btnConvert);
        myButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        float exchange;

        EditText inputValueText = (EditText)findViewById(R.id.txtAmount);
        float myAmount = Float.parseFloat(inputValueText.getText().toString());
        RadioButton cbSelected = (RadioButton)findViewById(R.id.radPounds);

        if (cbSelected.isChecked()) {
            exchange = eurosToPound(myAmount);
        }
        else {
            exchange = poundToEuros(myAmount);
        }

        EditText outputValueText = (EditText)findViewById(R.id.txtResult);
        outputValueText.setText(String.valueOf(exchange));
    }


    private float poundToEuros(float value){
        return (value / EUROS_TO_POUND);
    }
    private float eurosToPound(float value){
        return value * EUROS_TO_POUND;
    }
}
