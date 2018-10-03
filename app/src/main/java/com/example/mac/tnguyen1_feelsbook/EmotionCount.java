package com.example.mac.tnguyen1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EmotionCount extends AppCompatActivity {

    private ListView emotionCountListView;
    private static Controller controller;
    ArrayList<Record> records = new ArrayList<Record>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_count);

        emotionCountListView = (ListView)findViewById(R.id.emotion_list_id);
        controller = new Controller();


    }

    @Override
    protected void onStart(){
        super.onStart();
        records = controller.loadFromFile(EmotionCount.this, "emotions");
        ArrayAdapter<Record> adapter = new ArrayAdapter<Record>(this, R.layout.item_list,records);
        emotionCountListView.setAdapter(adapter);
    }
}
