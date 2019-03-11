package com.example.mycontactlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ContactSettingsActivity extends AppCompatActivity {

    static SharedPreferences prefs;
    static View scrollviewobject;
    //Scrollview Object for changing the background color and SharedPrefs object for storing color preference.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_settings);
        RadioButton rbName = (RadioButton) findViewById(R.id.radioName);

        scrollviewobject = findViewById(R.id.scrollView1);

        prefs = getSharedPreferences("bgColor", MODE_PRIVATE); //setting the shared prefs object to private with the name bgColor
        SharedPreferences.Editor editor = prefs.edit(); //edit object for changing the color preference

        String colorStr = prefs.getString("color", "WHITE"); //String for storing the String value of the color in SharedPrefs

        if(colorStr.equals("BLUE")){
            scrollviewobject.setBackgroundResource(R.color.blue);
        }
        else if(colorStr.equals("YELLOW")){
            scrollviewobject.setBackgroundResource(R.color.yellow);
        }
        else if(colorStr.equals("RED")){
            scrollviewobject.setBackgroundResource(R.color.red);
        }
        else{
            scrollviewobject.setBackgroundResource(R.color.white);
        }
        //changing the color of the scrollview depending on the colorStr

        initListButton();
        initSettingsButton();
        initMapButton();
        initSettings();
        initSortByClick();
        initSortOrderClick();
        initColorClick();

    }

    private void initListButton() {
        ImageButton ibList = (ImageButton) findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ContactSettingsActivity.this, ContactListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initMapButton() {
        ImageButton ibList = (ImageButton) findViewById(R.id.imageButtonMap);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ContactSettingsActivity.this, ContactMapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initSettingsButton() {
        ImageButton ibSettings = (ImageButton) findViewById(R.id.imageButtonSettings);
        ibSettings.setEnabled(false);
    }

    private void initSettings() {
        String sortBy = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortfield","contactname");
        String sortOrder = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortorder","ASC");

        RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
        RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);
        RadioButton rbBirthDay = (RadioButton) findViewById(R.id.radioBirthday);
        if (sortBy.equalsIgnoreCase("contactname")) {
            rbName.setChecked(true);
        }
        else if (sortBy.equalsIgnoreCase("city")) {
            rbCity.setChecked(true);
        }
        else {
            rbBirthDay.setChecked(true);
        }

        RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending);
        RadioButton rbDescending = (RadioButton) findViewById(R.id.radioDescending);
        if (sortOrder.equalsIgnoreCase("ASC")) {
            rbAscending.setChecked(true);
        }
        else {
            rbDescending.setChecked(true);
        }
    }

    private void initSortByClick() {
        RadioGroup rgSortBy = (RadioGroup) findViewById(R.id.radioGroupSortBy);
        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
                RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);
                if (rbName.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit() .putString("sortfield", "contactname").commit();
                }
                else if (rbCity.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield", "city").commit();
                }
                else {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield", "birthday").commit();
                }
            }
        });
    }

    private void initSortOrderClick() {
        RadioGroup rgSortOrder = (RadioGroup) findViewById(R.id.radioGroupSortOrder);
        rgSortOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending);
                if (rbAscending.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortorder", "ASC").commit();
                }
                else {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortorder", "DESC").commit();
                }
            }
        });
    }

    private void initColorClick() {
        RadioGroup bgColor = (RadioGroup) findViewById(R.id.radioGroupColor);
        bgColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbBlue = (RadioButton) findViewById(R.id.radioBlue);
                RadioButton rbRed = (RadioButton) findViewById(R.id.radioRed);
                RadioButton rbYellow = (RadioButton) findViewById(R.id.radioYellow);
                if (rbBlue.isChecked()) {
                    prefs.edit().putString("color", "BLUE").commit();
                    scrollviewobject.setBackgroundResource(R.color.blue);
                }
                else if (rbRed.isChecked()) {
                    prefs.edit().putString("color", "RED").commit();
                    scrollviewobject.setBackgroundResource(R.color.red);
                }
                if (rbYellow.isChecked()) {
                    prefs.edit().putString("color", "YELLOW").commit();
                    scrollviewobject.setBackgroundResource(R.color.yellow);
                }
            }//method for allowing the color radio button group to edit the bgColor sharedprefs
        });
    }
    //initiates the the bgColor radio group

}
