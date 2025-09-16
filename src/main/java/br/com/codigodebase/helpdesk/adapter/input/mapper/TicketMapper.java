package br.com.codigodebase.helpdesk.adapter.input.mapper;

import br.com.codigodebase.helpdesk.adapter.input.dto.ticket.TicketRequest;
import br.com.codigodebase.helpdesk.adapter.input.dto.ticket.TicketResponse;
import br.com.codigodebase.helpdesk.core.domain.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketMapper INSTANCE =  Mappers.getMapper(TicketMapper.class);

    Ticket toDomain(TicketRequest ticketRequest);

    TicketResponse toResponse(Ticket ticket);

}
