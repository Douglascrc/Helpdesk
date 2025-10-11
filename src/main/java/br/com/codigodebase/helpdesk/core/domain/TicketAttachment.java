package br.com.codigodebase.helpdesk.core.domain;

import java.util.UUID;

public class TicketAttachment {
    private UUID id;
    private Ticket ticket;
    private TicketInteraction ticketInteraction;
    private String filename;
    private User createdBy;
    private User updatedBy;

    public TicketAttachment() {}

    public TicketAttachment(UUID id, Ticket ticket, TicketInteraction ticketInteraction, String filename, User createdBy, User updatedBy) {
        this.id = id;
        this.ticket = ticket;
        this.ticketInteraction = ticketInteraction;
        this.filename = filename;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public TicketInteraction getTicketInteraction() {
        return ticketInteraction;
    }

    public void setTicketInteraction(TicketInteraction ticketInteraction) {
        this.ticketInteraction = ticketInteraction;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }
}
