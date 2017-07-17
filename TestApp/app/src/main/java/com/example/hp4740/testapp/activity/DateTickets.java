package com.example.hp4740.testapp.activity;


import com.example.hp4740.testapp.api.TicketEntity;

import java.util.List;

class DateTickets {
    private String date;
    private List<TicketEntity> tickets;

    DateTickets(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateTickets that = (DateTickets) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return tickets != null ? tickets.equals(that.tickets) : that.tickets == null;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DateTickets{" +
                "date='" + date + '\'' +
                ", getTickets=" + tickets +
                '}';
    }
}
