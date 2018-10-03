package com.example.mac.tnguyen1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView historyListView;
    private static Controller controller;
    private static SaveLoadFile saveloadfile;
    ArrayList<Record> records = new ArrayList<Record>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Get list view, init controller and its listener and init saveloadfile
        historyListView = (ListView)findViewById(R.id.history_list_id);
        controller = new Controller();
        saveloadfile = new SaveLoadFile();
        historyListView.setOnItemClickListener(this);

    }

    @Override
    protected void onStart(){
        super.onStart();

        // Get records from file and output
        records = saveloadfile.loadFromFile(History.this);
        ArrayAdapter<Record> adapter = new ArrayAdapter<Record>(this, R.layout.item_list,records);
        historyListView.setAdapter(adapter);
    }

    // stackoverflow check readme
    public void onItemClick(AdapterView<?> l, View v, int position, long id){

        // Get position of clicked item and pass it on to Item Activity for later processing
        Intent intent = new Intent(History.this,Item.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
