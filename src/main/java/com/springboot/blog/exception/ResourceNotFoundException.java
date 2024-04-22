package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  private String resourceName;
  private String fielName;
  private long fielValue;

  public ResourceNotFoundException(String resourceName, String fielName, long fileValue) {
    super(String.format("%s not found with %s : '%s'", resourceName, fielName, fileValue)); // Post not found with id : 1
    this.resourceName = resourceName;
    this.fielName = fielName;
    this.fielValue = fileValue;
  }

  public String getResourceName() {
    return resourceName;
  }

  public String getFielName() {
    return fielName;
  }

  public long getFileValue() {
    return fielValue;
  }

}
