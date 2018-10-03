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

    ArrayList<Record> records = new ArrayList<Record>();
    private static Controller controller;
    private ListView historyListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListView = (ListView)findViewById(R.id.history_list_id);
        controller = new Controller();
        historyListView.setOnItemClickListener(this);

    }

    @Override
    protected void onStart(){
        super.onStart();
        records = controller.loadFromFile(History.this, "records");
        ArrayAdapter<Record> adapter = new ArrayAdapter<Record>(this, R.layout.item_list,records);
        historyListView.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id){
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent(History.this,Item.class);
        intent.putExtra("position", position);
        intent.putExtra("id", id);
        startActivity(intent);

    }
}
