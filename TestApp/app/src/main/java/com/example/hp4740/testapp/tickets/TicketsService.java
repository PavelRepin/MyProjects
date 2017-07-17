package com.example.hp4740.testapp.tickets;

import android.support.annotation.Nullable;

import com.example.hp4740.testapp.api.TicketEntity;
import com.example.hp4740.testapp.api.TicketsApi;
import com.example.hp4740.testapp.domain.Ticket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TicketsService {
    private static final String TAG = TicketsService.class.getSimpleName();

    private static final String URL = "http://android-dev-tests.ru";

    private static final String DATE_FORMAT = "yyyyMMdd";

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    private final TicketsApi ticketsApi;

    public TicketsService(Retrofit retrofit) {
        ticketsApi = retrofit.create(TicketsApi.class);
    }

    @Nullable
    private static Exception validateDate(final Date date, final List<TicketEntity> ticketEntities) {
        String requestDate = Ticket.SIMPLE_DATE_FORMAT.format(date);

        for (final TicketEntity ticketEntity : ticketEntities) {
            String responseDate = ticketEntity.getDepDate();
            if (!requestDate.equals(responseDate)) {
                String errorMsg = String.format("Server responded with a wrong date: requested = %s, responded = %s",
                                                requestDate, responseDate);
                return new IllegalStateException(errorMsg);
            }
        }
        return null;
    }


    public Cancellable get(String cityFrom,
                           String cityTo,
                           Date dateFrom,
                           final TicketsCallback callback) {
        String dateStr = simpleDateFormat.format(dateFrom);
        String searchKeyPath = cityFrom + cityTo + dateStr;

        Call<List<TicketEntity>> call = ticketsApi.getTickets(searchKeyPath);

        call.enqueue(new Callback<List<TicketEntity>>() {
            @Override
            public void onResponse(Call<List<TicketEntity>> call, Response<List<TicketEntity>> response) {
                if (response.isSuccessful()) {
                    List<TicketEntity> ticketEntities = response.body();
                    Exception exception = validateDate(dateFrom, ticketEntities);
                    if (exception == null) {
                        callback.handleTickets(ticketEntities);
                    } else {
                        callback.handleError(exception);
                    }
                } else {
//                    callback.handleError(response.errorBody());
                    callback.handleError(new RuntimeException("Unknown Error"));
                }

            }

            @Override
            public void onFailure(Call<List<TicketEntity>> call, Throwable t) {
                callback.handleError(t);
            }
        });

        return call::cancel;
    }
}
