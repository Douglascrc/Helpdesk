package br.com.codigodebase.helpdesk.adapter.input.mapper;

import br.com.codigodebase.helpdesk.adapter.input.dto.ticket.TicketRequest;
import br.com.codigodebase.helpdesk.adapter.input.dto.ticket.TicketResponse;
import br.com.codigodebase.helpdesk.adapter.input.dto.ticketInteraction.TicketInteractionResponse;
import br.com.codigodebase.helpdesk.adapter.input.dto.ticketInteraction.TicketInteratictionRequest;
import br.com.codigodebase.helpdesk.core.domain.Ticket;
import br.com.codigodebase.helpdesk.core.domain.TicketInteraction;
import br.com.codigodebase.helpdesk.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {
    TicketMapper INSTANCE =  Mappers.getMapper(TicketMapper.class);

    Ticket toDomain(TicketRequest ticketRequest);

    TicketResponse toResponse(Ticket ticket);

    TicketInteraction toDomain(TicketInteratictionRequest request);

    TicketInteractionResponse toResponse(TicketInteraction ticket);

    TicketInteraction toDomain(TicketInteraction request);

    default User map(UUID userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setId(userId);
        return user;
    }

    default UUID map(User user) {
        return user == null ? null : user.getId();
    }

}
