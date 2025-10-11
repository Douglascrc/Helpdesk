package br.com.codigodebase.helpdesk.adapter.input.dto.ticketInteraction;

import lombok.Data;

import java.util.UUID;

@Data
public class TicketInteratictionRequest {

    private String message;

    private UUID userId;

    private String attachment;
}
