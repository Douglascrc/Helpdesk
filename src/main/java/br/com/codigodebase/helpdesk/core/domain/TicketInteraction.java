package br.com.codigodebase.helpdesk.core.domain;

import br.com.codigodebase.helpdesk.adapter.output.enums.TicketStatus;

import java.util.UUID;

public class TicketInteraction {
    private UUID id;
    private String message;
    private User sentByUser;
    private UUID userId;
    private UUID ticketId;
    private TicketStatus status;
    private User createdBy;


    public TicketInteraction() {}

    public TicketInteraction(UUID id, String message, User sentByUser, UUID userId, UUID ticketId, TicketStatus status, User createdBy) {
        this.id = id;
        this.message = message;
        this.sentByUser = sentByUser;
        this.userId = userId;
        this.ticketId = ticketId;
        this.status = status;
        this.createdBy = createdBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSentByUser() {
        return sentByUser;
    }

    public void setSentByUser(User sentByUser) {
        this.sentByUser = sentByUser;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

}
