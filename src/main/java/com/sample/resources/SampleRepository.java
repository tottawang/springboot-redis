package com.sample.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.sample.domain.User;

@Repository
public class SampleRepository {

  @Cacheable("users")
  public List<User> getUsers() {
    System.out.println("getUsers gets called");
    List<User> users = new ArrayList<>();
    users.add(new User(0L, "user0"));
    users.add(new User(1L, "user1"));
    return users;
  }

  @Cacheable(value = "emptyUsers", unless = "#result?.isEmpty()")
  public List<User> getEmptyUsers() {
    System.out.println("getEmptyUsers gets called");
    List<User> users = new ArrayList<>();
    return users;
  }

  @CacheEvict(value = "users")
  public void evict() {}

  @CacheEvict(value = "emptyUsers")
  public void evictEmptyUsers() {}

}
