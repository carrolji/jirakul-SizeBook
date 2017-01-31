package com.example.jirakul_sizebook;

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

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.example.jirakul_sizebook.R.styleable.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ListView nameListView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        TextView name = (TextView) findViewById(R.id.name2);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nameIntent = new Intent(MainActivity.this, DetailsActivity.class);

                startActivity(nameIntent);
            }
        });*/

        String[] names = {"James","John","Bill","Same","Smith","Paul"};

       /* ListAdapter nameAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,names);*/

   /*     nameListView.setAdapter(nameAdapter);

        nameListView.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(appInfo);
            }
        }); */
        editText = (EditText) findViewById(R.id.editText);
        nameListView = (ListView) findViewById(R.id.listView);
        listItems = new ArrayList<String>();
        listItems.add("First Item - added on Activity Create");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,listItems);
        nameListView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                listItems.add(editText.getText().toString());
                adapter.notifyDataSetChanged();

            }
        });
        nameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent appInfo = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(appInfo);
            }
        });



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
