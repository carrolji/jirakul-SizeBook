package com.example.jirakul_sizebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.example.jirakul_sizebook.R.styleable.FloatingActionButton;

public class DetailsActivity extends AppCompatActivity {

    private Button saveData;
    private SharedPreferences savednotes;
    private EditText nameEditText;
    private EditText dateEditText;
    private EditText neckEditText;
    private EditText bustEditText;
    private EditText chestEditText;
    private EditText waistEditText;
    private EditText hipEditText;
    private EditText inseamEditText;
    private EditText commentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameEditText = (EditText) findViewById(R.id.name);
        dateEditText = (EditText) findViewById(R.id.edit_date);
        neckEditText = (EditText) findViewById(R.id.edit_neck);
        bustEditText = (EditText) findViewById(R.id.edit_bust);
        chestEditText  = (EditText) findViewById(R.id.edit_chest);
        waistEditText = (EditText) findViewById(R.id.edit_waist);
        hipEditText = (EditText) findViewById(R.id.edit_hip);
        inseamEditText = (EditText) findViewById(R.id.edit_inseam);
        commentEditText = (EditText) findViewById(R.id.edit_comment);

        saveData = (Button) findViewById(R.id.save_data);
        savednotes = getSharedPreferences("notes",MODE_PRIVATE);

        nameEditText.setText(savednotes.getString("tag","date"));
        neckEditText.setText(savednotes.getString("tag","neck"));

        saveData.setOnClickListener(saveButtonListener);

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
            if(nameEditText.getText().length()>0){
                makeTag(nameEditText.getText().toString());

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(nameEditText.getWindowToken(),0);

            }

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
