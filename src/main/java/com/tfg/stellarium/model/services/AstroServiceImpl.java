package com.tfg.stellarium.model.services;

import com.tfg.stellarium.model.common.exceptions.DuplicateInstanceException;
import com.tfg.stellarium.model.common.exceptions.InstanceNotFoundException;
import com.tfg.stellarium.model.entities.Astro;
import com.tfg.stellarium.model.entities.AstroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AstroServiceImpl implements AstroService {

    @Autowired
    private AstroDao astroDao;


    @Override
    public void createAstro(Astro astro) throws DuplicateInstanceException {
        astro.setName(astro.getName().toLowerCase());

        if (astroDao.existsByName(astro.getName().toLowerCase())) {
            throw new DuplicateInstanceException("project.entities.astro", astro.getId());
        }

        astroDao.save(astro);

    }

    @Override
    public Astro getAstroById(Long id) {
        return astroDao.findById(id).orElseThrow();
    }

    @Override
    public Astro getAstroByName(String name) {
        return astroDao.findAstroByMapName(name.toLowerCase());
    }

    @Override
    public List<Astro> getAllAstros() {
        return astroDao.findAll();
    }

    @Override
    public boolean deleteByName(String name) {
        Astro astro = astroDao.findAstroByName(name);
        astroDao.delete(astro);
        return astroDao.findById(astro.getId()).isEmpty();
    }
}
