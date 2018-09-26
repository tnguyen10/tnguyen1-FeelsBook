package com.example.mac.tnguyen1_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller implements Serializable {

    List<Record> records = new ArrayList<Record>();
    Map<String,Integer> emotionCount= new HashMap<String,Integer>();
    private Date userDate;
    private String userEmotion;
    private String userDetail;
    private static final String FILENAME = "file.sav";


    public boolean updateUserInputs(TextView dateView, EditText emotionView, EditText detailView){
        // Update records list
        userDate = new Date(System.currentTimeMillis());
        userEmotion = emotionView.getText().toString();
        userDetail = detailView.getText().toString();

        // Invalid inputs
        if (detailView.getText().length() == 0){
            return false;
        }
        if (detailView.getText().length() > 100){
            return false;
        }

        // Update records
        Record userRecord = new Record(userDate,userEmotion,userDetail);
        records.add(userRecord);

        // Update emotionCount
        if (emotionCount.get(userEmotion) == null){
            emotionCount.put(userEmotion,0);
        }else{
            emotionCount.put(userEmotion,emotionCount.get(userEmotion) + 1);
        }
        return true;
    }

    public List<Record> getRecords(){return this.records;}

    public void saveToFile(List<Record> records, Context context){
        try{
            FileOutputStream fos = context.getApplicationContext().openFileOutput(FILENAME,Context.MODE_APPEND);
            for (final Record record:records){
                fos.write(new String((record.getDate()).toString()+ " | ") .getBytes());
                fos.write(new String(record.getEmotion()+ " | ").getBytes());
                fos.write(new String(record.getDetail()).getBytes());
                fos.write("\n".getBytes());
            }
            fos.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Record> loadFromFile()
}
