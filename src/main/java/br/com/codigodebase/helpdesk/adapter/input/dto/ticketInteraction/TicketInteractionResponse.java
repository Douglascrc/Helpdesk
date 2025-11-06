package br.com.codigodebase.helpdesk.adapter.input.dto.ticketInteraction;

import br.com.codigodebase.helpdesk.adapter.input.dto.attachment.AttachmentRequest;
import br.com.codigodebase.helpdesk.adapter.input.dto.user.UserRequest;
import br.com.codigodebase.helpdesk.adapter.output.enums.TicketStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class TicketInteractionResponse {

    private UUID id;

    private String message;

    private UserRequest sentByUser;

    private TicketStatus status;

    private List<AttachmentRequest> attachments;

    private LocalDateTime createdAt;
}
