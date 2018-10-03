package com.example.mac.tnguyen1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Item extends AppCompatActivity {

    ArrayList<Record> records = new ArrayList<Record>();
    private static Controller controller;
    int position;
    EditText date_edit_id_view;
    EditText emotion_edit_id_view;
    EditText detail_edit_id_view;
    Button button_edit_id_view;
    Button delete_button_edit_id_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        controller = new Controller();

        date_edit_id_view = (EditText) findViewById(R.id.date_edit_id);
        emotion_edit_id_view = (EditText) findViewById(R.id.emotion_edit_id);
        detail_edit_id_view = (EditText) findViewById(R.id.detail_edit_id);
        button_edit_id_view = findViewById(R.id.button_edit_id);
        delete_button_edit_id_view = findViewById(R.id.delete_button_edit_id);


    }

    @Override
    protected void onStart(){
        super.onStart();
        records = controller.loadFromFile(Item.this, "records");
        Record selRecord = records.get(position);

        date_edit_id_view.setText(selRecord.getDate().toString());
        emotion_edit_id_view.setText(selRecord.getEmotion());
        detail_edit_id_view.setText(selRecord.getDetail());

    }

    public void saveButton(View view) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
        Date newDate = formatter.parse(date_edit_id_view.getText().toString());
        String newEmotion = emotion_edit_id_view.getText().toString();
        String newDetail = detail_edit_id_view.getText().toString();
        Record newRecord = new Record(newDate,newEmotion,newDetail);
        records.set(position,newRecord);
        controller.saveToFile(records, Item.this);
        Toast.makeText(Item.this,"Your infos have been updated",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Item.this,History.class);
        startActivity(intent);
    }

    public void deleteButton(View view){
        if (records.size() != 0){
            records.remove(position);
            controller.saveToFile(records, Item.this);
            Toast.makeText(Item.this,"Your infos have been updated",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Item.this,History.class);
            startActivity(intent);
        }else{
            Toast.makeText(Item.this,"There's nothing to delete",Toast.LENGTH_LONG).show();
        }

    }
}
