package uws.gymbuddy;

import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.FontsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drhal on 03/03/2018.
 */

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper db;

    TableLayout tableLayout;
    TableRow tr;
    TextView tx1, tx2, tx3, tx4, tx5, tx6;
    ArrayList<TableRow> rowList;
    ArrayList<Button> btnDeleteEx;

    int rowCount;
    Spinner day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine_day_list);

        rowList = new ArrayList<>();
        rowCount = 0;
        int dataCount = 0;

        // spinner
        day = findViewById(R.id.days_View);
        String[] items = new String[]{"All Days","Monday" , "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        day.setAdapter(adapter);


        db = new DatabaseHelper(this);
        tableLayout= findViewById(R.id.table_contents);

        tableLayout.setColumnStretchable(0,true);
        tableLayout.setColumnStretchable(1,true);
        tableLayout.setColumnStretchable(2,true);
        tableLayout.setColumnStretchable(3,true);
        tableLayout.setColumnStretchable(4,true);

        tr = new TableRow(this);

        tx1 = new TextView(this);
        tx2 = new TextView(this);
        tx3 = new TextView(this);
        tx4 = new TextView(this);
        tx5 = new TextView(this);
        tx6 = new TextView(this);

        tx1.setText("Exercise ");
        tx1.setGravity(Gravity.CENTER);
        tx1.setTextSize(20);
        tx1.setTypeface(tx1.getTypeface(), Typeface.BOLD);

        tx2.setText("Reps ");
        tx2.setGravity(Gravity.CENTER);
        tx2.setTextSize(20);
        tx2.setTypeface(tx2.getTypeface(), Typeface.BOLD);


        tx3.setText("Sets ");
        tx3.setGravity(Gravity.CENTER);
        tx3.setTextSize(20);
        tx3.setTypeface(tx3.getTypeface(), Typeface.BOLD);


        tx4.setText("Weigh KG ");
        tx4.setGravity(Gravity.CENTER);
        tx4.setTextSize(20);
        tx4.setTypeface(tx4.getTypeface(), Typeface.BOLD);

        tx5.setText("Day");
        tx5.setGravity(Gravity.CENTER);
        tx5.setTextSize(20);
        tx5.setTypeface(tx5.getTypeface(), Typeface.BOLD);

        tx6.setText("Delete");
        tx6.setGravity(Gravity.CENTER);
        tx6.setTextSize(20);
        tx6.setTypeface(tx6.getTypeface(), Typeface.BOLD);

        tr.addView(tx1);
        tr.addView(tx2);
        tr.addView(tx3);
        tr.addView(tx4);
        tr.addView(tx5);
        tr.addView(tx6);

        tableLayout.addView(tr);

        ArrayList<String> theList = new ArrayList<>();
        ArrayList<Integer> idList = new ArrayList<>();
        Cursor data = db.getListContents(day.getSelectedItem().toString());

        ArrayList<TextView> txList = new ArrayList<>();
        btnDeleteEx = new ArrayList<>();
        while (data.moveToNext()) {

            idList.add(Integer.valueOf(data.getString(0)));

            rowList.add(new TableRow(this));
            for (int i = 0; i < 5; i++) {
                theList.add(data.getString(i + 1));
                txList.add(new TextView(this));
                txList.get(dataCount).setText(theList.get(dataCount));
                txList.get(dataCount).setGravity(Gravity.CENTER);
                rowList.get(rowCount).addView(txList.get(dataCount));
                dataCount++;
            }

            btnDeleteEx.add(new Button(this));
            // btnDeleteEx.get(rowCount).setText(theList.get(dataCount - 5).toString());
            btnDeleteEx.get(rowCount).setText("X");
            btnDeleteEx.get(rowCount).setId(idList.get(rowCount));
            System.out.println(btnDeleteEx.get(rowCount).getId() + "----------- ID");
            btnDeleteEx.get(rowCount).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    db.deleteExercise(((Button)v).getId());
                    System.out.println(((Button)v).getId() + "");
                    finish();
                    startActivity(getIntent());
                }
            });

            // db.deleteExercise("10"); MANUAL REMOVE
            rowList.get(rowCount).addView(btnDeleteEx.get(rowCount));
            tableLayout.addView(rowList.get(rowCount++));
        }
    }
}