package com.example.blogApp.service.impl;

import com.example.blogApp.dto.PostDto;
import com.example.blogApp.entity.Post;
import com.example.blogApp.repository.PostRepository;
import com.example.blogApp.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToPost(postDto);

        Post newPost = postRepository.save(post);

        PostDto newPostDto = mapToPostDto(newPost);
        return newPostDto;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToPostDto(post)).collect(Collectors.toList());
    }

    private Post mapToPost(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    private PostDto mapToPostDto(Post newPost){
        PostDto newPostDto = new PostDto();
        newPostDto.setTitle(newPost.getTitle());
        newPostDto.setDescription(newPost.getDescription());
        newPostDto.setContent(newPost.getContent());
        return newPostDto;
    }
}
