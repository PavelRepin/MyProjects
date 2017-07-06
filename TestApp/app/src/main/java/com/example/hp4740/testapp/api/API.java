package com.example.hp4740.testapp.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface API {
    @Headers("Authorization: Basic dXNlcjU6c2ZhaEdkZnNka2k=")
    @GET("/sapi/v1/flight/tickets/{key}?apikey=a4481d87526330a044454619dde2abd1")
    Call<List<Ticket>> tickets(@Path("key") String key);
}