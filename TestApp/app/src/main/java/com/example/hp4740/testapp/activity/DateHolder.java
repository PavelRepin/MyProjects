package com.example.hp4740.testapp.activity;


import android.support.annotation.NonNull;

import java.util.Date;

public final class DateHolder {

    private final int year;
    private final int month;
    private final int day;

    public static DateHolder of(@NonNull Date date) {
        return new DateHolder(date.getYear() + 1900, date.getMonth(), date.getDate());
    }

    public static DateHolder now() {
        return of(new Date());
    }

    public DateHolder(final int year, final int month, final int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
