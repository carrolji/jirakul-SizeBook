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
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
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

    private static final int EDIT = 0, DELETE = 1;

    private static final String FILENAME = "file.sav";

    private EditText nameText;
    private EditText dateText;
    private EditText neckText;
    private EditText bustText;

    private ListView nameListView;
    //List<Contact> nameArrayList = new ArrayList<Contact>();
    private ArrayList<Contact> nameArrayList;
    private ArrayAdapter<Contact> adapter;
    int longClickedItemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameText = (EditText) findViewById(R.id.nameTxt);
        dateText = (EditText) findViewById(R.id.dateTxt);
        neckText = (EditText) findViewById(R.id.neckTxt);
        bustText = (EditText) findViewById(R.id.bustTxt);
        nameListView = (ListView) findViewById(R.id.listView);
        nameArrayList = new ArrayList<Contact>();

        registerForContextMenu(nameListView);
        nameListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClickedItemIndex = position;

                return false;
            }
        });

        // Adding Tab
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tabCreator);
        tabSpec.setIndicator("Add");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tabContactList);
        tabSpec.setIndicator("List");
        tabHost.addTab(tabSpec);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                nameArrayList.add(new Contact(nameText.getText().toString(),dateText.getText().toString(),
                        neckText.getText().toString(),bustText.getText().toString()));
                saveInFile();
                Toast.makeText(getApplicationContext(),nameText.getText().toString() +" added",Toast.LENGTH_SHORT).show();

            }
        });

        nameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);

        menu.setHeaderIcon(R.drawable.edit_icon);
        menu.setHeaderTitle("Options");
        menu.add(Menu.NONE,EDIT,menu.NONE,"Edit Details");
        menu.add(Menu.NONE,DELETE,menu.NONE,"Delete Details");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode ==1){
            if(resultCode == Activity.RESULT_OK){
                Contact contact = (Contact)data.getExtras().getSerializable("result");
                int position = data.getIntExtra("position",-1);
                nameArrayList.set(position,contact);
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        }
    }

    private class nameListAdapter extends ArrayAdapter<Contact> {
        public nameListAdapter() {
            super (MainActivity.this, R.layout.listview_item, nameArrayList);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            Contact currentName = nameArrayList.get(position);

            TextView name = (TextView) view.findViewById(R.id.contactName);
            name.setText(currentName.get_name());
            TextView date = (TextView) view.findViewById(R.id.dateDisplay);
            date.setText(currentName.get_date());
            TextView neck = (TextView) view.findViewById(R.id.lv_neck);
            neck.setText(currentName.get_neck());
            TextView bust = (TextView) view.findViewById(R.id.lv_bust);
            bust.setText(currentName.get_bust());

            return view;
        }
    }


    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new nameListAdapter();
        nameListView.setAdapter(adapter);
    }

    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            nameArrayList = gson.fromJson(in, new TypeToken<ArrayList<Contact>>(){}.getType());
            fis.close();
        }catch(FileNotFoundException e){
            nameArrayList = new ArrayList<Contact>();
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
            gson.toJson(nameArrayList,out);
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
