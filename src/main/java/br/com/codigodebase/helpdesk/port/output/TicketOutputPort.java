package br.com.codigodebase.helpdesk.port.output;

import br.com.codigodebase.helpdesk.core.domain.Ticket;
import br.com.codigodebase.helpdesk.core.domain.TicketInteraction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketOutputPort {
    Ticket save(Ticket ticket);
    List<Ticket> findAll();
    Optional<Ticket> findById(UUID id);
    void deleteById(UUID id);
    TicketInteraction saveInteraction(TicketInteraction interaction);
}
