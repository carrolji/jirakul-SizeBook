package com.example.jirakul_sizebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class DetailsActivity extends AppCompatActivity {

    private Button saveData;
    private EditText editText1;
    private SharedPreferences savednotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        saveData = (Button) findViewById(R.id.save_data);
        editText1 = (EditText) findViewById(R.id.edit_date);
        savednotes = getSharedPreferences("notes",MODE_PRIVATE);

        editText1.setText(savednotes.getString("tag","Edit"));

        saveData.setOnClickListener(saveButtonListener);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void makeTag(String tag){
        String or = savednotes.getString(tag, null);
        SharedPreferences.Editor preferencesEditor = savednotes.edit();
        preferencesEditor.putString("tag",tag); //change this line to this
        preferencesEditor.commit();
    }

    public View.OnClickListener saveButtonListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(editText1.getText().length()>0){
                makeTag(editText1.getText().toString());

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText1.getWindowToken(),0);

            }
        }
    };


}
