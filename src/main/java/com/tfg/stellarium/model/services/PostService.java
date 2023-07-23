package com.tfg.stellarium.model.services;

import com.tfg.stellarium.model.entities.Post;

import java.util.List;

public interface PostService {
    Long createPost(Post post);

    Post searchPost(Long id);

    List<Post> searchAllPosts();

    List<Post> searchPostsByType(String tipo);

    boolean deletePost(Long id);
}
