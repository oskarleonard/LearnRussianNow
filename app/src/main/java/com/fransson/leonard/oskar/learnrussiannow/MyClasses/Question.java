package com.fransson.leonard.oskar.learnrussiannow.MyClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oskar on 2015-06-28.
 */
public class Question implements Parcelable{

    private String question;
    private double statisticsCorrectness;
    private List<Answer> answers = new ArrayList<>();

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public void increaseCorrectness() {
        statisticsCorrectness = statisticsCorrectness + 1.0;
    }
    public void decreaseCorrectness() {
        statisticsCorrectness = statisticsCorrectness - 0.5;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getQuestion() {
        return question;
    }

    public double getStatisticsCorrectness() {
        return statisticsCorrectness;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.question);
        dest.writeDouble(this.statisticsCorrectness);
        dest.writeList(this.answers);
    }

    public static final Creator CREATOR = new Parcelable.Creator<Question>(){
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };


    // A constructor for the Parcelable
    private Question (Parcel in){
        this.question = in.readString();
        this.statisticsCorrectness = in.readDouble();
        in.readList(answers, Answer.class.getClassLoader());
    }

}
