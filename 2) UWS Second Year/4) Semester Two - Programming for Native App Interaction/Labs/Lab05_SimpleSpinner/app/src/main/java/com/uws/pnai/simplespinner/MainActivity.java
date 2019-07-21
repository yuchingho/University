package com.uws.pnai.simplespinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView selection;
    Spinner spin;
    private static final String[] items={"Nibali", "Froome", "Wiggins", "Evans", "Schleck*", "Contador", "Sastre", "Pereiro"};

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selection=(TextView)findViewById(R.id.selection);
        spin=(Spinner)findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        selection.setText(items[position]);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        selection.setText("");
    }
}