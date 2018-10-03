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

    ArrayList<Record> records = new ArrayList<Record>();
    Map<String,Integer> emotionCount= new HashMap<String,Integer>();
    private Date userDate;
    private String userEmotion;
    private String userDetail;


    public boolean updateUserInputs(TextView dateView, EditText emotionView, EditText detailView){
        // Update records list
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

        // Update records
        Record userRecord = new Record(userDate,userEmotion,userDetail);
        records.add(userRecord);

        // Update emotionCount
        for (final Record record:records){
            String emotionForCounting = record.getEmotion();
            if (emotionCount.get(emotionForCounting) == null){
                emotionCount.put(userEmotion,1);
            }else{
                emotionCount.put(emotionForCounting,emotionCount.get(emotionForCounting) + 1);
            }
        }

        return true;
    }

    public ArrayList<Record> getRecords(){return this.records;}
    public void setRecords(ArrayList<Record> records){this.records = records; }

    public class DateCompare implements Comparator<Record> {
        @Override
        public int compare(Record r1, Record r2) {
            return r1.getDate().compareTo(r2.getDate());
        }
    }

    public void orderRecords(ArrayList<Record> records){


        Collections.sort(records, new DateCompare() );

    }

    public void saveToFile(ArrayList<Record> records, Context context){

        orderRecords(records);

        String RECORDFILE = "records.sav";
        String EMOTIONFILE = "emotions.sav";

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

    public ArrayList<Record> loadFromFile(Context context, String state){

        String FILENAME;

        if (state == "emotions") {
            FILENAME = "emotions.sav";
        }else{
            FILENAME = "records.sav";
        }

        ArrayList<Record> fileList = new ArrayList<Record>();
        try{
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();
            Type listTweetType = new TypeToken<ArrayList<Record>>(){}.getType();
            fileList = gson.fromJson(reader, listTweetType);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return(fileList);
    }
}
