package com.example.hp4740.testapp.api;

public class TicketEntity {

    private final String cityNameFrom;
    private final String cityNameTo;
    private final String iataFrom;
    private final String iataTo;
    private final String airportNameFrom;
    private final String airportNameTo;
    private final String depDate;
    private final String depTime;
    private final String arrDate;
    private final String arrTime;
    private final long duration;
    private final String durationStr;
    private final String airlineCode;
    private final String airlineName;
    private final String flightNumber;
    private final String aircraft;
    private final String airlineCode2;
    private final String airlineName2;
    private final String flightNumber2;
    private final String aircraft2;
    private final double price;
    private final String priceCurrency;
    private final String code;


    public TicketEntity(String cityNameFrom,
                        String cityNameTo,
                        String iataFrom,
                        String iataTo,
                        String airportNameFrom,
                        String airportNameTo,
                        String depDate,
                        String depTime,
                        String arrDate,
                        String arrTime,
                        long duration,
                        String durationStr,
                        String airlineCode,
                        String airlineName,
                        String flightNumber,
                        String aircraft,
                        String airlineCode2,
                        String airlineName2,
                        String flightNumber2,
                        String aircraft2,
                        double price,
                        String priceCurrency,
                        String code) {
        this.cityNameFrom = cityNameFrom;
        this.cityNameTo = cityNameTo;
        this.iataFrom = iataFrom;
        this.iataTo = iataTo;
        this.airportNameFrom = airportNameFrom;
        this.airportNameTo = airportNameTo;
        this.depDate = depDate;
        this.depTime = depTime;
        this.arrDate = arrDate;
        this.arrTime = arrTime;
        this.duration = duration;
        this.durationStr = durationStr;
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        this.aircraft = aircraft;
        this.airlineCode2 = airlineCode2;
        this.airlineName2 = airlineName2;
        this.flightNumber2 = flightNumber2;
        this.aircraft2 = aircraft2;
        this.price = price;
        this.priceCurrency = priceCurrency;
        this.code = code;
    }

    public String getCityNameFrom() {
        return cityNameFrom;
    }

    public String getCityNameTo() {
        return cityNameTo;
    }

    public String getIataFrom() {
        return iataFrom;
    }

    public String getIataTo() {
        return iataTo;
    }

    public String getAirportNameFrom() {
        return airportNameFrom;
    }

    public String getAirportNameTo() {
        return airportNameTo;
    }

    public String getDepDate() {
        return depDate;
    }

    public String getDepTime() {
        return depTime;
    }

    public String getArrDate() {
        return arrDate;
    }

    public String getArrTime() {
        return arrTime;
    }

    public long getDuration() {
        return duration;
    }

    public String getDurationStr() {
        return durationStr;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAircraft() {
        return aircraft;
    }

    public String getAirlineCode2() {
        return airlineCode2;
    }

    public String getAirlineName2() {
        return airlineName2;
    }

    public String getFlightNumber2() {
        return flightNumber2;
    }

    public String getAircraft2() {
        return aircraft2;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "cityNameFrom='" + cityNameFrom + '\'' +
                ", cityNameTo='" + cityNameTo + '\'' +
                ", iataFrom='" + iataFrom + '\'' +
                ", iataTo='" + iataTo + '\'' +
                ", airportNameFrom='" + airportNameFrom + '\'' +
                ", airportNameTo='" + airportNameTo + '\'' +
                ", depDate='" + depDate + '\'' +
                ", depTime='" + depTime + '\'' +
                ", arrDate='" + arrDate + '\'' +
                ", arrTime='" + arrTime + '\'' +
                ", duration=" + duration +
                ", durationStr='" + durationStr + '\'' +
                ", airlineCode='" + airlineCode + '\'' +
                ", airlineName='" + airlineName + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", aircraft='" + aircraft + '\'' +
                ", airlineCode2='" + airlineCode2 + '\'' +
                ", airlineName2='" + airlineName2 + '\'' +
                ", flightNumber2='" + flightNumber2 + '\'' +
                ", aircraft2='" + aircraft2 + '\'' +
                ", price=" + price +
                ", priceCurrency='" + priceCurrency + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

}
