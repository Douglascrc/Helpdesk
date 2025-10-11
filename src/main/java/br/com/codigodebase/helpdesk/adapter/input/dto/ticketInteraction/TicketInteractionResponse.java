package br.com.codigodebase.helpdesk.adapter.input.dto.ticketInteraction;

import br.com.codigodebase.helpdesk.adapter.input.dto.attachment.AttachmentRequest;
import br.com.codigodebase.helpdesk.adapter.output.enums.TicketStatus;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TicketInteractionResponse {

    private UUID id;

    private String message;

    private UUID userId;

    private TicketStatus status;

    private List<AttachmentRequest> attachments;
}
