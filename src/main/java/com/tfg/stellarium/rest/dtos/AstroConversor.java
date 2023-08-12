package com.tfg.stellarium.rest.dtos;

import com.tfg.stellarium.model.entities.Astro;

import java.util.ArrayList;
import java.util.List;

public class AstroConversor {

    public static final Astro toAstro(AstroDto astroDto){
        return Astro.builder()
                .name(astroDto.getName())
                .mapName(astroDto.getMapName())
                .description(astroDto.getDescription())
                .build();
    }

    public static final AstroDto toAstroDto(Astro astro){
        return AstroDto.builder()
                .mapName(astro.getMapName())
                .description(astro.getDescription())
                .name(astro.getName())
                .build();
    }

    public static final List<AstroDto> toAstroDtos(List<Astro> astros){
        List<AstroDto> result = new ArrayList<>();

        astros.forEach(astro -> result.add(toAstroDto(astro)));

        return result;
    }
}
