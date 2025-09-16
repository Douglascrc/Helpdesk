package br.com.codigodebase.helpdesk.core.domain;

import java.util.UUID;

public class Ticket {
    private UUID id;
    private String subject;
    private String description;
    private String status;
    private UUID createdBy;
    private UUID atendantUser;
    private UUID updatedBy;


    public Ticket() {}

    public Ticket(UUID id, String subject, String description, String status, UUID createdBy, UUID atendantUser, UUID updatedBy) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.createdBy = createdBy;
        this.atendantUser = atendantUser;
        this.updatedBy = updatedBy;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getAtendantUser() {
        return atendantUser;
    }

    public void setAtendantUser(UUID atendantUser) {
        this.atendantUser = atendantUser;
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
}
