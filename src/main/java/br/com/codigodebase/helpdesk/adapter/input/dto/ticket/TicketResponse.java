package br.com.codigodebase.helpdesk.adapter.input.dto.ticket;

import br.com.codigodebase.helpdesk.adapter.output.enums.TicketStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class TicketResponse {

    private UUID id;

    private String subject;

    private String description;

    private UUID createdBy;

    private TicketStatus status;
}
