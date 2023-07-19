package com.tfg.stellarium.rest.dtos;

import java.util.List;

public class PostDto {

    private Long id;
    private String description;
    private List<byte[]> relatedImages;
    private String name;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<byte[]> getRelatedImages() {
        return relatedImages;
    }

    public void setRelatedImages(List<byte[]> relatedImages) {
        this.relatedImages = relatedImages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
