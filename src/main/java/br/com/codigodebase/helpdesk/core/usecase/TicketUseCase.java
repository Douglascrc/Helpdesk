package br.com.codigodebase.helpdesk.core.usecase;

import br.com.codigodebase.helpdesk.adapter.input.mapper.TicketMapper;
import br.com.codigodebase.helpdesk.adapter.output.enums.TicketStatus;
import br.com.codigodebase.helpdesk.core.domain.Ticket;
import br.com.codigodebase.helpdesk.core.domain.TicketInteraction;
import br.com.codigodebase.helpdesk.core.domain.User;
import br.com.codigodebase.helpdesk.infrastructure.exception.BusinessException;
import br.com.codigodebase.helpdesk.port.input.TicketInputPort;
import br.com.codigodebase.helpdesk.port.output.TicketOutputPort;
import br.com.codigodebase.helpdesk.port.output.UserOutputPort;

public class TicketUseCase implements TicketInputPort {

    private TicketOutputPort ticketOutputPort;
    private UserOutputPort userOutputPort;
    private TicketMapper mapper;

    public TicketUseCase(TicketOutputPort ticketOutputPort, UserOutputPort userOutputPort) {
        this.ticketOutputPort = ticketOutputPort;
        this.userOutputPort = userOutputPort;
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

    @Override
    public TicketInteraction addInteraction(TicketInteraction interaction) {
        Ticket ticket = ticketOutputPort.findById(interaction.getTicketId())
                .orElseThrow(() -> new BusinessException("Ticket not found with id: " + interaction.getTicketId()));

        User user = userOutputPort.findById(interaction.getUserId())
                .orElseThrow(() -> new BusinessException("User not found with id: " + interaction.getUserId()));

        TicketInteraction newInteraction = new TicketInteraction();
        newInteraction.setTicketId(ticket.getId());
        newInteraction.setUserId(user.getId());
        newInteraction.setMessage(interaction.getMessage());
        newInteraction.setCreatedBy(user);
        newInteraction.setSentByUser(user);
        newInteraction.setStatus(TicketStatus.IN_PROGRESS);

        ticket.setStatus(TicketStatus.IN_PROGRESS.name());
        ticketOutputPort.saveInteraction(newInteraction);
        ticketOutputPort.save(ticket);

        return newInteraction;
    }
}