package br.com.codigodebase.helpdesk.adapter.input.controller;

import br.com.codigodebase.helpdesk.adapter.input.dto.ticket.TicketRequest;
import br.com.codigodebase.helpdesk.adapter.input.dto.ticket.TicketResponse;
import br.com.codigodebase.helpdesk.adapter.input.mapper.TicketMapper;
import br.com.codigodebase.helpdesk.core.domain.Ticket;
import br.com.codigodebase.helpdesk.port.input.TicketInputPort;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Tickets", description = "Operations related support tickets")
@OpenAPIDefinition
@RequestMapping("tickets")
@RestController
public class TicketController {

    private final TicketInputPort ticketInputPort;

    private final TicketMapper mapper;

    public TicketController(TicketInputPort ticketInputPort, TicketMapper mapper) {
        this.ticketInputPort = ticketInputPort;
        this.mapper = mapper;
    }

    @Operation(summary = "Create a new support ticket")
    @PostMapping
    public ResponseEntity<TicketResponse> create(@RequestBody TicketRequest req) {
        Ticket ticket = mapper.toDomain(req);

        Ticket newTicket = ticketInputPort.createTicket(ticket);
        TicketResponse response = mapper.toResponse(newTicket);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
