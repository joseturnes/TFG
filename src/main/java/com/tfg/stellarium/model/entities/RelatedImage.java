package com.tfg.stellarium.model.entities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class RelatedImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private byte[] image;

    public RelatedImage() {
    }

    public RelatedImage(byte[] image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatedImage that = (RelatedImage) o;
        return id.equals(that.id) && post.equals(that.post) && Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, post);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
