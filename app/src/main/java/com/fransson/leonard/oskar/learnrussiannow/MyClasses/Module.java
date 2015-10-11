package com.fransson.leonard.oskar.learnrussiannow.MyClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oskar on 2015-06-14.
 */
public class Module implements Parcelable {

    private String title;
    private List<Lession> lessions = new ArrayList<>();


    public Module(String title) {
        this.title = title;
    }

    public Module(String title, List<Lession> lessions) {
        this.title = title;
        this.lessions = lessions;
    }

    public List<Lession> getLessions() {
        return lessions;
    }

    public void updateLession(Lession lession) {

        for (int x = 0; x < lessions.size(); x++) {
            if (lessions.get(x).getTitle().equals(lession.getTitle())) {
                lessions.add(x, lession);
                lessions.remove(x+1);
                break;
            }
        }
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeList(this.lessions);
    }

    public static final Creator CREATOR = new Parcelable.Creator<Module>() {
        @Override
        public Module createFromParcel(Parcel source) {
            return new Module(source);
        }

        @Override
        public Module[] newArray(int size) {
            return new Module[size];
        }
    };


    // A constructor for the Parcelable
    private Module(Parcel in) {
        this.title = in.readString();
        in.readList(lessions, Lession.class.getClassLoader());

    }

}
