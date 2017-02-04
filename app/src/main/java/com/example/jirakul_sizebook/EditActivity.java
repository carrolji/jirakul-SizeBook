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

import com.example.jirakul_sizebook.Contact;
import com.example.jirakul_sizebook.MainActivity;
import com.example.jirakul_sizebook.R;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    List<Contact> contactsList = new ArrayList<Contact>();
    private EditText nameTxt;
    private EditText dateTxt;
    private EditText neckTxt;
    private EditText bustTxt;
    private Button saveData;
    protected String oldName;
    protected String oldDate;
    protected String oldNeck;
    protected String oldBust;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Contact contact = (Contact)getIntent().getSerializableExtra("result");

        nameTxt = (EditText) findViewById(R.id.txtNameE);
        dateTxt = (EditText) findViewById(R.id.txtDateE);
        neckTxt = (EditText) findViewById(R.id.txtNeckE);
        bustTxt = (EditText) findViewById(R.id.txtBustE);

        saveData = (Button) findViewById(R.id.btnAdd);
        oldName = contact.get_name();
        oldDate = contact.get_date();
        oldNeck = contact.get_neck();
        oldBust = contact.get_bust();

        nameTxt.setText(oldName);
        dateTxt.setText(oldDate);
        neckTxt.setText(oldNeck);
        bustTxt.setText(oldBust);

        saveData.setOnClickListener(saveButtonListener);


    }

    public View.OnClickListener saveButtonListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            String name =nameTxt.getText().toString();
            String date =dateTxt.getText().toString();
            String neck = neckTxt.getText().toString();
            String bust =bustTxt.getText().toString();

            Contact contact = new Contact(name,date,neck,bust);
            contact.set_name(name);
            contact.set_date(date);
            contact.set_neck(neck);
            contact.set_bust(bust);

            Intent intent = new Intent();
            Bundle bundle = getIntent().getExtras();
            int position = bundle.getInt("position");
            intent.putExtra("position",position);
            intent.putExtra("result",contact);
            setResult(MainActivity.RESULT_OK,intent);
            Toast.makeText(getApplicationContext(),"Data updated",Toast.LENGTH_SHORT).show();
            finish();

        }
    };



}


