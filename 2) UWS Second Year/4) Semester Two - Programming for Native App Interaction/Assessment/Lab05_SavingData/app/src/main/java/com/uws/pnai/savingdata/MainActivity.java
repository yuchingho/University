package com.uws.pnai.savingdata;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    EditText userName;
    Button inButton, outButton, toNewActivity;
    SharedPreferences mySharedPreferences;
    String name;
    TextView nameIn, storedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText)findViewById(R.id.nameInput);

        nameIn = (TextView)findViewById(R.id.nameIn);
        storedName = (TextView)findViewById(R.id.nameOut);

        inButton = (Button)findViewById(R.id.storeName);
        inButton.setOnClickListener(this);

        outButton = (Button)findViewById(R.id.retrieveName);
        outButton.setOnClickListener(this);

        toNewActivity = (Button)findViewById(R.id.newActivity);
        toNewActivity.setOnClickListener(this);

        mySharedPreferences = getSharedPreferences("myPrefsFile", MainActivity.MODE_PRIVATE);
        // declares the shared preferences object
    }

    @Override
    public void onClick(View view) {

        SharedPreferences.Editor editor = mySharedPreferences.edit();  // creates the editor

        switch (view.getId()) {
            case R.id.storeName:
                Toast.makeText(this, "Store name button.", Toast.LENGTH_LONG).show();
                name = userName.getText().toString();
                nameIn.setText(name);
                editor.putString("clientName", name);
                editor.apply();
                break;
            case R.id.retrieveName:
                Toast.makeText(this, "Retrieve name button", Toast.LENGTH_LONG).show();
                String nameSF = mySharedPreferences.getString("clientName", "not something");
                storedName.setText(nameSF);
                break;
            case R.id.newActivity:
                Toast.makeText(this, "New Activity button", Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(this,ShowName.class);
                //startActivity(intent);
                break;
        }
    }
}
