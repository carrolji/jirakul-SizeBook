package com.example.jirakul_sizebook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.jirakul_sizebook.MainActivity.validateJavaDate;

public class EditActivity extends AppCompatActivity {

    List<Contact> contactsList = new ArrayList<Contact>();
    private EditText nameTxt;
    private EditText dateTxt;
    private EditText neckTxt;
    private EditText bustTxt;
    private EditText chestTxt;
    private EditText waistTxt;
    private EditText hipTxt;
    private EditText inseamTxt;
    private EditText commentTxt;
    private Button saveData;
    protected String oldName;
    protected String oldDate;
    protected String oldNeck;
    protected String oldBust;
    protected String oldChest;
    protected String oldWaist;
    protected String oldHip;
    protected String oldInseam;
    protected String oldComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Contact contact = (Contact)getIntent().getSerializableExtra("result");

        nameTxt = (EditText) findViewById(R.id.txtNameE);
        dateTxt = (EditText) findViewById(R.id.txtDateE);
        neckTxt = (EditText) findViewById(R.id.txtNeckE);
        bustTxt = (EditText) findViewById(R.id.txtBustE);
        chestTxt = (EditText) findViewById(R.id.txtChestE);
        waistTxt = (EditText) findViewById(R.id.txtWaistE);
        hipTxt = (EditText) findViewById(R.id.txtHipE);
        inseamTxt = (EditText) findViewById(R.id.txtInseamE);
        commentTxt = (EditText) findViewById(R.id.txtCommentE);

        saveData = (Button) findViewById(R.id.btnAdd);
        oldName = contact.getName();
        oldDate = contact.getDate();
        oldNeck = contact.getNeck();
        oldBust = contact.getBust();
        oldChest = contact.getChest();
        oldWaist = contact.getWaist();
        oldHip = contact.getHip();
        oldInseam = contact.getInseam();
        oldComment = contact.getComment();


        nameTxt.setText(oldName);
        dateTxt.setText(oldDate);
        neckTxt.setText(oldNeck);
        bustTxt.setText(oldBust);
        chestTxt.setText(oldChest);
        waistTxt.setText(oldWaist);
        hipTxt.setText(oldHip);
        inseamTxt.setText(oldInseam);
        commentTxt.setText(oldComment);

        saveData.setOnClickListener(saveButtonListener);


    }

    public View.OnClickListener saveButtonListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            String name =nameTxt.getText().toString();
            String date =dateTxt.getText().toString();
            String neck = neckTxt.getText().toString();
            String bust =bustTxt.getText().toString();
            String chest = chestTxt.getText().toString();
            String waist = waistTxt.getText().toString();
            String hip = hipTxt.getText().toString();
            String inseam = inseamTxt.getText().toString();
            String comment = commentTxt.getText().toString();

            Contact contact = new Contact(name,date,neck,bust,chest,waist,hip,inseam,comment);

            if(!validateJavaDate(date)){
                dateTxt.setError("Date format invalid");
            }
            else{
                contact.set_name(name);
                contact.set_date(date);
                contact.set_neck(neck);
                contact.set_bust(bust);
                contact.set_chest(chest);
                contact.set_waist(waist);
                contact.set_hip(hip);
                contact.set_inseam(inseam);
                contact.set_comment(comment);


                Intent intent = new Intent();
                Bundle bundle = getIntent().getExtras();
                int position = bundle.getInt("position");
                intent.putExtra("position",position);
                intent.putExtra("result",contact);
                setResult(MainActivity.RESULT_OK,intent);
                Toast.makeText(getApplicationContext(),"Data updated",Toast.LENGTH_SHORT).show();
                finish();


            }

        }
    };



}

