package uws.gymbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class menu extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    Button editWorkoutButton;
    Button view_list_button;
    Button bmiButton;
    Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        editWorkoutButton = (Button)findViewById(R.id.editWorkout);
        bmiButton = (Button)findViewById(R.id.bmi);
        view_list_button = (Button)findViewById(R.id.view_list);
        editWorkoutButton.setOnClickListener(this);
        bmiButton.setOnClickListener(this);
        view_list_button.setOnClickListener(this);
    }

    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.view_list:
                intent = new Intent(this, ListDataActivity.class);
                startActivity(intent);
                break;
            case R.id.editWorkout:
                intent = new Intent(this, editworkout.class);
                startActivity(intent);
                break;
            case R.id.bmi:
                 intent = new Intent(this, BmiCalc.class);
                startActivity(intent);
                break;
        }
    }
}