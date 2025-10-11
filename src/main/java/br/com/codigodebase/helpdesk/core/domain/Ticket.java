package br.com.codigodebase.helpdesk.core.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Ticket {
    private UUID id;
    private String subject;
    private String description;
    private String status;
    private User createdBy;
    private User supportUser;
    private UUID updatedBy;
    private List<Attachment> attachments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Ticket() {}

    public Ticket(UUID id, String subject, String description, String status, User createdBy, User supportUser, UUID updatedBy, List<Attachment> attachments, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.createdBy = createdBy;
        this.supportUser = supportUser;
        this.updatedBy = updatedBy;
        this.attachments = attachments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getSupportUser() {
        return supportUser;
    }

    public void setSupportUser(User supportUser) {
        this.supportUser = supportUser;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
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
}
