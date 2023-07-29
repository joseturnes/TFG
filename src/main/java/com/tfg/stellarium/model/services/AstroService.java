package com.tfg.stellarium.model.services;

import com.tfg.stellarium.model.common.exceptions.DuplicateInstanceException;
import com.tfg.stellarium.model.entities.Astro;

import java.util.List;

public interface AstroService {

    void createAstro(Astro astro) throws DuplicateInstanceException;

    Astro getAstroById(Long id);

    Astro getAstroByName(String name);

   List<Astro> getAllAstros();

   boolean deleteByName(String name);
}
