package br.com.codigodebase.helpdesk.adapter.input.controller;

import br.com.codigodebase.helpdesk.adapter.input.dto.ticket.TicketRequest;
import br.com.codigodebase.helpdesk.adapter.input.dto.ticket.TicketResponse;
import br.com.codigodebase.helpdesk.adapter.input.mapper.TicketMapper;
import br.com.codigodebase.helpdesk.core.domain.Ticket;
import br.com.codigodebase.helpdesk.port.input.TicketInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class TicketControllerTest {

    private TicketInputPort ticketInputPort;
    private TicketMapper mapper;
    private TicketController controller;


    @BeforeEach
    void setUp() {
        ticketInputPort = mock(TicketInputPort.class);
        mapper = mock(TicketMapper.class);
        controller = new TicketController(ticketInputPort, mapper);
    }

    @Test
    void testCreateTicketReturnsCreatedResponse() {
        TicketRequest request = new TicketRequest();
        Ticket domainTicket = new Ticket();
        Ticket createdTicket = new Ticket();
        TicketResponse response = new TicketResponse();

        when(mapper.toDomain(request)).thenReturn(domainTicket);
        when(ticketInputPort.createTicket(domainTicket)).thenReturn(createdTicket);
        when(mapper.toResponse(createdTicket)).thenReturn(response);

        ResponseEntity<TicketResponse> result = controller.create(request);

        assertEquals(201, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(mapper).toDomain(request);
        verify(ticketInputPort).createTicket(domainTicket);
        verify(mapper).toResponse(createdTicket);
    }
}
