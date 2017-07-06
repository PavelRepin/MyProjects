package com.example.hp4740.testapp.tickets;

import com.example.hp4740.testapp.api.Ticket;

import java.util.List;

public interface TicketsCallback {
    void handleTickets(List<Ticket> tickets);
}