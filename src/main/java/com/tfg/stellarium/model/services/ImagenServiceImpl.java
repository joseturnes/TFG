package com.tfg.stellarium.model.services;

import com.tfg.stellarium.model.entities.Imagen;
import com.tfg.stellarium.model.entities.ImagenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ImagenServiceImpl {

    @Autowired
    private ImagenDao imagenDao;

    public void guardarImagen(MultipartFile archivo) throws IOException {
        Imagen imagen = new Imagen();
        imagen.setNombre(archivo.getOriginalFilename());
        imagen.setDatos(archivo.getBytes());
        imagenDao.save(imagen);
    }

    public Imagen obtenerImagen(Long id) {
        return imagenDao.findById(id).orElse(null);
    }

    public List<Imagen> obtenerTodas() {
        return imagenDao.findAll();
    }

    public boolean eliminarImagen(final Long id){
        imagenDao.deleteById(id);

        if(imagenDao.existsById(id)){
            return false;
        }
        return true;
    }
}
