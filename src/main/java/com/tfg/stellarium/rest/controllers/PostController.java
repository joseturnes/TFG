package com.tfg.stellarium.rest.controllers;

import com.tfg.stellarium.model.entities.Post;
import com.tfg.stellarium.model.entities.RelatedImage;
import com.tfg.stellarium.model.entities.RelatedImageDao;
import com.tfg.stellarium.model.services.PostServiceImpl;
import com.tfg.stellarium.rest.dtos.PostConversor;
import com.tfg.stellarium.rest.dtos.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostServiceImpl postService;

    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody PostDto postDto) {
        Long postId = postService.createPost(PostConversor.toEntity(postDto));
        if(postId == -1L) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(postId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> searchPost(@PathVariable Long id) {
        try {
            PostDto postDto = PostConversor.toDto(postService.searchPost(id));
            return new ResponseEntity<>(postDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> searchAllPosts() {
        List<PostDto> postDtos = postService.searchAllPosts().stream()
                .map(PostConversor::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        boolean isDeleted = postService.deletePost(id);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
