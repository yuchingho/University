package com.uws.assessment01campingtrip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.uws.assessment01campingtrip.R;
import com.uws.assessment01campingtrip.reservations;

public class booking extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener   {

    Spinner parkSpin;

    RadioButton cabin;
    RadioButton caravan;
    RadioButton tent;

    Spinner dateSpin;
    Spinner monthSpin;

    EditText nightsText;   //Put number in for number of nights
    EditText adultsText;
    EditText teensText;
    EditText childrenText;

    Switch overage;
    CheckBox pets;

    EditText cost;
    Button cButton; // Links to Cost Button

    Button Button;     // Links to Confirm button

    // Park Spinner Choices
    private static final String[] parkItems = {"Select", "Lothianside Camping and Caravanning Park", "Loch Bannoch Touring Park", "Mckeowns Holiday Park"};
    String parkChoice = "not chosen";

    // Date Spinner Choices
    private static final String[] dateItems = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" , "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",  "27", "28", "29", "30", "31",};
    String dateChoice = "not chosen";

    // Month Spinner Choices
    private static final String[] monthItems = {"MM", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String monthChoice = "not chosen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Park Spinner
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        parkSpin = (Spinner) findViewById(R.id.parkSpinner);
        parkSpin.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, parkItems);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parkSpin.setAdapter(aa);

        // Radio Group
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cabin = (RadioButton) findViewById(R.id.cabinRadio);
        caravan = (RadioButton) findViewById(R.id.caravanRadio);
        tent = (RadioButton) findViewById(R.id.tentRadio);

        cabin.setChecked(false);
        caravan.setChecked(false);
        tent.setChecked(false);

        // Date Spinner
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        dateSpin = (Spinner) findViewById(R.id.dateSpinner);
        dateSpin.setOnItemSelectedListener(this);
        ArrayAdapter<String> bb = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dateItems);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpin.setAdapter(bb);

        // Month Spinner
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        monthSpin = (Spinner) findViewById(R.id.monthSpinner);
        monthSpin.setOnItemSelectedListener(this);
        ArrayAdapter<String> cc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monthItems);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpin.setAdapter(cc);

        // TextView
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        nightsText = (EditText) findViewById(R.id.nightsInput);
        adultsText = (EditText) findViewById(R.id.adultsInput);
        teensText = (EditText) findViewById(R.id.teensInput);
        childrenText = (EditText) findViewById(R.id.childrenInput);

        // Overage
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        overage = (Switch) findViewById(R.id.overageSwitch);
        overage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    overage.setText("Yes");
                }
                else {
                    overage.setText("No");
                }
            }
        });
        // Pets
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        pets = (CheckBox) findViewById(R.id.petsCheckBox);
        pets.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pets.setText("Yes");
                }
                else {
                    pets.setText("No");
                }
            }
        });

        // Cost Button
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cost = (EditText) findViewById(R.id.costText);
        // cButton links to costButton
        cButton = (Button) findViewById(R.id.costButton);
        cButton.setOnClickListener(this);

        // Confirm Button
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Button = (Button) findViewById(R.id.confirmButton);
        Button.setOnClickListener(this);
    }

    // Selection of Spinners
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (parent.getId()) {

            case R.id.parkSpinner:
                break;

            case R.id.dateSpinner:
                break;

            case R.id.monthSpinner:
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        switch (parent.getId()) {

            case R.id.parkSpinner:
                parkChoice = "";
                break;

            case R.id.dateSpinner:
                dateChoice = "";
                break;

            case R.id.monthSpinner:
                monthChoice = "";
                break;
        }
    }

    // When Confirm Button clicked
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void onClick(View view) {

        // Shared Preferences
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch(view.getId()){
            case (R.id.confirmButton):

                // Park Spinner saved data
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
                editor.putString("parkChoiceOut", parkSpin.getSelectedItem().toString());
                editor.commit();

                // Radio Group saved data
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if (cabin.isChecked()) {
                    RadioButton cabin = (RadioButton)findViewById(R.id.cabinRadio);
                    editor.putString("radioChoiceOut", cabin.getText().toString());
                }
                if (caravan.isChecked()) {
                    RadioButton caravan = (RadioButton) findViewById(R.id.caravanRadio);
                    editor.putString("radioChoiceOut", caravan.getText().toString());
                }
                if (tent.isChecked()) {
                    RadioButton tent = (RadioButton)findViewById(R.id.tentRadio);
                    editor.putString("radioChoiceOut", tent.getText().toString());
                }
                editor.commit();

                // Date Spinner saved data
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
                editor.putString("dateChoiceOut", dateSpin.getSelectedItem().toString());
                editor.commit();

                // Month Spinner saved data
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
                editor.putString("monthChoiceOut", monthSpin.getSelectedItem().toString());
                editor.commit();

                // TextView saved data
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
                editor.putString("nights", nightsText.getText().toString());
                editor.putString("adults", adultsText.getText().toString());
                editor.putString("teens", teensText.getText().toString());
                editor.putString("children", childrenText.getText().toString());
                editor.commit();

                // Overage saved data
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(overage.isChecked()) {
                    editor.putString("overageOut", "Yes");
                }
                else {
                    editor.putString("overageOut", "No");
                }
                editor.putString("overageOut", overage.getText().toString());
                editor.commit();

                // Pets saved data
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(pets.isChecked()) {
                    editor.putString("petsOut", "Yes");
                }
                else {
                    editor.putString("petsOut", "No");
                }
                editor.putString("petsOut", pets.getText().toString());
                editor.commit();

                Intent intent = new Intent(this, reservations.class);
                startActivity(intent);
                break;

            case (R.id.costButton):

                // There's a small bug of if you do not put in a value for any of nights/adults/teens/children and press "calculate cost", the app crashes.

                float nights;

                if (cabin.isChecked()) {
                    nights = Float.parseFloat(nightsText.getText().toString()) * 45;

                    float adults = Float.parseFloat(adultsText.getText().toString());
                    float teens = Float.parseFloat(teensText.getText().toString());
                    float children = Float.parseFloat(childrenText.getText().toString());

                    float people = nights + (adults * 10) + (teens * 5) + (children * 3);

                    cost.setText(String.valueOf(people));
                }

                if (caravan.isChecked()) {
                    nights = Float.parseFloat(nightsText.getText().toString()) * 25;

                    float adults = Float.parseFloat(adultsText.getText().toString());
                    float teens = Float.parseFloat(teensText.getText().toString());
                    float children = Float.parseFloat(childrenText.getText().toString());

                    float people = nights + (adults * 10) + (teens * 5) + (children * 3);

                    cost.setText(String.valueOf(people));
                }

                if (tent.isChecked()) {
                    nights = Float.parseFloat(nightsText.getText().toString()) * 15;

                    float adults = Float.parseFloat(adultsText.getText().toString());
                    float teens = Float.parseFloat(teensText.getText().toString());
                    float children = Float.parseFloat(childrenText.getText().toString());

                    float people = nights + (adults * 10) + (teens * 5) + (children * 3);

                    cost.setText(String.valueOf(people));
                }

                editor.putString("cost", cost.getText().toString());
                editor.commit();

                break;
        }
    }
}