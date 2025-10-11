package br.com.codigodebase.helpdesk.adapter.input.dto.attachment;

import lombok.Data;

@Data
public class AttachmentRequest {

    private String filename;

    private String content;
}
