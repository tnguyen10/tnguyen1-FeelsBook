package com.example.mac.tnguyen1_feelsbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView dateView;
    private EditText emotionView;
    private EditText detailView;
    private static Controller controller;
    private static SaveLoadFile saveloadfile;
    public static final String EXTRA_MESSAGE = "com.example.android.tnguyen-feelbook.extra.MESSAGE";
    public String message;
    private String emotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get view and initialize controller,saveloadfile
        dateView = findViewById(R.id.date_id);
        detailView = findViewById(R.id.detail_id);
        controller = new Controller();
        saveloadfile = new SaveLoadFile();

        // Update controller record list
        controller.setRecords(saveloadfile.loadFromFile(MainActivity.this));
    }

    // BUTTON CLICKED
    public void loveClicked(View view){
        emotion = "love";
        updateUserInput();
    }
    public void joyClicked(View view){
        emotion = "joy";
        updateUserInput();
    }
    public void surpriseClicked(View view){
        emotion = "surprise";
        updateUserInput();
    }
    public void angerClicked(View view){
        emotion = "anger";
        updateUserInput();
    }
    public void sadnessClicked(View view){
        emotion = "sadness";
        updateUserInput();
    }
    public void fearClicked(View view){
        emotion = "fear";
        updateUserInput();
    }

    public void updateUserInput(){

        // Update user inputs and save to file
        boolean updateSucceed = controller.updateUserInputs(dateView,emotion,detailView);
        if (updateSucceed == true){
            Toast.makeText(MainActivity.this,"Your infos have been added",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this,"Invalid Input. Try Again",Toast.LENGTH_LONG).show();
        }
        saveloadfile.saveToFile(controller.getRecords(), MainActivity.this);

        // Restart back to main activity
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Switch to Emotion Count or History activities
        if (id == R.id.emotion_count_id) {
            Intent intent = new Intent(MainActivity.this,EmotionCount.class);
            startActivity(intent);
        }
        else if (id == R.id.history_id){
            Intent intent = new Intent(MainActivity.this,History.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
