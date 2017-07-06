package com.example.hp4740.testapp.api;

import java.util.Comparator;

public class Ticket {
    private String cityNameFrom;
    private String cityNameTo;
    private String iataFrom;
    private String iataTo;
    private String airportNameFrom;
    private String airportNameTo;
    private String depDate;
    private String depTime;
    private String arrDate;
    private String arrTime;
    private long duration;
    private String durationStr;
    private String airlineCode;
    private String airlineName;
    private String flightNumber;
    private String aircraft;
    private String airlineCode2;
    private String airlineName2;
    private String flightNumber2;
    private String aircraft2;
    private double price;
    private String priceCurrency;
    private String code;
    private boolean isFastest;
    private boolean isCheapestNonStop;
    private boolean isCheapestWithStops;
    private boolean isBest;

    public Ticket() {
    }

    public Ticket(String cityNameFrom, String cityNameTo, String iataFrom, String iataTo, String airportNameFrom, String airportNameTo, String depDate, String depTime, String arrDate, String arrTime, long duration, String durationStr, String airlineCode, String airlineName, String flightNumber, String aircraft, String airlineCode2, String airlineName2, String flightNumber2, String aircraft2, double price, String priceCurrency, String code, boolean isFastest, boolean isCheapestNonStop, boolean isCheapestWithStops, boolean isBest) {
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
        this.isFastest = isFastest;
        this.isCheapestNonStop = isCheapestNonStop;
        this.isCheapestWithStops = isCheapestWithStops;
        this.isBest = isBest;
    }

    public String getCityNameFrom() {
        return cityNameFrom;
    }

    public void setCityNameFrom(String cityNameFrom) {
        this.cityNameFrom = cityNameFrom;
    }

    public String getCityNameTo() {
        return cityNameTo;
    }

    public void setCityNameTo(String cityNameTo) {
        this.cityNameTo = cityNameTo;
    }

    public String getIataFrom() {
        return iataFrom;
    }

    public void setIataFrom(String iataFrom) {
        this.iataFrom = iataFrom;
    }

    public String getIataTo() {
        return iataTo;
    }

    public void setIataTo(String iataTo) {
        this.iataTo = iataTo;
    }

    public String getAirportNameFrom() {
        return airportNameFrom;
    }

    public void setAirportNameFrom(String airportNameFrom) {
        this.airportNameFrom = airportNameFrom;
    }

    public String getAirportNameTo() {
        return airportNameTo;
    }

