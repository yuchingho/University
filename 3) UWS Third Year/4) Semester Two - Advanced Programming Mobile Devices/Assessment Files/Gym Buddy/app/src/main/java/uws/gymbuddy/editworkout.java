package uws.gymbuddy;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class editworkout extends AppCompatActivity /* implements View.OnClickListener, AdapterView.OnItemSelectedListener */ {

    DatabaseHelper db ;
    EditText dayText, exerciseText, repNumberText, setNumberText, weightNumberText, editText;
    Button addExercise, deleteExercise, viewRoutine, updateRoutine;
    boolean isInserted;
    Spinner day;

    public void btnAdd(View view) {
        String e  = exerciseText.getText().toString();
        String r  = repNumberText.getText().toString();
        String s  = setNumberText.getText().toString();
        String w  = weightNumberText.getText().toString();
        String d = day.getSelectedItem().toString();

        db.addData(e, r, s, w, d);

        if (isInserted = true)
            Toast.makeText(editworkout.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(editworkout.this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }

    public void btnViewList(View view) {
        Intent intent = new Intent(editworkout.this,ListDataActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editroutine);

        db = new DatabaseHelper(this);

        //code to allow the spinner to work
        day = findViewById(R.id.days);
        String[] items = new String[]{"Monday" , "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        day.setAdapter(adapter);

        dayText = (EditText) findViewById(R.id.inDay);
        exerciseText = (EditText) findViewById(R.id.inExercise);
        repNumberText = (EditText) findViewById(R.id.inReps);
        setNumberText = (EditText) findViewById(R.id.inSets);
        weightNumberText = (EditText) findViewById(R.id.inWeight);

        // new buttons added to allow database to be updated with CRUD methods
        addExercise = (Button) findViewById(R.id.addExercise);
        // deleteExercise = (Button) findViewById(R.id.deleteExercise);
        viewRoutine = (Button) findViewById(R.id.viewRoutine);
        // updateRoutine = (Button) findViewById(R.id.updateRoutine);

        viewRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(editworkout.this,ListDataActivity.class);
            startActivity(intent);
            }
        });
    }
}
