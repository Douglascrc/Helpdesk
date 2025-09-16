package br.com.codigodebase.helpdesk.port.input;

import br.com.codigodebase.helpdesk.core.domain.Ticket;

public interface TicketInputPort {
    Ticket createTicket(Ticket ticket);
}
