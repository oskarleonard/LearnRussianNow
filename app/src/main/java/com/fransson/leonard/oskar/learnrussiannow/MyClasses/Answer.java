package com.fransson.leonard.oskar.learnrussiannow.MyClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Oskar on 2015-06-28.
 */
public class Answer implements Parcelable{
    private String answer;
    private boolean correct;

    public Answer(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.answer);
        dest.writeByte((byte) (this.correct ? 1 : 0));
    }

    public static final Creator CREATOR = new Parcelable.Creator<Answer>(){
        @Override
        public Answer createFromParcel(Parcel source) {
            return new Answer(source);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    // A constructor for the Parcelable
    private Answer (Parcel in){
        this.answer = in.readString();
        this.correct = in.readByte() != 0;
    }
}
