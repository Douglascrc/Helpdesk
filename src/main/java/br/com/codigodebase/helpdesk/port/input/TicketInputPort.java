package br.com.codigodebase.helpdesk.port.input;

import br.com.codigodebase.helpdesk.core.domain.Ticket;
import br.com.codigodebase.helpdesk.core.domain.TicketInteraction;

public interface TicketInputPort {
    Ticket createTicket(Ticket ticket);
    TicketInteraction addInteraction(TicketInteraction interaction);

}
