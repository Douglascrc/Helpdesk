package br.com.codigodebase.helpdesk.core.domain;

import br.com.codigodebase.helpdesk.adapter.output.enums.TicketStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TicketInteraction {
    private UUID id;
    private String message;
    private User sentByUser;
    private UUID userId;
    private UUID ticketId;
    private TicketStatus status;
    private User createdBy;
    private Ticket ticket;
    private List<Attachment> attachments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID updatedBy;

    public TicketInteraction() {}

    public TicketInteraction(UUID id, String message, User sentByUser, UUID userId, UUID ticketId, TicketStatus status, User createdBy, Ticket ticket, List<Attachment> attachments, LocalDateTime createdAt, LocalDateTime updatedAt, UUID updatedBy) {
        this.id = id;
        this.message = message;
        this.sentByUser = sentByUser;
        this.userId = userId;
        this.ticketId = ticketId;
        this.status = status;
        this.createdBy = createdBy;
        this.ticket = ticket;
        this.attachments = attachments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }
}
