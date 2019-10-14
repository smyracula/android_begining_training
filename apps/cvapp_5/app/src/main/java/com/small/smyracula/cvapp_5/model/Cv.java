package com.small.smyracula.cvapp_5.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class Cv implements Parcelable {
    @SerializedName("name")
    @Nullable
    private String name;

    @SerializedName("surname")
    @Nullable
    private String surname;

    @SerializedName("born")
    private int born;

    @SerializedName("from")
    @Nullable
    private String from;

    @SerializedName("university")
    @Nullable
    private String university;

    @SerializedName("university_graduated")
    private int university_graduated;

    @SerializedName("highSchool")
    @Nullable
    private String highSchool;

    @SerializedName("highSchool_graduated")
    private int highSchool_graduated;

    @SerializedName("corporate")
    @Nullable
    private String corporate;

    protected Cv(Parcel in) {
        name = in.readString();
        surname = in.readString();
        born = in.readInt();
        from = in.readString();
        university = in.readString();
        university_graduated = in.readInt();
        highSchool = in.readString();
        highSchool_graduated = in.readInt();
        corporate = in.readString();
    }

    public static final Creator<Cv> CREATOR = new Creator<Cv>() {
        @Override
        public Cv createFromParcel(Parcel in) {
            return new Cv(in);
        }

        @Override
        public Cv[] newArray(int size) {
            return new Cv[size];
        }
    };

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getSurname() {
        return surname;
    }

    public void setSurname(@Nullable String surname) {
        this.surname = surname;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    @Nullable
    public String getFrom() {
        return from;
    }

    public void setFrom(@Nullable String from) {
        this.from = from;
    }

    @Nullable
    public String getUniversity() {
        return university;
    }

    public void setUniversity(@Nullable String university) {
        this.university = university;
    }

    public int getUniversity_graduated() {
        return university_graduated;
    }

    public void setUniversity_graduated(int university_graduated) {
        this.university_graduated = university_graduated;
    }

    @Nullable
    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(@Nullable String highSchool) {
        this.highSchool = highSchool;
    }

    public int getHighSchool_graduated() {
        return highSchool_graduated;
    }

    public void setHighSchool_graduated(int highSchool_graduated) {
        this.highSchool_graduated = highSchool_graduated;
    }

    @Nullable
    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(@Nullable String corporate) {
        this.corporate = corporate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.surname);
        parcel.writeInt(this.born);
        parcel.writeString(this.from);
        parcel.writeString(this.university);
        parcel.writeInt(this.university_graduated);
        parcel.writeString(this.highSchool);
        parcel.writeInt(this.highSchool_graduated);
        parcel.writeString(this.corporate);

    }
}
