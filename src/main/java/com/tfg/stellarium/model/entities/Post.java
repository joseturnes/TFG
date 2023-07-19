package com.tfg.stellarium.model.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Post {

    public enum PostType {
        CURIOSIDADES,
        INICIATIVAS_AGRUPACIONS,
        ARTE_ASTRONOMIA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private java.lang.String description;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RelatedImage> relatedImages;

    private java.lang.String name;

    @Column(name = "post_type")
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public List<RelatedImage> getRelatedImages() {
        return relatedImages;
    }

    public void setRelatedImages(List<RelatedImage> relatedImages) {
        this.relatedImages = relatedImages;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) && description.equals(post.description) && relatedImages.equals(post.relatedImages) && name.equals(post.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, relatedImages, name);
    }
}
