package com.fransson.leonard.oskar.learnrussiannow.MyClasses;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Oskar on 2015-06-15.
 */
public class Lession implements Parcelable{
    private String title;
    private String htmlPath;

    private List<Question> classQuestions = new ArrayList<>();

    private boolean completed = false;

    public Lession(String title) {
        this.title = title;
    }

    public Lession(String title, List<Question> classQuestions, String htmlPath) {
        this.title = title;
        this.classQuestions = classQuestions;
        this.htmlPath = htmlPath;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public String getHtmlPath() {
        return htmlPath;
    }
    public List<Question> getClassQuestions() {
        return classQuestions;
    }


    public void setClassQuestions(List<Question> classQuestions) {
        this.classQuestions = classQuestions;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.htmlPath);
        dest.writeByte((byte) (completed ? 1 : 0));
        dest.writeList(this.classQuestions);
    }

    public static final Creator CREATOR = new Parcelable.Creator<Lession>(){
        @Override
        public Lession createFromParcel(Parcel source) {
            return new Lession(source);
        }

        @Override
        public Lession[] newArray(int size) {
            return new Lession[size];
        }
    };


    // A constructor for the Parcelable
    private Lession (Parcel in){
        this.title = in.readString();
        this.htmlPath = in.readString();
        this.completed = in.readByte() != 0;

        in.readList(classQuestions, Question.class.getClassLoader());
    }
}
