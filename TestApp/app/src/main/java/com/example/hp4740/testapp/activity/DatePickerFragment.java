package com.example.hp4740.testapp.activity;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    @Nullable
    private DateHolder date;
    @Nullable
    private DateHolder dateMin;

    private DatePickerDialog.OnDateSetListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final DateHolder minDate = dateMin != null ? dateMin
                                                   : DateHolder.now();

        return prepareDatePicker(listener,
                                 date != null ? date
                                              : minDate,
                                 minDate);
    }

    @NonNull
    private Dialog prepareDatePicker(final DatePickerDialog.OnDateSetListener listener,
                                     @NonNull final DateHolder dateHolder,
                                     @NonNull final DateHolder minDate) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                                                                 listener,
                                                                 dateHolder.getYear(),
                                                                 dateHolder.getMonth(),
                                                                 dateHolder.getDay());

        datePickerDialog.getDatePicker()
                        .setMinDate(getMinDateInMs(minDate));

        return datePickerDialog;
    }

    private static long getMinDateInMs(@NonNull final DateHolder date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonth(), date.getDay());

        return calendar.getTimeInMillis() - 1000;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

//    TODO pass via Fragment#setArguments
    public void setDate(@Nullable DateHolder date) {
        this.date = date;
    }

//    TODO pass via Fragment#setArguments
    public void setDateMin(@Nullable final DateHolder dateMin) {
        this.dateMin = dateMin;
    }
}
