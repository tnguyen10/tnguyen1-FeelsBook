package com.example.mac.tnguyen1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmotionCount extends AppCompatActivity {

    private ListView emotionCountListView;
    private static Controller controller;
    ArrayList<Record> records = new ArrayList<Record>();
    String[] emotionCountString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_count);

        // Get list view and init controller
        emotionCountListView = (ListView)findViewById(R.id.emotion_list_id);
        controller = new Controller();
    }

    @Override
    protected void onStart(){
        super.onStart();

        // Load from file records list, Count emotion and output to list view
        records = controller.loadFromFile(EmotionCount.this);
        emotionCountString = controller.countEmotions(records);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_list, emotionCountString);
        emotionCountListView.setAdapter(adapter);
    }
}
