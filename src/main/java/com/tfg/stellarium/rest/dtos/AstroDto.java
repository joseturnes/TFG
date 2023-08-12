package com.tfg.stellarium.rest.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AstroDto {

    private String description;

    private String mapName;

    private String name;
}
