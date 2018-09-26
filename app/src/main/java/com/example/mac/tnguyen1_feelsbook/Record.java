package com.example.mac.tnguyen1_feelsbook;

import java.util.Date;

public class Record {
    private Date date;
    private String emotion;
    private String detail;

    public Record(Date date, String emotion, String detail){
        this.date = date;
        this.emotion = emotion;
        this.detail = detail;
    }
    public Date getDate(){
        return this.date;
    }
    public String getEmotion() { return this.emotion; }
    public String getDetail(){
        return this.detail;
    }

    public void setDate(Date date){
        this.date = date;
    }
    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }
    public void setDetail(String detail){
        this.detail = detail;
    }

}
