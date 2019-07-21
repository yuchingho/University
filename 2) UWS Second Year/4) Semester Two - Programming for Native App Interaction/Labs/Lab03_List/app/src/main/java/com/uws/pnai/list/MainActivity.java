package com.uws.pnai.list;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    private TextView selection;
    private static final String[] items = {
            "FROOME Christopher", "VAN GARDEREN Tejay", "URAN URAN Rigoberto",
            "ESP - RODRIGUEZ Joaquim", "GESINK Robert", "POZZOVIVO Domenico",
            "HENAO MONTOYA Sergio", "KREUZIGER Roman", "TSCHOPP Johann",
            "MORENO FERNANDEZ Daniel", "JEANNESSON Arnold", "NIBALI Vincenzo",
            "BARDET Romain", "IMPEY Daryl", "LÖVKVIST Thomas", "VAN DEN BROECK Jurgen",
            "KONIG Leopold", "SANTAROMITA Ivan", "SCHLECK Frank", "NIEVE ITURRALDE Mikel"};

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        selection = (TextView) findViewById(R.id.selection);
    }
    public void onListItemClick(ListView parent, View v, int position, long id) {
        Toast.makeText(this,items[position], Toast.LENGTH_LONG).show();
        selection.setText(items[position]);
    }
}