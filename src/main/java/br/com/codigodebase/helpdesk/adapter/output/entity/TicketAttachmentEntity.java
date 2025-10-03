package br.com.codigodebase.helpdesk.adapter.output.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class TicketAttachmentEntity {

    private UUID id;

    private TicketEntity ticket;

    private TicketInteractionEntity ticketInteraction;

    private String fileName;

    private UserEntity createdBy;

    private UserEntity updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
