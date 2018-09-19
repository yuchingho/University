package com.uws.pnai.menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "cereal"); // Group, Item ID, Order, Title
        menu.add(0, 2, 1, "muesli");
        menu.add(1, 3, 2, "bacon");
        menu.add(1, 4, 1, "eggs");
        menu.add(1, 5, 2, "toast");
        menu.add(2, 6, 3, "porridge");
        menu.add(2, 7, 4, "kipper");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (1): {
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            }
            case (2): {
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            }
            // and so on
        }
        return super.onOptionsItemSelected(item);
    }
}
