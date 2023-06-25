package com.tfg.stellarium.rest.controllers;

import com.tfg.stellarium.model.entities.Imagen;
import com.tfg.stellarium.model.services.ImagenService;
import com.tfg.stellarium.model.services.ImagenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {
    @Autowired
    private ImagenServiceImpl imagenService;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarImagen(@RequestParam("archivo") MultipartFile archivo) {
        try {
            imagenService.guardarImagen(archivo);
            return ResponseEntity.ok("Imagen guardada correctamente");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable("id") Long id) {
        Imagen imagen = imagenService.obtenerImagen(id);
        if (imagen != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen.getDatos());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Imagen>> obtenerTodasLasImagenes() {
        List<Imagen> imagenes = imagenService.obtenerTodas();
        return ResponseEntity.ok(imagenes);
    }

}
