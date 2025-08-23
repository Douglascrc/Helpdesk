package br.com.codigodebase.helpdesk.adapter.input.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest{

    private String username;
    private String name;
    private String email;
    private String password;
}
