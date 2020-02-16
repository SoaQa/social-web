package ru.starry_sky.model.domain_layer;

import lombok.Data;

@Data
public class NewUser {
    private String login;
    private String password;
    private String email;
}
