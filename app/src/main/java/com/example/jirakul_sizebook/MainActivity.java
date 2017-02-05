package com.example.jirakul_sizebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int EDIT = 0, DELETE = 1;

    //create new file
    private static final String FILENAME = "file1.sav";

    EditText nameTxt, dateTxt;
    EditText neckTxt, bustTxt,chestTxt,waistTxt,inseamTxt;
    EditText commentTxt;
    EditText hipTxt;
    List<Contact> contactsList = new ArrayList<Contact>();
    ListView contactListView;
    int longClickedItemIndex;
    ArrayAdapter<Contact> contactAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameTxt = (EditText) findViewById(R.id.txtName);
        dateTxt = (EditText) findViewById(R.id.txtDate);
        bustTxt = (EditText) findViewById(R.id.txtBust);
        chestTxt = (EditText) findViewById(R.id.txtChest);
        waistTxt = (EditText) findViewById(R.id.txtWaist);
        hipTxt = (EditText) findViewById(R.id.txtHip);
        inseamTxt = (EditText) findViewById(R.id.txtInseam);
        neckTxt = (EditText) findViewById(R.id.txtNeck);
        commentTxt = (EditText) findViewById(R.id.txtComment);

        //assignListView
        contactListView = (ListView) findViewById(R.id.listView);

        registerForContextMenu(contactListView);
        contactListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClickedItemIndex = position;

                return false;
            }
        });
        // Adding Tab
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tabContactList);
        tabSpec.setIndicator("List");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tabCreator);
        tabSpec.setIndicator("Add");
        tabHost.addTab(tabSpec);



        final Button addBtn = (Button) findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String name = nameTxt.getText().toString();
                String date = dateTxt.getText().toString();
                String neck = neckTxt.getText().toString();
                String bust = bustTxt.getText().toString();
                String chest = chestTxt.getText().toString();
                String waist = waistTxt.getText().toString();
                String hip = hipTxt.getText().toString();
                String inseam = inseamTxt.getText().toString();
                String comment = commentTxt.getText().toString();
                String error = "Enter inches in one decimal place";

                if(!isOneDecimal(bust)){
                    bustTxt.setError(error);
                }
                else if(!isOneDecimal(neck)){
                    neckTxt.setError(error);
                }
                else if(!isOneDecimal(chest)){
                    chestTxt.setError(error);
                }
                else if(!isOneDecimal(waist)){
                    waistTxt.setError(error);
                }
                else if(!isOneDecimal(hip)){
                    hipTxt.setError(error);
                }
                else if(!isOneDecimal(inseam)){
                    inseamTxt.setError(error);
                }
                else if(!validateJavaDate(date)){
                    dateTxt.setError("Date format invalid");
                }
                else {
                    if(bust.matches("\\d+$")){
                        bust = bust +".0";
                    }
                    if(neck.matches("\\d+$")){
                        neck = neck +".0";
                    }
                    if(chest.matches("\\d+$")){
                        chest = chest +".0";
                    }
                    if(waist.matches("\\d+$")){
                        waist = waist +".0";
                    }
                    if(hip.matches("\\d+$")){
                        hip = hip +".0";
                    }
                    if(inseam.matches("\\d+$")){
                        inseam = inseam +".0";
                    }
                    contactsList.add(new Contact(name, date, neck, bust, chest, waist, hip, inseam, comment));
                    contactAdapter.notifyDataSetChanged();
                    showTotalRecord();
                    saveInFile();
                    Toast.makeText(getApplicationContext(), nameTxt.getText().toString() + " added", Toast.LENGTH_SHORT).show();
                }

            }
        });


        nameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addBtn.setEnabled(!nameTxt.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editIntent = new Intent(MainActivity.this,DetailsActivity.class);
                editIntent.putExtra("position",position);
                editIntent.putExtra("result",(Serializable) contactListView.getItemAtPosition(position));
                startActivityForResult(editIntent,1);

            }
        });



    }

    public static boolean isOneDecimal(String string) {
        if(string.trim().equals("")){
            return true;
        }
        // Check for whole number
        //http://stackoverflow.com/questions/16331423/whats-the-java-regular-expression-for-an-only-integer-numbers-string
        else if(string.matches("\\d+$")){
            return true;
        }
        // Check for one decimal
        return string.matches("^\\d+\\.\\d{1}$");
    }

    //http://beginnersbook.com/2013/05/java-date-format-validation/
    //2017-02-04

    public static boolean validateJavaDate(String strDate)
    {
    /* Check if date is 'null' */
        if (strDate.trim().equals("")) { return true; }
    /* Date is not 'null' */
        else {
        /* parse the string into date form */
            try {
                // Set Date format
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                sdf.setLenient(false);
                sdf.parse(strDate);
            } catch (ParseException e) { return false; }
        /* Return 'true' - since date is in valid format */
            return true;
        }
    }

    public void showTotalRecord() {

        String message = "Total Records: " + contactListView.getAdapter().getCount();;
        TextView totalRecordView = (TextView) findViewById(R.id.total_record);
        totalRecordView.setText(message);
    }


    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);

        menu.setHeaderIcon(R.drawable.edit_icon);
        menu.setHeaderTitle("Record Options");
        menu.add(Menu.NONE,EDIT,menu.NONE,"Edit Record");
        menu.add(Menu.NONE,DELETE,menu.NONE,"Delete Record");
    }

    // https://www.youtube.com/watch?v=1V2DBKZhi9Q&list=WL&index=234
    // Jan 30
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case EDIT:
                // Creates a new Intent to edit a contact
                //setResult(RESULT_OK);
                Intent editIntent = new Intent(MainActivity.this,EditActivity.class);
                editIntent.putExtra("result",(Serializable) contactListView.getItemAtPosition(longClickedItemIndex));
                startActivityForResult(editIntent,1);
                saveInFile();
                break;
            case DELETE:
                contactsList.remove(longClickedItemIndex);
                contactAdapter.notifyDataSetChanged();
                showTotalRecord();
                saveInFile();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode ==1){
            if(resultCode == MainActivity.RESULT_OK){
                Contact contact = (Contact)data.getExtras().getSerializable("result");
                int position = data.getIntExtra("position",-1);
                contactsList.set(position,contact);
                contactAdapter.notifyDataSetChanged();
                saveInFile();
            }
        }
    }

    private class ContactListAdapter extends ArrayAdapter<Contact> {
        public ContactListAdapter() {
            super (MainActivity.this, R.layout.listview_item, contactsList);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            Contact currentContact = contactsList.get(position);

            TextView name = (TextView) view.findViewById(R.id.contactName);
            name.setText(currentContact.getName());
            TextView bust = (TextView) view.findViewById(R.id.lv_bust);
            if(currentContact.getBust().equals("")){
                bust.setText("");
            }
            else{
                bust.setText("Bust: "+currentContact.getBust()+ "\t\t");
            }

            TextView chest = (TextView) view.findViewById(R.id.lv_chest);
            if(currentContact.getChest().equals("")){
                chest.setText("");
            }
            else{
                chest.setText("Chest: "+ currentContact.getChest()+ "\t\t");
            }

            TextView waist = (TextView) view.findViewById(R.id.lv_waist);
            if(currentContact.getWaist().equals("")){
                waist.setText("");
            }
            else{
                waist.setText("Waist: " + currentContact.getWaist()+ "\t\t");
            }

            TextView inseam = (TextView) view.findViewById(R.id.lv_inseam);
            if(currentContact.getInseam().equals("")){
                inseam.setText("");
            }
            else{
                inseam.setText("Inseam: " + currentContact.getInseam());
            }

            return view;
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        contactAdapter = new ContactListAdapter();
        contactListView.setAdapter(contactAdapter);
        showTotalRecord();
    }


    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            contactsList = gson.fromJson(in, new TypeToken<ArrayList<Contact>>(){}.getType());
            fis.close();
        }catch(FileNotFoundException e){
            contactsList = new ArrayList<Contact>();
        }catch(IOException e){
            throw new RuntimeException();
        }
    }

    private void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(contactsList,out);
            out.flush();
            fos.close();
        }catch(FileNotFoundException e){
            throw new RuntimeException();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

}
