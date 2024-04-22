package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import java.util.List;

public interface PostService {
  PostDto createPost(PostDto postDto);
  List<PostDto> getAllPosts();
  PostResponse getListPostsPaginationSort(int pageNo, int pageSize, String sortBy, String sortDir);
  PostDto getPostById(long id);
  PostDto updatePost(PostDto postDto, long id);
  void deletePostById(long id);
}
