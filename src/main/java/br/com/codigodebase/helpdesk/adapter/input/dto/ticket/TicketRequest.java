package br.com.codigodebase.helpdesk.adapter.input.dto.ticket;

import br.com.codigodebase.helpdesk.adapter.input.dto.attachment.AttachmentRequest;
import lombok.Data;

import java.util.List;

@Data
public class TicketRequest {

    private String subject;

    private String description;

    private List<AttachmentRequest> attachments;
}
