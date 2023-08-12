package com.tfg.stellarium.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AstroDao extends JpaRepository<Astro, Long> {
    Astro findAstroByMapName(String name);

    boolean existsByName(String name);

    Astro findAstroByName(String name);
}
