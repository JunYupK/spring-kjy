package com.kjy.service;

import com.kjy.DAO.Ticket;
import com.kjy.database.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketDecreaseService {
    private final TicketRepository ticketRepository;
    public Ticket ticketDecrease(Long ticketId){
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(IllegalArgumentException::new);
        ticket.generalDecrease();
        ticketRepository.save(ticket);
        return ticket;
    }

}
