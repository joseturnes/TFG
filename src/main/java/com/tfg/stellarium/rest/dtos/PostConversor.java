package com.tfg.stellarium.rest.dtos;

import com.tfg.stellarium.model.entities.Post;
import com.tfg.stellarium.model.entities.RelatedImage;

import java.util.List;
import java.util.stream.Collectors;

public class PostConversor{

    public static PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setDescription(post.getDescription());
        postDto.setName(post.getName());

        List<byte[]> images = post.getRelatedImages().stream()
                .map(RelatedImage::getImage)
                .collect(Collectors.toList());

        postDto.setRelatedImages(images);

        return postDto;
    }

    public static Post toEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setDescription(postDto.getDescription());
        post.setName(postDto.getName());

        List<RelatedImage> relatedImages = postDto.getRelatedImages().stream()
                .map(RelatedImage::new)
                .collect(Collectors.toList());
        post.setRelatedImages(relatedImages);

        return post;
    }
}
