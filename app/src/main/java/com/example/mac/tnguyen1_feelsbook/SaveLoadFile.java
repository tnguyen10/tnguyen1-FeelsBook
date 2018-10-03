package com.example.mac.tnguyen1_feelsbook;

import android.content.Context;

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

public class SaveLoadFile implements Serializable{

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


    // lab session check readme
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
