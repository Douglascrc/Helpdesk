package br.com.codigodebase.helpdesk.adapter.input.dto.ticket;

import br.com.codigodebase.helpdesk.adapter.input.dto.user.UserRequest;
import br.com.codigodebase.helpdesk.adapter.output.enums.TicketStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
public class TicketResponse {

    private UUID id;

    private String subject;

    private String description;

    private UserRequest supportUser;

    private UserRequest createdBy;

    private UUID updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private TicketStatus status;

}
