package com.example.blogApp.dto;

import com.example.blogApp.entity.Post;
import lombok.Data;

@Data
public class PostDto{
    private long id;
    private String title;
    private String description;
    private String content;
}
