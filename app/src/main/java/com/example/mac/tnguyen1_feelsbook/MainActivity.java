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
    public static final String EXTRA_MESSAGE = "com.example.android.tnguyen-feelbook.extra.MESSAGE";
    public String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Get view and initialize controller
        dateView = findViewById(R.id.date_id);
        emotionView = findViewById(R.id.emotion_id);
        detailView = findViewById(R.id.detail_id);
        controller = new Controller();

        // Update controller record list
        controller.setRecords(controller.loadFromFile(MainActivity.this));

        // Button clicked
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Update user inputs and save to file
                boolean updateSucceed = controller.updateUserInputs(dateView,emotionView,detailView);
                if (updateSucceed == true){
                    Toast.makeText(MainActivity.this,"Your infos have been added",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Invalid Input. Try Again",Toast.LENGTH_LONG).show();
                }
                controller.saveToFile(controller.getRecords(), MainActivity.this);

                // Restart back to main activity
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
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
