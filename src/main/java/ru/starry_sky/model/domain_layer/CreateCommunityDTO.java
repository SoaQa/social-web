package ru.starry_sky.model.domain_layer;

import lombok.Data;

@Data
public class CreateCommunityDTO {
    private String name;
    private int ageLimit;
}
