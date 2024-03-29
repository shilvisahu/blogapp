package com.example.blogApp.service.impl;

import com.example.blogApp.payload.PostDto;
import com.example.blogApp.entity.Post;
import com.example.blogApp.exception.ResourceNotFoundException;
import com.example.blogApp.payload.PostResponse;
import com.example.blogApp.repository.PostRepository;
import com.example.blogApp.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> postList = posts.getContent();

        List<PostDto> postDtoList = postList.stream().map(post -> mapToPostDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setPosts(postDtoList);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getNumberOfElements());
        postResponse.setTotalPages(posts.getTotalPages());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        PostDto postDto = new PostDto();
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("there is no post with this ID","id"));
        return mapToPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post postFound =  postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("there is no post with this ID","id"));
        PostDto postDto1 = mapToPostDto(postFound);
        if(!postDto1.equals(postDto)){
            postDto1.setId(id);
            if(!postDto.getDescription().isEmpty())postDto1.setDescription(postDto.getDescription());
            if(!postDto.getContent().isEmpty())postDto1.setContent(postDto.getContent());
            if(!postDto.getTitle().isEmpty())postDto1.setTitle(postDto.getTitle());
        }
        Post post = mapToPost(postDto1);
        Post updatedPost = postRepository.save(post);
        return mapToPostDto(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    private Post mapToPost(PostDto postDto){
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    private PostDto mapToPostDto(Post newPost){
        PostDto newPostDto = new PostDto();
        newPostDto.setId(newPost.getId());
        newPostDto.setTitle(newPost.getTitle());
        newPostDto.setDescription(newPost.getDescription());
        newPostDto.setContent(newPost.getContent());
        return newPostDto;
    }
}
