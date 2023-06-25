package com.tfg.stellarium.model.services;

import com.tfg.stellarium.model.entities.Imagen;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImagenService {
    public void guardarImagen(MultipartFile archivo) throws IOException;

    public Imagen obtenerImagen(Long id);

    public List<Imagen> obtenerTodas();
}
