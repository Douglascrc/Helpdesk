package br.com.codigodebase.helpdesk.adapter.output.repository;

import br.com.codigodebase.helpdesk.core.domain.Ticket;
import br.com.codigodebase.helpdesk.core.domain.TicketAttachment;
import br.com.codigodebase.helpdesk.core.domain.TicketInteraction;
import br.com.codigodebase.helpdesk.core.domain.User;
import br.com.codigodebase.helpdesk.port.output.TicketOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TicketRepository implements TicketOutputPort {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Ticket save(Ticket ticket) {
        UUID newTicketId = jdbcTemplate.execute((ConnectionCallback<UUID>) (connection) -> {
            try(CallableStatement cs = connection.prepareCall("CALL pr_create_ticket(?,?,?,?)")){
                cs.setString(1, ticket.getSubject());
                cs.setString(2, ticket.getDescription());
                cs.setObject(3, ticket.getCreatedBy().getId(), Types.OTHER);
                cs.registerOutParameter(4, Types.OTHER);
                cs.execute();
                return (UUID) cs.getObject(4);
            }
        });
        return findById(newTicketId)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve created ticket"));
    }

    @Override
    public Optional<Ticket> findById(UUID id) {
        try {
            String sql = "SELECT id, subject, description, created_by, status FROM tickets WHERE id = ?";

            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Ticket ticket = new Ticket();
                User user = new User();
                user.setId(UUID.fromString(rs.getString("created_by")));
                ticket.setId(UUID.fromString(rs.getString("id")));
                ticket.setSubject(rs.getString("subject"));
                ticket.setDescription(rs.getString("description"));
                ticket.setCreatedBy(user);
                ticket.setStatus(rs.getString("status"));
                return ticket;
            }, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<Ticket> findAll() {
        return List.of();
    }

    @Override
    public TicketInteraction saveInteraction(TicketInteraction interaction) {
        UUID interactionId = jdbcTemplate.execute((ConnectionCallback<UUID>) (connection) -> {
            try (CallableStatement cs = connection.prepareCall("CALL pr_add_ticket_interaction(?,?,?,?,?)")) {
                cs.setObject(1, interaction.getTicketId(), Types.OTHER);
                cs.setObject(2, interaction.getUserId(), Types.OTHER);
                cs.setString(3, interaction.getMessage());
                cs.setObject(4, interaction.getStatus(), Types.VARCHAR);
                cs.registerOutParameter(5,Types.OTHER);
                cs.execute();
                return (UUID) cs.getObject(5);
            }
        });
        interaction.setId(interactionId);
        return interaction;
    }

    @Override
    public TicketAttachment saveTicketAttachment(TicketAttachment ticketAttachment) {
        UUID attachmentId = jdbcTemplate.execute((ConnectionCallback<UUID>) (connection) -> {
            try (CallableStatement cs = connection.prepareCall("CALL pr_add_ticket_attachment(?,?,?,?)")) {
                cs.setObject(1, ticketAttachment.getTicket().getId(), Types.OTHER);
                cs.setObject(2, ticketAttachment.getCreatedBy().getId(), Types.OTHER);
                cs.setString(3, ticketAttachment.getFilename());
                cs.registerOutParameter(4, Types.OTHER);
                cs.execute();
                return (UUID) cs.getObject(4);
            }
        });
        ticketAttachment.setId(attachmentId);
        return ticketAttachment;
    }

}
