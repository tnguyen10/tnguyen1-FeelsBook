package com.example.mac.tnguyen1_feelsbook;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Record {
    private Date date;
    private String emotion;
    private String detail;

    // Constructor
    public Record(Date date, String emotion, String detail) {
        this.date = date;
        this.emotion = emotion;
        this.detail = detail;
    }

    // Getter functions
    public Date getDate() {
        return this.date;
    }
    public String getEmotion() {
        return this.emotion;
    }
    public String getDetail() {
        return this.detail;
    }


    // Setter functions
    public void setDate(Date date) { this.date = date; }
    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    // For printing
    public String toString() {
        // stackoverflow check readme
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.UK);
        String date = sdf.format(this.date);
        return date + " " + this.emotion + " || " + this.detail;
    }
}
