package br.com.codigodebase.helpdesk.adapter.input.dto.ticketInteraction;

import br.com.codigodebase.helpdesk.adapter.input.dto.attachment.AttachmentRequest;
import lombok.Data;

import java.util.List;

@Data
public class TicketInteratictionRequest {

    private String message;

    private List<AttachmentRequest> attachments;
}
