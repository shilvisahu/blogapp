package com.example.blogApp.service;

import com.example.blogApp.payload.PostDto;
import com.example.blogApp.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto,long id);

    void deletePost(long id);
}
