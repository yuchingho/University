package com.uws.pnai.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    Button myButton;
    CheckBox cb;  // Declaration of the checkbox
    private TextView selection;
    Spinner spin;
    private static final String[] items = {"Nibali", "Froome", "Wiggins", "Evans", "Schleck*", "Contador", "Sastre", "Pereiro"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = (Button)findViewById(R.id.toastButton);
        myButton.setOnClickListener(this);

        cb = (CheckBox)findViewById(R.id.checkBox); // Connect the java checkbox to the xml checkbox
        cb.setOnCheckedChangeListener(this);    // Attach the listener to the checkbox

        selection = (TextView)findViewById(R.id.textselection);
        spin = (Spinner)findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }

    public void onClick(View view) {
        Toast.makeText(this,"Button has been clicked.", Toast.LENGTH_LONG).show();
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked == true) {
            cb.setText("This checkbox is: checked");
        }
        else {
            cb.setText("This checkbox is now unchecked");
        }
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        selection.setText(items[position]);
    }
    public void onNothingSelected(AdapterView<?> parent) {
        selection.setText("");
    }
}
