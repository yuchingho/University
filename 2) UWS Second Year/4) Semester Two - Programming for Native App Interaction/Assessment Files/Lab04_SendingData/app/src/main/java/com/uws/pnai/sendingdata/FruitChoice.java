package com.uws.pnai.sendingdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FruitChoice extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_choice);
        Intent intent = getIntent(); String uir = intent.getStringExtra("nameTran");
        String uir2 = intent.getStringExtra("chosenFruit");
        TextView textView = (TextView)findViewById(R.id.nameOut);
        textView.setText(uir);
        TextView textView2 = (TextView)findViewById(R.id.fruitChoiceOut);
        textView2.setText(uir2);
    }
}
