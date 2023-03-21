package com.example.blogApp.service;

import com.example.blogApp.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();


}
