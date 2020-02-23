package ru.starry_sky.model.domain_layer;

import lombok.Data;

@Data
public class AuthenticatedRequestDTO {
    private String login;
    private String password;

}
