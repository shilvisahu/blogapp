package com.example.blogApp.service.impl;

import com.example.blogApp.dto.PostDto;
import com.example.blogApp.entity.Post;
import com.example.blogApp.repository.PostRepository;
import com.example.blogApp.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        PostDto newPostDto = new PostDto();
        newPostDto.setTitle(newPost.getTitle());
        newPostDto.setDescription(newPost.getDescription());
        newPostDto.setContent(newPost.getContent());
        return newPostDto;
    }
}
