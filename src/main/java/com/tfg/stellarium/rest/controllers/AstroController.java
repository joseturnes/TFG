package com.tfg.stellarium.rest.controllers;

import com.tfg.stellarium.model.common.exceptions.DuplicateInstanceException;
import com.tfg.stellarium.model.entities.Astro;
import com.tfg.stellarium.model.services.AstroService;
import com.tfg.stellarium.rest.dtos.AstroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.tfg.stellarium.rest.dtos.AstroConversor.*;

@RestController
@RequestMapping("/astros")
public class AstroController {

    @Autowired
    private AstroService astroService;

    @PostMapping("/guardar")
    public ResponseEntity<String> createAstro(@RequestBody AstroDto astroDto) {
        try {
            astroService.createAstro(toAstro(astroDto));
            return ResponseEntity.ok("Astro creado correctamente");
        } catch (DuplicateInstanceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public AstroDto searchAstroById(@PathVariable("id") Long id) {
        Astro astro = astroService.getAstroById(id);
        return toAstroDto(astro);
    }


    @GetMapping("/{name}")
    public AstroDto searchAstroByName(@PathVariable("name") String name) {
        Astro astro = astroService.getAstroByName(name);
        return toAstroDto(astro);
    }

    @GetMapping
    public List<AstroDto> getAllAstros() {
        List<Astro> astros = astroService.getAllAstros();
        return toAstroDtos(astros);
    }

    @DeleteMapping("/{name}")
    public boolean deleteAstroByName(@PathVariable String name) {
        return astroService.deleteByName(name);
    }
}
