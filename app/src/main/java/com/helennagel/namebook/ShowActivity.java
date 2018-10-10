package com.helennagel.namebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ShowActivity extends AppCompatActivity {

    private People people;
    private ListView view;
    public ArrayAdapter<Person> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        /* The method onCreate() determines the parameter transferred from MainActivity, where it is important to use the same key and the correct type of cast. */
        Intent intent = getIntent();
        people = (People)intent.getSerializableExtra("persons");
        view = findViewById(R.id.lstNames);
        reset();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        registerForContextMenu(view);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    // Context menu using MenuInflater, that'll use the menu created in xml
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        new MenuInflater(this).inflate(R.menu.context_menu,menu);
    }

    // Added event handlers for context menu items
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Person person = people.getPerson(menuInfo.position);
        switch (item.getItemId()){
            // By blicking on Details menu a new activity will be opened, using intent.putExtra will send data to other activity
            case R.id.details_menu:
                Intent intent = new Intent(ShowActivity.this, DetailsActivity.class);
                intent.putExtra("person", person);
                startActivity(intent);
                return true;
            // Will remove data
            case R.id.remove_menu:
                try{
                    people.remove( this,person);
                    Toast.makeText(this, "Data removed", Toast.LENGTH_SHORT).show();
                }
                catch(Exception ex){
                    Toast.makeText(this, "Error while removing data", Toast.LENGTH_SHORT).show();
                }

        }
        return super.onContextItemSelected(item);
    }
    private void reset(){
        view.setAdapter(adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,people.getPeople()));
    }
}
