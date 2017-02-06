package com.example.jirakul_sizebook;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.example.jirakul_sizebook.R.styleable.FloatingActionButton;

/**
 * The type Details activity.
 */
public class DetailsActivity extends AppCompatActivity {

    private Button backButton;

    protected TextView saveName;
    protected TextView saveDate;
    protected TextView saveNeck;
    protected TextView saveBust;
    protected TextView saveChest;
    protected TextView saveWaist;
    protected TextView saveHip;
    protected TextView saveInseam;
    protected TextView saveComment;

    protected String oldName;
    protected String oldDate;
    protected String oldNeck;
    protected String oldBust;
    protected String oldChest;
    protected String oldWaist;
    protected String oldHip;
    protected String oldInseam;
    protected String oldComment;

    /**
     * The Contacts list.
     */
    List<Contact> contactsList = new ArrayList<Contact>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Get Serializable key from the MainActivity
         */
        Contact contact = (Contact)getIntent().getSerializableExtra("result");

        saveName = (TextView) findViewById(R.id.name);
        saveDate = (TextView) findViewById(R.id.edit_date);
        saveNeck = (TextView) findViewById(R.id.edit_neck);
        saveBust = (TextView) findViewById(R.id.edit_bust);
        saveChest  = (TextView) findViewById(R.id.edit_chest);
        saveWaist = (TextView) findViewById(R.id.edit_waist);
        saveHip = (TextView) findViewById(R.id.edit_hip);
        saveInseam = (TextView) findViewById(R.id.edit_inseam);
        saveComment = (TextView) findViewById(R.id.edit_comment);

        backButton = (Button) findViewById(R.id.back_button);

        /**
         * Get contact object
         */
        oldName = contact.getName();
        oldDate = contact.getDate();
        oldNeck = contact.getNeck();
        oldBust = contact.getBust();
        oldChest = contact.getChest();
        oldWaist = contact.getWaist();
        oldHip = contact.getHip();
        oldInseam = contact.getInseam();
        oldComment = contact.getComment();

        /**
         * Display contact object in TextView
         */
        saveName.setText(oldName);
        saveDate.setText(oldDate);
        saveNeck.setText(oldNeck);
        saveBust.setText(oldBust);
        saveChest.setText(oldChest);
        saveWaist.setText(oldWaist);
        saveHip.setText(oldHip);
        saveInseam.setText(oldInseam);
        saveComment.setText(oldComment);

        backButton.setOnClickListener(backButtonListener);

    }

    /**
     * The Back button listener,
     * return to main activity
     */
    public View.OnClickListener backButtonListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            finish();

        }
    };





}
