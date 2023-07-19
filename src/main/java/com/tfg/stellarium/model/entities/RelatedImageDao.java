package com.tfg.stellarium.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelatedImageDao extends JpaRepository<RelatedImage, Long> {
    List<RelatedImage> findRelatedImagesByPost(final Post post);
}
