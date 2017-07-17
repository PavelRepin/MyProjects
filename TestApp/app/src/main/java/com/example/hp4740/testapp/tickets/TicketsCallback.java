package com.example.hp4740.testapp.tickets;

import com.example.hp4740.testapp.api.TicketEntity;

import java.util.List;

public interface TicketsCallback {
    void handleTickets(List<TicketEntity> tickets);
    void handleError(Throwable error);
}