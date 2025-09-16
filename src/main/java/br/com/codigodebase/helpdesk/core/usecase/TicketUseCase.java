package br.com.codigodebase.helpdesk.core.usecase;

import br.com.codigodebase.helpdesk.core.domain.Ticket;
import br.com.codigodebase.helpdesk.port.input.TicketInputPort;
import br.com.codigodebase.helpdesk.port.output.TicketOutputPort;


public class TicketUseCase implements TicketInputPort {

    private TicketOutputPort ticketOutputPort;

    public TicketUseCase(TicketOutputPort ticketOutputPort) {
        this.ticketOutputPort = ticketOutputPort;
    }

    public Ticket createTicket(Ticket ticket) {

        if (ticket.getCreatedBy() == null) {
            throw new IllegalArgumentException("createdBy cannot be null");
        }

        Ticket newTicket = new Ticket();
        newTicket.setSubject(ticket.getSubject());
        newTicket.setDescription(ticket.getDescription());
        newTicket.setCreatedBy(ticket.getCreatedBy());

        return ticketOutputPort.save(newTicket);
    }
}