    public void setAirportNameTo(String airportNameTo) {
        this.airportNameTo = airportNameTo;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrDate() {
        return arrDate;
    }

    public void setArrDate(String arrDate) {
        this.arrDate = arrDate;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getDurationStr() {
        return durationStr;
    }

    public void setDurationStr(String durationStr) {
        this.durationStr = durationStr;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getAirlineCode2() {
        return airlineCode2;
    }

    public void setAirlineCode2(String airlineCode2) {
        this.airlineCode2 = airlineCode2;
    }

    public String getAirlineName2() {
        return airlineName2;
    }

    public void setAirlineName2(String airlineName2) {
        this.airlineName2 = airlineName2;
    }

    public String getFlightNumber2() {
        return flightNumber2;
    }

    public void setFlightNumber2(String flightNumber2) {
        this.flightNumber2 = flightNumber2;
    }

    public String getAircraft2() {
        return aircraft2;
    }

    public void setAircraft2(String aircraft2) {
        this.aircraft2 = aircraft2;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isFastest() {
        return isFastest;
    }

    public void setFastest(boolean fastest) {
        isFastest = fastest;
    }

    public boolean isCheapestNonStop() {
        return isCheapestNonStop;
    }

    public void setCheapestNonStop(boolean cheapestNonStop) {
        isCheapestNonStop = cheapestNonStop;
    }

    public boolean isCheapestWithStops() {
        return isCheapestWithStops;
    }

    public void setCheapestWithStops(boolean cheapestWithStops) {
        isCheapestWithStops = cheapestWithStops;
    }

    public boolean isBest() {
        return isBest;
    }

    public void setBest(boolean best) {
        isBest = best;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (duration != ticket.duration) return false;
        if (Double.compare(ticket.price, price) != 0) return false;
        if (isFastest != ticket.isFastest) return false;
        if (isCheapestNonStop != ticket.isCheapestNonStop) return false;
        if (isCheapestWithStops != ticket.isCheapestWithStops) return false;
        if (isBest != ticket.isBest) return false;
        if (cityNameFrom != null ? !cityNameFrom.equals(ticket.cityNameFrom) : ticket.cityNameFrom != null)
            return false;
        if (cityNameTo != null ? !cityNameTo.equals(ticket.cityNameTo) : ticket.cityNameTo != null)
            return false;
        if (iataFrom != null ? !iataFrom.equals(ticket.iataFrom) : ticket.iataFrom != null)
            return false;
        if (iataTo != null ? !iataTo.equals(ticket.iataTo) : ticket.iataTo != null) return false;
        if (airportNameFrom != null ? !airportNameFrom.equals(ticket.airportNameFrom) : ticket.airportNameFrom != null)
            return false;
        if (airportNameTo != null ? !airportNameTo.equals(ticket.airportNameTo) : ticket.airportNameTo != null)
            return false;
        if (depDate != null ? !depDate.equals(ticket.depDate) : ticket.depDate != null)
            return false;
        if (depTime != null ? !depTime.equals(ticket.depTime) : ticket.depTime != null)
            return false;
        if (arrDate != null ? !arrDate.equals(ticket.arrDate) : ticket.arrDate != null)
            return false;
        if (arrTime != null ? !arrTime.equals(ticket.arrTime) : ticket.arrTime != null)
            return false;
        if (durationStr != null ? !durationStr.equals(ticket.durationStr) : ticket.durationStr != null)
            return false;
        if (airlineCode != null ? !airlineCode.equals(ticket.airlineCode) : ticket.airlineCode != null)
            return false;
        if (airlineName != null ? !airlineName.equals(ticket.airlineName) : ticket.airlineName != null)
            return false;
        if (flightNumber != null ? !flightNumber.equals(ticket.flightNumber) : ticket.flightNumber != null)
            return false;
        if (aircraft != null ? !aircraft.equals(ticket.aircraft) : ticket.aircraft != null)
            return false;
        if (airlineCode2 != null ? !airlineCode2.equals(ticket.airlineCode2) : ticket.airlineCode2 != null)
            return false;
        if (airlineName2 != null ? !airlineName2.equals(ticket.airlineName2) : ticket.airlineName2 != null)
            return false;
        if (flightNumber2 != null ? !flightNumber2.equals(ticket.flightNumber2) : ticket.flightNumber2 != null)
            return false;
        if (aircraft2 != null ? !aircraft2.equals(ticket.aircraft2) : ticket.aircraft2 != null)
            return false;
        if (priceCurrency != null ? !priceCurrency.equals(ticket.priceCurrency) : ticket.priceCurrency != null)
            return false;
        return code != null ? code.equals(ticket.code) : ticket.code == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = cityNameFrom != null ? cityNameFrom.hashCode() : 0;
        result = 31 * result + (cityNameTo != null ? cityNameTo.hashCode() : 0);
        result = 31 * result + (iataFrom != null ? iataFrom.hashCode() : 0);
        result = 31 * result + (iataTo != null ? iataTo.hashCode() : 0);
        result = 31 * result + (airportNameFrom != null ? airportNameFrom.hashCode() : 0);
        result = 31 * result + (airportNameTo != null ? airportNameTo.hashCode() : 0);
        result = 31 * result + (depDate != null ? depDate.hashCode() : 0);
        result = 31 * result + (depTime != null ? depTime.hashCode() : 0);
        result = 31 * result + (arrDate != null ? arrDate.hashCode() : 0);
        result = 31 * result + (arrTime != null ? arrTime.hashCode() : 0);
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + (durationStr != null ? durationStr.hashCode() : 0);
        result = 31 * result + (airlineCode != null ? airlineCode.hashCode() : 0);
        result = 31 * result + (airlineName != null ? airlineName.hashCode() : 0);
        result = 31 * result + (flightNumber != null ? flightNumber.hashCode() : 0);
        result = 31 * result + (aircraft != null ? aircraft.hashCode() : 0);
        result = 31 * result + (airlineCode2 != null ? airlineCode2.hashCode() : 0);
        result = 31 * result + (airlineName2 != null ? airlineName2.hashCode() : 0);
        result = 31 * result + (flightNumber2 != null ? flightNumber2.hashCode() : 0);
        result = 31 * result + (aircraft2 != null ? aircraft2.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (priceCurrency != null ? priceCurrency.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (isFastest ? 1 : 0);
        result = 31 * result + (isCheapestNonStop ? 1 : 0);
        result = 31 * result + (isCheapestWithStops ? 1 : 0);
        result = 31 * result + (isBest ? 1 : 0);
        return result;
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
                ", isFastest=" + isFastest +
                ", isCheapestNonStop=" + isCheapestNonStop +
                ", isCheapestWithStops=" + isCheapestWithStops +
                ", isBest=" + isBest +
                '}';
    }

    public static final Comparator<Ticket> SORTING_BY_PRICE_MULTIPLY_DURATION = new Comparator<Ticket>() {
        @Override
        public int compare(Ticket ticket, Ticket t1) {
            return (int) (ticket.getPrice() * ticket.getDuration() - t1.getPrice() * t1.getDuration());
        }
    };
}
