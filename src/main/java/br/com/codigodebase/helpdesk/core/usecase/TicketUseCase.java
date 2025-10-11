package br.com.codigodebase.helpdesk.core.usecase;

import br.com.codigodebase.helpdesk.adapter.input.mapper.TicketMapper;

import br.com.codigodebase.helpdesk.adapter.output.enums.TicketStatus;
import br.com.codigodebase.helpdesk.core.domain.*;
import br.com.codigodebase.helpdesk.infrastructure.exception.BusinessException;
import br.com.codigodebase.helpdesk.infrastructure.utils.FileUtils;
import br.com.codigodebase.helpdesk.port.input.TicketInputPort;
import br.com.codigodebase.helpdesk.port.output.TicketOutputPort;
import br.com.codigodebase.helpdesk.port.output.UserOutputPort;

import java.io.File;
import java.io.IOException;


public class TicketUseCase implements TicketInputPort {

    private TicketOutputPort ticketOutputPort;
    private UserOutputPort userOutputPort;
    private TicketMapper mapper;
    private String attachmentsFolder;

    public TicketUseCase(TicketOutputPort ticketOutputPort, UserOutputPort userOutputPort, String attachmentsFolder) {
        this.ticketOutputPort = ticketOutputPort;
        this.userOutputPort = userOutputPort;
        this.attachmentsFolder = attachmentsFolder;
    }

    public Ticket createTicket(Ticket ticket) {

        if (ticket.getCreatedBy() == null) {
            throw new IllegalArgumentException("createdBy cannot be null");
        }

        Ticket newTicket = new Ticket();
        newTicket.setSubject(ticket.getSubject());
        newTicket.setDescription(ticket.getDescription());
        newTicket.setCreatedBy(ticket.getCreatedBy());

        return ticketOutputPort.save(newTicket);
    }

    @Override
    public TicketInteraction addInteraction(TicketInteraction interaction) {
        Ticket ticket = ticketOutputPort.findById(interaction.getTicketId())
                .orElseThrow(() -> new BusinessException("Ticket not found with id: " + interaction.getTicketId()));

        User user = userOutputPort.findById(interaction.getUserId())
                .orElseThrow(() -> new BusinessException("User not found with id: " + interaction.getUserId()));

        TicketInteraction newInteraction = new TicketInteraction();
        newInteraction.setTicketId(ticket.getId());
        newInteraction.setUserId(user.getId());
        newInteraction.setMessage(interaction.getMessage());
        newInteraction.setCreatedBy(user);
        newInteraction.setSentByUser(user);
        newInteraction.setStatus(TicketStatus.IN_PROGRESS);

        ticketOutputPort.saveInteraction(newInteraction);

        if (interaction.getAttachments() != null && !interaction.getAttachments().isEmpty()) {
            for (Attachment attachment : interaction.getAttachments()) {
                TicketAttachment ticketAttachment = new TicketAttachment();
                ticketAttachment.setTicket(ticket);
                ticketAttachment.setCreatedBy(user);
                ticketAttachment.setFilename(attachment.getFilename());
                ticketAttachment = ticketOutputPort.saveTicketAttachment(ticketAttachment);
                saveFileToDisk(ticketAttachment, attachment.getContent());
            }
        }

        ticket.setUpdatedBy(user.getId());
        ticket.setStatus(TicketStatus.IN_PROGRESS.name());
        ticketOutputPort.save(ticket);

        return newInteraction;
    }

    private void saveFileToDisk(TicketAttachment entity, String content) {
        byte[] attachmentContent = null;
        try {
            attachmentContent = FileUtils.convertBase64ToByteArray(content);
            String fileName = entity.getId().toString();

            FileUtils.saveByteArrayToFile(attachmentContent, new File(attachmentsFolder + fileName));
        } catch (IOException ex) {
            throw new BusinessException("Error saving " + entity.getFilename() + " file");
        }
    }
}