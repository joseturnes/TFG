package com.tfg.stellarium.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDao extends JpaRepository<Post, Long> {
    List<Post> findAllByTipo(String tipo);
}
