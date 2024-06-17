package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import java.util.List;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/api/posts"))
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

  PostService postService;

  @PreAuthorize("hasRole('ADMIN')")
  // create blog post rest api
  @PostMapping
  public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
    return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
  }

  // get all post rest api
  @GetMapping
  public List<PostDto> getAllPosts(){
    return postService.getAllPosts();
  }

  // get all post rest api pagination
  @GetMapping("/pagination-sort")
  public PostResponse getListPostsPaginationSort(
      @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
      @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
      @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
      @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
  ){
    return postService.getListPostsPaginationSort(pageNo, pageSize, sortBy, sortDir);
  }

  // get post by id
  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
    return ResponseEntity.ok(postService.getPostById(id));
  }

  @PreAuthorize("hasRole('ADMIN')")
  // update post by id rest api
  @PutMapping("/{id}")
  public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
      @PathVariable(name = "id") long id) {
    PostDto postResponse = postService.updatePost(postDto, id);
    return new ResponseEntity<>(postResponse, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN')")
  // delete post rest api
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
    postService.deletePostById(id);
    return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
  }

}
