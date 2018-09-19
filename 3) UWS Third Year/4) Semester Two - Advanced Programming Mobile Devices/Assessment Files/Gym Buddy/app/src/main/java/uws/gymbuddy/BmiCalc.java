package uws.gymbuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by drhall on 18/03/2018.
 */

public class BmiCalc  extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);
    }

    public void calculateBMI(View v) {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr) && weightStr != null  &&  !"".equals(weightStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);
            float bmi = weightValue / (heightValue * heightValue);
            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";

        if (Float.compare(bmi, 15f) <= 0) {
            Toast.makeText(BmiCalc.this, "You are very severely underweight", Toast.LENGTH_SHORT).show();
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            Toast.makeText(BmiCalc.this, "You are severely underweight", Toast.LENGTH_SHORT).show();
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            Toast.makeText(BmiCalc.this, "You are underweight", Toast.LENGTH_SHORT).show();
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            Toast.makeText(BmiCalc.this, "You are normal", Toast.LENGTH_SHORT).show();
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            Toast.makeText(BmiCalc.this, "You are OverWeight", Toast.LENGTH_SHORT).show();
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            Toast.makeText(BmiCalc.this, "You are moderately obese", Toast.LENGTH_SHORT).show();
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            Toast.makeText(BmiCalc.this, "You are severely obese", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BmiCalc.this, "You are very severely obese", Toast.LENGTH_SHORT).show();
        }

        bmiLabel ="Your BMI is\n " + bmi  + bmiLabel;
        result.setText(bmiLabel);
    }
}
