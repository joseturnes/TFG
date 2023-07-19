package com.tfg.stellarium.model.services;

import com.tfg.stellarium.model.entities.Post;
import com.tfg.stellarium.model.entities.PostDao;
import com.tfg.stellarium.model.entities.RelatedImage;
import com.tfg.stellarium.model.entities.RelatedImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private RelatedImageDao relatedImageDao;

    @Override
    public Long createPost(Post post) {
        Post savedPost =  postDao.save(post);
        List<RelatedImage> images = savedPost.getRelatedImages();
        images.forEach(image -> image.setPost(savedPost));
        relatedImageDao.saveAll(images);
        savedPost.getRelatedImages().addAll(images);
        postDao.save(savedPost);
        if(savedPost.getId() != null){
            return  savedPost.getId();
        }
        return -1L;
    }

    @Override
    public Post searchPost(Long id) {
        return postDao.findById(id).stream().findAny().orElseThrow();
    }

    @Override
    public List<Post> searchAllPosts() {
        return postDao.findAll();
    }

    @Override
    public boolean deletePost(Long id) {
        postDao.deleteById(id);
        return postDao.findById(id).isEmpty();
    }
}
