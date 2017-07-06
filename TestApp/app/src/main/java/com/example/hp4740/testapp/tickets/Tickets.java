package com.example.hp4740.testapp.tickets;

import android.util.Log;

import com.example.hp4740.testapp.api.API;
import com.example.hp4740.testapp.api.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tickets {
    static private String TAG="Tickets";

    public static void get(String cityFrom, String cityTo, String dateFrom, final TicketsCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://android-dev-tests.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API service = retrofit.create(API.class);

        service.tickets(cityFrom+cityTo+dateFrom).enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, retrofit2.Response<List<Ticket>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                callback.handleTickets(response.body());
            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {
                Log.e(TAG, "get tickets onFailure");
            }
        });
    }
}
