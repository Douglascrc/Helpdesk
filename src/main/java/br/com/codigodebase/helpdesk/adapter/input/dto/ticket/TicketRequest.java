package br.com.codigodebase.helpdesk.adapter.input.dto.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TicketRequest {

    @NotBlank
    private String subject;

    @NotBlank
    private String description;

    @NotNull
    private UUID createdBy;
}
