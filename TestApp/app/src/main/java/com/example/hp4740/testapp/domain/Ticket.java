package com.example.hp4740.testapp.domain;


import com.example.hp4740.testapp.api.TicketEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public final class Ticket {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    private final String code;

    private final String cityNameFrom;
    private final String cityNameTo;

    private final String airportNameFrom;
    private final String airportNameTo;

    private final Date depDate;

    private final long duration;
    private final double price;

    private final boolean isFastest;
    private final boolean isCheapestNonStop;
    private final boolean isCheapestWithStops;
    private final boolean isBest;

    public static Ticket of(TicketEntity ticketEntity,
                            boolean isFastest,
                            boolean isCheapestNonStop,
                            boolean isCheapestWithStops) {

        final Date depDate;
        try {
            depDate = SIMPLE_DATE_FORMAT.parse(ticketEntity.getDepDate());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format", e);
        }

        return new Ticket(ticketEntity.getCode(),
                          ticketEntity.getCityNameFrom(),
                          ticketEntity.getCityNameTo(),
                          ticketEntity.getAirportNameFrom(),
                          ticketEntity.getAirportNameTo(),
                          depDate,
                          ticketEntity.getDuration(),
                          ticketEntity.getPrice(),
                          isFastest,
                          isCheapestNonStop,
                          isCheapestWithStops,
                          false);
    }

    private Ticket(final String code,
                   final String cityNameFrom,
                   final String cityNameTo,
                   final String airportNameFrom,
                   final String airportNameTo,
                   final Date depDate,
                   final long duration,
                   final double price,
                   final boolean isFastest,
                   final boolean isCheapestNonStop,
                   final boolean isCheapestWithStops,
                   final boolean isBest) {

        if (isCheapestNonStop && isCheapestWithStops) {
            throw new IllegalArgumentException();
        }

        this.code = code;
        this.cityNameFrom = cityNameFrom;
        this.cityNameTo = cityNameTo;
        this.airportNameFrom = airportNameFrom;
        this.airportNameTo = airportNameTo;
        this.depDate = depDate;
        this.duration = duration;
        this.price = price;
        this.isFastest = isFastest;
        this.isCheapestNonStop = isCheapestNonStop;
        this.isCheapestWithStops = isCheapestWithStops;
        this.isBest = isBest;
    }

    public Ticket withIsBest(final boolean isBest) {
        if (this.isBest == isBest) {
            return this;
        } else {
            return new Ticket(code,
                              cityNameFrom,
                              cityNameTo,
                              airportNameFrom,
                              airportNameTo,
                              depDate,
                              duration,
                              price,
                              isFastest,
                              isCheapestNonStop,
                              isCheapestWithStops,
                              isBest);
        }
    }

    public String getCode() {
        return code;
    }

    public String getCityNameFrom() {
        return cityNameFrom;
    }

    public String getCityNameTo() {
        return cityNameTo;
    }

    public String getAirportNameFrom() {
        return airportNameFrom;
    }

    public String getAirportNameTo() {
        return airportNameTo;
    }

    public Date getDepDate() {
        return depDate;
    }

    public long getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public boolean isFastest() {
        return isFastest;
    }

    public boolean isCheapestNonStop() {
        return isCheapestNonStop;
    }

    public boolean isCheapestWithStops() {
        return isCheapestWithStops;
    }

    public boolean isBest() {
        return isBest;
    }

    public static final Comparator<Ticket> SORTING_BY_PRICE_MULTIPLY_DURATION = (ticket, other) ->
                    (int) (ticket.getPrice() * ticket.getDuration() - other.getPrice() * other.getDuration());
}
