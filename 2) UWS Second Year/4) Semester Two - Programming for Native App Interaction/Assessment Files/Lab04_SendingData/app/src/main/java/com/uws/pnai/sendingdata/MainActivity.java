package com.uws.pnai.sendingdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    EditText nameIn;
    Spinner fruitSpin;
    Button aButton;

    private static final String[] fruitItems = {"Select fruit", "Apple", "Banana", "Orange", "Melon"};
    String userName = "enter name";
    String chosenFruit ="not chosen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameIn = (EditText)findViewById(R.id.inName);
        fruitSpin = (Spinner)findViewById(R.id.fruitSpinner);
        fruitSpin.setOnItemSelectedListener(this);

        ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fruitItems);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); fruitSpin.setAdapter(aa);
        aButton = (Button)findViewById(R.id.toActOut);
        aButton.setOnClickListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        chosenFruit = fruitItems[position]; } public void onNothingSelected(AdapterView<?> parent) {
        chosenFruit = "";
    }

    public void onClick(View view) {
        Intent intent; String sName = nameIn.getText().toString();
        intent = new Intent(this, FruitChoice.class);
        intent.putExtra("nameTran", sName);
        intent.putExtra("chosenFruit", chosenFruit); startActivity(intent);
    }
}
