package com.example.blogApp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse<P> {
    private List<PostDto> posts;
    private int pageSize;
    private int pageNo;
    private int totalElements;
    private int totalPages;

}
