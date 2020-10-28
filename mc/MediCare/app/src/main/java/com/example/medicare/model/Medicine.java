package com.example.medicare.model;

import androidx.annotation.NonNull;

public class Medicine {
    private String Date,Name,Frequency,Dosage,Meal,Days,EndDate;

    public Medicine() {
    }

    public Medicine(String date, String name, String frequency, String dosage, String meal, String days, String endDate) {
        Date = date;
        Name = name;
        Frequency = frequency;
        Dosage = dosage;
        Meal = meal;
        Days = days;
        EndDate = endDate;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String frequency) {
        Frequency = frequency;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String dosage) {
        Dosage = dosage;
    }

    public String getMeal() {
        return Meal;
    }

    public void setMeal(String meal) {
        Meal = meal;
    }

    public String getDays() {
        return Days;
    }

    public void setDays(String days) {
        Days = days;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    @NonNull
    @Override
    public String toString() {
        return Name;
    }
}
