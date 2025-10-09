package br.com.codigodebase.helpdesk.adapter.output.entity;

import br.com.codigodebase.helpdesk.adapter.output.enums.TicketStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class TicketInteractionEntity {

    private UUID id;

    private TicketEntity ticket;

    private UserEntity sentByUser;

    private String message;

    @Builder.Default
    private TicketStatus status = TicketStatus.OPEN;

    private UserEntity createdBy;

    private UserEntity updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
