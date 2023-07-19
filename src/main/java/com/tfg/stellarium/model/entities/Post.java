package com.tfg.stellarium.model.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RelatedImage> relatedImages;

    private String name;

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

    public List<RelatedImage> getRelatedImages() {
        return relatedImages;
    }

    public void setRelatedImages(List<RelatedImage> relatedImages) {
        this.relatedImages = relatedImages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
