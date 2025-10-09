package br.com.codigodebase.helpdesk.adapter.input.mapper;

import br.com.codigodebase.helpdesk.adapter.input.dto.ticket.TicketRequest;
import br.com.codigodebase.helpdesk.adapter.input.dto.ticket.TicketResponse;
import br.com.codigodebase.helpdesk.adapter.input.dto.ticketInteraction.TicketInteractionResponse;
import br.com.codigodebase.helpdesk.adapter.input.dto.ticketInteraction.TicketInteratictionRequest;
import br.com.codigodebase.helpdesk.core.domain.Ticket;
import br.com.codigodebase.helpdesk.core.domain.TicketInteraction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {
    TicketMapper INSTANCE =  Mappers.getMapper(TicketMapper.class);

    Ticket toDomain(TicketRequest ticketRequest);

    TicketResponse toResponse(Ticket ticket);

    TicketInteraction toDomain(TicketInteratictionRequest request);

    TicketInteractionResponse toResponse(TicketInteraction ticket);

    TicketInteraction toDomain(TicketInteraction request);
}
