package com.example.jirakul_sizebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.example.jirakul_sizebook.R.styleable.Toolbar;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private EditText editText;
    private ListView nameListView;
    private ArrayList<Contact> listItems;
    private ArrayAdapter<Contact> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.editText);
        nameListView = (ListView) findViewById(R.id.listView);
        listItems = new ArrayList<Contact>();
        adapter = new ArrayAdapter<Contact>(this,android.R.layout.simple_expandable_list_item_1,listItems);
        nameListView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*listItems.add(editText.getText().toString());
                adapter.notifyDataSetChanged();*/
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);

            }
        });

/*
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String value = extras.getString("result");
            listItems.add(value);
            adapter.notifyDataSetChanged();
        }*/


        nameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent appInfo = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(appInfo);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Intent i = new Intent(this, DetailsActivity.class);
        startActivityForResult(i,1);

        if (requestCode ==1){
            if(resultCode == Activity.RESULT_OK){
                final String result = data.getStringExtra("result");

            }
        }
    }

/*
    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        //adapter = new ArrayAdapter<Contact>(this,R.layout.list_item,listItems);
        //nameListView.setAdapter(adapter);
    }*/

    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Contact>>() {
            }.getType();
            listItems = gson.fromJson(in, listType);
        }catch(FileNotFoundException e){
            listItems = new ArrayList<Contact>();
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
            gson.toJson(listItems,out);
            out.flush();
            fos.close();
        }catch(FileNotFoundException e){
            throw new RuntimeException();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
