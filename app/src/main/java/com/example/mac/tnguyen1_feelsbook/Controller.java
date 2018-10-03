package com.example.mac.tnguyen1_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
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
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller implements Serializable {

    // Contains records list and emotion count dictionary
    ArrayList<Record> records = new ArrayList<Record>();
    Map<String,Integer> emotionCount= new HashMap<String,Integer>();
    private Date userDate;
    private String userEmotion;
    private String userDetail;

    // Getter and setter for records list
    public ArrayList<Record> getRecords(){return this.records;}
    public void setRecords(ArrayList<Record> records){this.records = records; }


    // Get user inputs, create a new record and update the records list
    public boolean updateUserInputs(TextView dateView, EditText emotionView, EditText detailView){
        // Get user inputs
        userDate = new Date(System.currentTimeMillis());
        userEmotion = emotionView.getText().toString();
        userDetail = detailView.getText().toString();

        // Invalid inputs
        if (emotionView.getText().length() == 0){
            return false;
        }
        if (detailView.getText().length() > 100){
            return false;
        }

        // Add to records list
        Record userRecord = new Record(userDate,userEmotion,userDetail);
        records.add(userRecord);
        return true;
    }

    // Count all emotions from records list, return list of string for output
    public String[] countEmotions(ArrayList<Record> records){
        ArrayList<String> emotionStrings = new ArrayList<String>();

        // Count all emotions in records list
        for (final Record record:records){
            String key = record.getEmotion();
            if (emotionCount.get(key) == null){
                emotionCount.put(key,1);
            }else{
                emotionCount.put(key,emotionCount.get(key) + 1);
            }
        }

        // Put this into string format to be viewed by list view adapter
        for (Map.Entry<String, Integer> entry : emotionCount.entrySet()) {
            String key = entry.getKey();
            String value = Integer.toString(entry.getValue());
            String keyValue = new String (key + " count = " + value);
            emotionStrings.add(keyValue);
        }
        return (emotionStrings.toArray(new String[emotionStrings.size()]));
    }


    // Order record list
    public class DateCompare implements Comparator<Record> {
        @Override
        public int compare(Record r1, Record r2) {
            return r1.getDate().compareTo(r2.getDate());
        }
    }
    public void orderRecords(ArrayList<Record> records){
        Collections.sort(records, new DateCompare() );
    }


    // Save to file "records.sav" records list
    public void saveToFile(ArrayList<Record> records, Context context){
        orderRecords(records);
        String RECORDFILE = "records.sav";

        try{
            FileOutputStream fos = context.openFileOutput(RECORDFILE,0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(records, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    // Load from file into records list
    public ArrayList<Record> loadFromFile(Context context){
        String RECORDFILE = "records.sav";
        ArrayList<Record> fileList = new ArrayList<Record>();

        try{
            FileInputStream fis = context.openFileInput(RECORDFILE);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();
            Type listTweetType = new TypeToken<ArrayList<Record>>(){}.getType();
            fileList = gson.fromJson(reader, listTweetType);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return(fileList);
    }
}
